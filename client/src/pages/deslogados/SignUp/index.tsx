import { useState } from 'react'; //Ruck para criar estados --

import { Link, useNavigate } from 'react-router-dom';

import { FiLogIn, FiMail, FiLock, FiUser } from 'react-icons/fi';
import { Button, TextField } from "@mui/material"

import { Container, Form, Background } from './styles';
import { api } from '../../../services/api';

export function SignUp() {
    const [nome, setNome] = useState(""); //Quando criamos um estado, podemos informar um valor inicial --
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const [cofSenha, setCofSenha] = useState("");

    const navigate = useNavigate();

    function handleSignUp() {
        if (!nome || !email || !senha || !cofSenha) {
            return alert("Preencha todos os campos")
        }

        api.post("/usuario", { nome, email, senha , role : "CLIENTE" })
            .then(() => {
                alert("usuário cadastrado com sucesso!");
                navigate("/");
            })
            .catch(error => {
                if (error.response) {
                    alert(error.response.data.message);
                } else {
                    alert("Não foi possivel cadastrar");
                }
            });
    }

    return (
        <Container>
            <Background />

            <Form>
                <h1>Supla-Ment</h1>
                <p>Compre os melhores suplementos</p>

                <h2>Cadastro</h2>
                <TextField
                      label="Nome"
                      color="warning"
                      placeholder="digite sua senha"
                      type="text"
                      variant="standard"
                      fullWidth
                      required
                      //icon={FiLock}
                      onChange={e => setNome(e.target.value)}
                />

                <TextField
                      label="Email"
                      color="warning"
                      placeholder="digite sua senha"
                      type="email"
                      variant="standard"
                      fullWidth
                      required
                      //icon={FiLock}
                      onChange={e => setEmail(e.target.value)}
                />

                <TextField
                    label="Senha"
                    color="warning"
                    placeholder="digite sua senha"
                    type="password"
                    variant="standard"
                    fullWidth
                    required
                    //icon={FiLock}
                    onChange={e => setSenha(e.target.value)}
                />

                <TextField
                    label="Confirme a senha"
                    color="warning"
                    placeholder="Confirme sua senha"
                    type="password"
                    variant="standard"
                    fullWidth
                    required
                    //icon={FiLock}
                    onChange={e => setCofSenha(e.target.value)}
                />

                <Button title="Cadastrar" onClick={handleSignUp} />

                <Link to="/">
                    Voltar para o login
                </Link>
            </Form>


        </Container>
    );
}