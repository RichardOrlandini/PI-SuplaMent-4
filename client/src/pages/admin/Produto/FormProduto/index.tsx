import { Box, Button, TextField, Typography, Alert } from "@mui/material"
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom"
import { api } from "../../../../services/api";
import ICategoria from "../../../../shared/interfaces/ICategoria";
import { IProduto } from "../../../../shared/interfaces/IProduto";
import { IErroMessage } from "../../../../shared/interfaces/IErroMessage";

export function FormProduto() {

    const params = useParams();
    const [erro, setErro] = useState<IErroMessage | null>(null);

    const [nome, setNome] = useState('');
    const [descri, setDescri] = useState('');
    const [valor, setValor] = useState('');
    const [qtd, setQtd] = useState('');

    const navigate = useNavigate();

    useEffect(() => {
        if (params.id) {
            api.get<IProduto>(`/produtos/${params.id}`)
                .then(resp => setNome(resp.data.nome));
        }
    }, [params]);


    const aoSubmeterForm = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        if (params.id) {
            api.put(`/produtos/${params.id}`, {
                nome: nome
            })
                .then(() => {
                    alert("Produto atualizado com suceso!");
                    navigate("/admin/produtos");
                })
                .catch(e => {
                    if (typeof e.response.data === 'string') {
                        setErro({ campo: 'Erro', mensagem: e.response.data });
                    } else {
                        const erro: IErroMessage = e.response.data[0];
                        setErro(erro);
                    }
                })
        } else {
            api.post(`/produtos`, {
                nome,
                descri,
                valor,
                qtd
            })
                .then(() => {
                    alert("Produto cadastrado com suceso!");
                    navigate("/admin/produtos");
                })
                .catch(e => {
                    if (typeof e.response.data === 'string') {
                        setErro({ campo: 'Erro', mensagem: e.response.data });
                    } else {
                        const erro: IErroMessage = e.response.data[0];
                        setErro(erro);
                    }
                })
        }
    }

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
                />

                <TextField
                    value={descri}
                    onChange={e => setDescri(e.target.value)}
                    label="Descrição do produto"
                    variant="standard"
                    fullWidth
                    required
                />

                <TextField
                    value={valor}
                    onChange={e => setValor(e.target.value)}
                    label="Valor do produto"
                    variant="standard"
                    fullWidth
                    required
                />

                <TextField
                    value={qtd}
                    onChange={e => setQtd(e.target.value)}
                    label="Quantidade do produto"
                    variant="standard"
                    fullWidth
                    required
                />
                <Button sx={{ marginTop: 1 }} type="submit" fullWidth variant="outlined">Salvar</Button>
            </Box>
        </Box>
    )
}
