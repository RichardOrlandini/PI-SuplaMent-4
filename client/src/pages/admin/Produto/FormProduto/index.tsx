import { Box, Button, TextField, Typography, Alert, FormControl, InputLabel, Select, MenuItem } from "@mui/material"
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom"
import { api } from "../../../../services/api";
import ICategoria from "../../../../shared/interfaces/ICategoria";
import { IProduto } from "../../../../shared/interfaces/IProduto";
import { IErroMessage } from "../../../../shared/interfaces/IErroMessage";
import { Method } from "axios";
import IFornecedor from "shared/interfaces/IFornecedor";

export function FormProduto() {

    //TODO: COLOCAR FRELG DE DISABLE FAZENOD UM IF VENDO SE O USURIO LOGADO E UM ESTOQUISTA, SE FORM ADMIN TIRAR O DISABLE. 

    const params = useParams();
    const [erro, setErro] = useState<IErroMessage | null>(null);

    const [nome, setNome] = useState('');
    const [descri, setDescri] = useState('');
    const [valor, setValor] = useState('');
    const [qtd, setQtd] = useState('');

    const [categorias, setCategorias] = useState<ICategoria[]>([]);
    const [categoria, setCategoria] = useState<number | null>(null);

    const [fornecedores, setFornecedores] = useState<IFornecedor[]>([]);
    const [fornecedor, setFornecedor] = useState<number | null>(null);

    const [imagem, setImagem] = useState<File | null>(null);
    const navigate = useNavigate();

    const GET_CATEGORIAS = () => {

        api.get<ICategoria[]>("/categoria")
            .then(resp => {
                setCategorias(resp.data.content);
            });
    }

    const GET_FORNECEDORES = () => {

        api.get<ICategoria[]>("/fornecedores")
            .then(resp => {
                setFornecedores(resp.data.content);
            });
    }


    const selecionarArquivo = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files?.length) {
            setImagem(e.target.files[0]);
        } else {
            setImagem(null);
        }
    }

    useEffect(() => {

        GET_CATEGORIAS();
        GET_FORNECEDORES();

        if (params.id) {
            api.get<IProduto>(`/produto/${params.id}`)
                .then(resp => {
                    setNome(resp.data.nome);
                    setDescri(resp.data.descri);
                    setQtd(resp.data.qtd);
                    setValor(resp.data.valor);
                    //setCategoria(resp.data.categoria?);
                    //setImagem(resp.data.imagem?)
                });
        }

    }, [params]);


    const aoSubmeterForm = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        let url = "/produto";
        let method: Method = 'POST';
        let mensagem = 'Produto Criado com sucesso!';
        if (params.id) {
            method = 'PUT';
            url += `${params.id}/`;
            mensagem = "Produto Atualizado com sucesso!";
        }

        // Crie um objeto com os dados que você deseja enviar
        const data = {
            nome: nome,
            qtd: qtd,
            fornecedor_id: fornecedor,
            categoria_id: categoria,
            nomeImagem: imagem ? imagem.name : null
        };


        const json = JSON.stringify(data);
        console.log(json)

        const formData = new FormData();
        formData.append('json', json);
    
        if (imagem) {
            formData.append('imagem', imagem);
        }
    
        api.request({
            url,
            method,
            headers: {
                'Content-type': 'multipart/form-data'
            },
            data: formData
        })
            .then(() => {
                alert(mensagem);
                navigate('/admin/produtos');
            })
            .catch(e => {
                console.log(e)
                if (typeof e.response.data === 'string') {
                    setErro({ campo: 'Erro', mensagem: e.response.data });
                } else {
                    const erro: IErroMessage = e.response.data[0];
                    setErro(erro);
                }
            })
    }

    // setNome('');
    // setDescri('');
    // setValor('');
    // setQtd('');
    // setCategoria('');
    // setCategorias([]);
    // setImagem(null);

    return (
        <Box sx={{ display: 'flex', flexDirection: "column", alignItems: "center", flexGrow: 1 }} >
            <Typography component="h1" variant="h6"> Formulário de Produtos </Typography>
            <Box component="form" sx={{ width: '100%' }} onSubmit={aoSubmeterForm}>
                {erro && <Alert onClick={() => setErro(null)} severity="error">{erro.campo}, {erro.mensagem}!</Alert>}

                <TextField
                    value={nome}
                    onChange={e => setNome(e.target.value)}
                    label="Nome do Produto"
                    variant="standard"
                    fullWidth
                    required
                    margin="dense"
                />

                <TextField
                    value={descri}
                    onChange={e => setDescri(e.target.value)}
                    label="Descrição do produto"
                    variant="standard"
                    fullWidth
                    margin="dense"
                />

                <TextField
                    value={valor}
                    onChange={e => setValor(e.target.value)}
                    label="Valor do produto"
                    variant="standard"
                    fullWidth
                    margin="dense"

                />

                <TextField
                    value={qtd}
                    onChange={e => setQtd(e.target.value)}
                    label="Quantidade do produto"
                    variant="standard"
                    fullWidth
                    margin="dense"
                    sx={{marginBottom : 3}}
                    required
                />

                <FormControl margin="dense" fullWidth  sx={{marginBottom : 3}} >
                    <InputLabel margin="dense" id="select-cat">Categoria</InputLabel>

                    <Select labelId="select-cat" margin="dense" value={categoria} onChange={e => setCategoria(e.target.value)} >
                        {categorias.map(c => <MenuItem key={c.id} value={c.id}>
                            {c.descricao}
                        </MenuItem>)}
                    </Select>
                </FormControl >

                <FormControl margin="dense" fullWidth  sx={{marginBottom : 3}} >
                    <InputLabel margin="dense" id="select-cat">Fornecedor</InputLabel>

                    <Select labelId="select-cat" margin="dense" value={fornecedor} onChange={e => setFornecedor(e.target.value)} >
                        {fornecedores.map(f => <MenuItem key={f.id} value={f.id}>
                            {f.nome}
                        </MenuItem>)}
                    </Select>
                </FormControl >


                <input type="file" onChange={selecionarArquivo} />
                <Button sx={{ marginTop: 1 }} type="submit" fullWidth variant="outlined">Salvar</Button>
            </Box>
        </Box>
    )
}
