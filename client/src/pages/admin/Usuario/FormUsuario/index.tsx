import { Box, Button, TextField, Typography } from "@mui/material"
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom"
import { api } from "../../../../services/api";
import ICategoria from "../../../../shared/interfaces/ICategoria";
import { IUsuario } from "../../../../shared/interfaces/IUsuario";

export function FormUsuario() {

    const params = useParams();
    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');
    const [role, setRole] = useState('');
    const [telefone, setTelefone] = useState('');
    const [endereco, setEndereco] = useState('');


    useEffect(() => {
        if (params.id) {
            api.get<IUsuario>(`/usuarios/${params.id}`)
                .then(resp => {
                    setNome(resp.data.nome);
                });
        }
    }, [params]);

    const aoSubmeterForm = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        if (params.id) {
            api.put(`/usuarios/${params.id}`, {
                nome: nome
            })
                .then(() => {
                    alert("Usuario atualizado com suceso!");
                })
                .catch(e => {
                    console.log(e)
                })
        } else {
            api.post(`/usuarios`, {
                nome: nome
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
        <Box sx={{ display: 'flex', flexDirection: "column", alignItems: "center", flexGrow: 1 }} >
            <Typography component="h1" variant="h6"> Formulário de Usuário </Typography>
            <Box component="form" sx={{ width: '100%' }} onSubmit={aoSubmeterForm}>

                <TextField
                    value={nome}
                    onChange={e => setNome(e.target.value)}
                    label="Nome do Usuário"
                    variant="standard"
                    fullWidth
                    required
                />

                <TextField
                    value={email}
                    onChange={e => setEmail(e.target.value)}
                    label="Email"
                    variant="standard"
                    fullWidth
                    required
                    type="email"
                />

                <TextField
                    value={senha}
                    type="password"
                    onChange={e => setSenha(e.target.value)}
                    label="Senha"
                    variant="standard"
                    fullWidth
                    required
                />

                <TextField // TODO: MUDAR PARA INPUT SELECT COM OS VALORES DE ROLES DISPÓNIVEIS PELO BACKEND.
                    value={role}
                    onChange={e => setRole(e.target.value)}
                    label="Grupo"
                    variant="standard"
                    fullWidth
                    required
                />

                <TextField
                    value={telefone}
                    onChange={e => setTelefone(e.target.value)}
                    label="Telefone"
                    variant="standard"
                    fullWidth
                    type="tel"
                    required
                />

                <TextField  //Ver como funciona o cadastro de enreço.
                    value={endereco}
                    onChange={e => setEndereco(e.target.value)}
                    label="Endereço"
                    variant="standard"
                    fullWidth
                />

                <Button sx={{ marginTop: 1 }} type="submit" fullWidth variant="outlined">Salvar</Button>
            </Box>
        </Box>
    )
}
