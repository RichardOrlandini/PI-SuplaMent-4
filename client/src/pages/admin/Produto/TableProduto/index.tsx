
import { Button, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Box, TextField } from "@mui/material"
import { useEffect, useState } from "react"
import { IProduto } from "../../../../shared/interfaces/IProduto"
import { api } from "../../../../services/api";
import { Link as RouterLink, useNavigate } from 'react-router-dom'
import { useAuth } from "../../../../hooks/auth";
import { IPaginacao } from "../../../../shared/interfaces/IPaginacao";
import { AxiosRequestConfig } from "axios";
import { IParametrosBusca } from "../../../../shared/interfaces/IParametrosBusca";

export function TableProduto() {

    const [produtos, setProdutos] = useState<IProduto[]>([]);
    const [proximaPagina, setProximaPagina] = useState('');
    const [paginaAnterior, setPaginaAnterior] = useState('');
    const [busca, setBusca] = useState('');

    const context = useAuth();
    const navigate = useNavigate();

    const getDados = (url: string, opcoes: AxiosRequestConfig = {}) => {
        api.get<IPaginacao<IProduto>>(url, opcoes)
            .then(resp => {
                setProdutos([...resp.data.content]);
                setProximaPagina(resp.data.last ? '' : `/usuarios?page=${resp.data.number + 1}`);
                setPaginaAnterior(resp.data.first ? '' : `/usuarios?page=${resp.data.number - 1}`);
            })
            .catch(e => {
                if (e.message && e.code === "ERR_BAD_REQUEST") {
                    alert(`Token de acesso inspirado ou inválido, faça login novamente`)
                    context?.signOut();
                    navigate("/");
                }
            })
    }

    const buscar = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        if (!busca) {
            alert("Digite o nome de um produto");
            return;
        }

        const opcoes = {
            params: {

            } as IParametrosBusca
        }

        if (busca) {
            opcoes.params.nome = busca;
        }
        getDados('/produtos/busca', opcoes)
    }

    const excluir = (produtoAhSerExcluido: IProduto) => {
        api.delete(`/produtos/${produtoAhSerExcluido.id}`)
            .then((resp) => {
                console.log(resp)
                const produtosFiltrados = produtos.filter(produto => produto.id !== produtoAhSerExcluido.id);
                setProdutos([...produtosFiltrados])
            })
            .catch(e => console.log(e))
    }

    useEffect(() => {
        getDados("/produtos");
    }, []);


    return (
        <TableContainer component={Paper} >
            <Box sx={{ display: 'flex', alignItems: 'center', width: '800px'}}>
                <RouterLink to="novo">
                    <Button sx={{ backgroundColor: '#666360', color: '#F4EDE8', marginLeft: 5, marginTop: 2, flex: '0 0 50%' }}>
                        Novo +
                    </Button>
                </RouterLink>

                <form onSubmit={buscar} style={{ display: 'flex', flex: '0 0 50%' ,  marginLeft: '60px'}}>
                    <TextField
                        value={busca}
                        onChange={evento => setBusca(evento.target.value)}
                        label="Digite o nome do Produto"
                        placeholder="Digite o nome do Produto"
                        variant="standard"
                        type="text"
                        required
                        style={{ flex: '1' }}
                    />
                    <Button type='submit'>buscar</Button>
                </form>
            </Box>



            <Table sx={{ marginTop: 5 }}>

                <TableHead>
                    <TableRow>
                        <TableCell>Nome</TableCell>
                        <TableCell>Quantidade</TableCell>
                        <TableCell>Valor</TableCell>
                        <TableCell>Status</TableCell>
                        <TableCell>Editar</TableCell>
                        <TableCell>Excluir</TableCell>
                    </TableRow>
                </TableHead>

                <TableBody>
                    {
                        produtos.map(p => <TableRow key={p.id}>
                            <TableCell>{p.nome}</TableCell>
                            <TableCell>{p.qtd}</TableCell>
                            <TableCell>{p.valor}</TableCell>
                            <TableCell>{p.ativo ? "ATIVO" : "INATIVADO"}</TableCell>
                            <TableCell>
                                [ <RouterLink to={`/admin/produtos/${p.id}`}>Editar</RouterLink> ]
                            </TableCell>
                            <TableCell>
                                <Button variant="outlined" color="error" onClick={() => excluir(p)}>
                                    Excluir
                                </Button>
                            </TableCell>
                        </TableRow>
                        )
                    }
                </TableBody>

            </Table>

            <Box sx={{ marginTop: 2, marginLeft: 50, marginRight: 50 }}>
                {<Button onClick={() => getDados(paginaAnterior)} disabled={!paginaAnterior}>
                    Página Anterior
                </Button>}

                {<Button onClick={() => getDados(proximaPagina)} disabled={!proximaPagina}>
                    Próxima página
                </Button>}
            </Box>

        </TableContainer>
    )
}
