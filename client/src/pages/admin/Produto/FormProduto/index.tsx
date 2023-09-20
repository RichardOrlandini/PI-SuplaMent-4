import { Box, Button, TextField, Typography } from "@mui/material"
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom"
import { api } from "../../../../services/api";
import ICategoria from "../../../../shared/interfaces/ICategoria";
import { IProduto } from "../../../../shared/interfaces/IProduto";

export function FormProduto() {

    const params = useParams();
    const [nome, setNome] = useState('');

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
                    alert("Produto atualizada com suceso!");
                })
                .catch(e => {
                    console.log(e)
                })
        } else {
            api.post(`/produtos`, {
                nome: nome
            })
                .then(() => {
                    alert("Produto cadastrada com suceso!");
                })
                .catch(e => {
                    console.log(e)
                })
        }
    }

    return (
        <Box sx={{ display: 'flex', flexDirection: "column", alignItems: "center", flexGrow: 1}} >
            <Typography component="h1" variant="h6"> Formulário de Produtos </Typography>
            <Box component="form" sx={{ width: '100%'}} onSubmit={aoSubmeterForm}>
                <TextField
                    value={nome}
                    onChange={e => setNome(e.target.value)}
                    label="Nome do Produto"
                    variant="standard"
                    fullWidth
                    required
                />
                <Button sx={{ marginTop: 1}} type="submit" fullWidth variant="outlined">Salvar</Button>
            </Box>
        </Box>
    )
}
