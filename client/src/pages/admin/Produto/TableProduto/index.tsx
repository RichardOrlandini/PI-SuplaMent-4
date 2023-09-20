
import { Button, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material"
import { useEffect, useState } from "react"
import { IProduto } from "../../../../shared/interfaces/IProduto"
import { api } from "../../../../services/api";
import { Link as RouterLink, useNavigate } from 'react-router-dom'
import { useAuth } from "../../../../hooks/auth";
import { IPaginacao } from "../../../../shared/interfaces/IPaginacao";
import { AxiosError } from "axios";

export function TableProduto() {

    const [produtos, setProdutos] = useState<IProduto[]>([]);
    const context = useAuth();
    const navigate = useNavigate();

    useEffect(() => {

        api.get<IPaginacao<IProduto>>("/produtos")
            .then(resp => {
                setProdutos(resp.data.content);
            })
            .catch(e => {
                if (e.message && e.code === "ERR_BAD_REQUEST") {
                    alert(`Token de acesso inspirado, faça login novamente`)
                    context?.signOut();
                    navigate("/");
                }
            })
    }, []);

    const excluir = (produtoAhSerExcluido: IProduto) => {
        api.delete(`/produtos/${produtoAhSerExcluido.id}`)
            .then((resp) => {
                console.log(resp)
                const produtosFiltrados = produtos.filter(produto => produto.id !== produtoAhSerExcluido.id);
                setProdutos([...produtosFiltrados])
            }) 
            .catch(e => console.log(e))
    }

    return (
        <TableContainer component={Paper} >
            <RouterLink  to="/novo">
                <Button sx={{ backgroundColor: '#666360', color: '#F4EDE8', marginLeft: 5, marginTop: 2 }}>
                    Novo
                </Button>
            </RouterLink>

            <Table sx={{marginTop: 5}}>

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

        </TableContainer>
    )
}
