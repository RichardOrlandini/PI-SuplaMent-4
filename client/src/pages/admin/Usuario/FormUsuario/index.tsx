import { Box, Button, TextField, Typography, Alert, InputLabel, Select, MenuItem } from "@mui/material"
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom"
import { api } from "../../../../services/api";
import ICategoria from "../../../../shared/interfaces/ICategoria";
import { IUsuario } from "../../../../shared/interfaces/IUsuario";
import { IErroMessage } from "../../../../shared/interfaces/IErroMessage";

export function FormUsuario() {

    const params = useParams();
    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');
    const [confSenha, setConfSenha] = useState('');
    const [role, setRole] = useState('');
    const [cpf, setCpf] = useState('');
    const [telefone, setTelefone] = useState('');
    const [endereco, setEndereco] = useState('');
    const [erro, setErro] = useState<IErroMessage | null>(null);
    const navigate = useNavigate();



    useEffect(() => {
        if (params.id) {
            api.get<IUsuario>(`/usuarios/${params.id}`)
                .then(resp => {
                    setNome(resp.data.nome);
                    setEmail(resp.data.email);
                    setSenha(resp.data.senha);
                    setRole(resp.data.role);
                    setRole(resp.data.cpf);
                    setTelefone(resp.data.telefone ? resp.data.telefone : '');
                    // setEndereco(resp.data.endereco ? resp.data.endereco : '');

                })
                .catch(e => {
                    console.log(e)
                })
        }
    }, [params]);

    const aoSubmeterForm = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();


        if (senha !== confSenha) {
            return alert("As senhas devem ser iguais");
        }

        if (params.id) {
            api.put(`/usuarios/${params.id}`, {
                nome: nome,
                email: email,
                senha: senha,
                role: role
            })
                .then(() => {
                    alert("Usuário atualizado com suceso!");
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
            api.post(`/usuarios`, {
                nome,
                email,
                senha,
                role,
                cpf,
                telefone
            })
                .then(() => {
                    alert("Usuário cadastrada com suceso!");
                    navigate("/admin/usuarios");
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
            <Typography component="h1" variant="h6"> Formulário de Usuário </Typography>
            <Box component="form" sx={{ width: '100%' }} onSubmit={aoSubmeterForm}>

                {erro && <Alert onClick={() => setErro(null)} severity="error">{erro.campo}, {erro.mensagem}!</Alert>}

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


                <TextField
                    value={confSenha}
                    type="password"
                    onChange={e => setConfSenha(e.target.value)}
                    label="Confirme a Senha"
                    variant="standard"
                    fullWidth
                    required
                />

                <InputLabel id="role" sx={{ marginTop: 2 }} >Grupo</InputLabel>
                <Select
                    value={role}
                    labelId="role"
                    label="Role"
                    fullWidth
                    required
                    onChange={(e) => setRole(e.target.value)}
                    variant="standard"
                    color="primary"
                    placeholder="Role"
                >
                    <MenuItem value={"ADMIN"}>ADMIN</MenuItem>
                    <MenuItem value={"ESTOQUISTA"}>ESTOQUISTA</MenuItem>
                    <MenuItem value={"CLIENTE"}>CLIENTE</MenuItem>
                </Select>

                <TextField
                    value={cpf}
                    onChange={e => setCpf(e.target.value)}
                    label="Cpf"
                    variant="standard"
                    fullWidth
                    type="cpf"
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
