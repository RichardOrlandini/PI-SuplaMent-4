import { Box, Button, TextField, Typography } from "@mui/material"
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom"
import { api } from "../../../../services/api";
import ICategoria from "../../../../shared/interfaces/ICategoria";

export function FormCategoria() {

    const params = useParams();
    const [nomeCategoria, setNomeCategoria] = useState('');

    useEffect(() => {
        if (params.id) {
            api.get<ICategoria>(`/categorias/${params.id}`)
                .then(resp => setNomeCategoria(resp.data.nome));
        }
    }, [params]);


    const aoSubmeterForm = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        if (params.id) {
            api.put(`/categorias/${params.id}`, {
                nome: nomeCategoria
            })
                .then(() => {
                    alert("Categoria atualizada com suceso!");
                })
                .catch(e => {
                    console.log(e)
                })
        } else {
            api.post(`/categorias`, {
                nome: nomeCategoria
            })
                .then(() => {
                    alert("Categoria cadastrada com suceso!");
                })
                .catch(e => {
                    console.log(e)
                })
        }
    }

    return (
        <Box sx={{ display: 'flex', flexDirection: "column", alignItems: "center", flexGrow: 1}} >
            <Typography component="h1" variant="h6"> Formulário de Categorias </Typography>
            <Box component="form" sx={{ width: '100%'}} onSubmit={aoSubmeterForm}>
                <TextField
                    value={nomeCategoria}
                    onChange={e => setNomeCategoria(e.target.value)}
                    label="Nome da Categoria"
                    variant="standard"
                    fullWidth
                    required
                />
                <Button sx={{ marginTop: 1}} type="submit" fullWidth variant="outlined">Salvar</Button>
            </Box>
        </Box>
    )
}
