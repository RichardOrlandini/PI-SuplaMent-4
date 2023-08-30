import { Input } from "../../components/Input";
//import { Link, useNavigate } from 'react-router-dom';
import { FiMail, FiLock } from 'react-icons/fi';
import { Container, Form, Background } from './styles';
import { Input } from '../../components/Input'
import { Button } from '../../components/Button'
import { useState } from 'react';


import { useAuth } from '../../hooks/auth';

export function Login() {
   // const navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");


    const { signIn } = useAuth();

    function handleLogin() {
        signIn({ email, password });
    }

    return (

        <Container>
            <Background>

                <Form>
                    <h1>Supla-Ment</h1>
                    <p>Compre na melhor loja de suplementos</p>


                    <h2>Entrar</h2>
                    <Input
                        placeholder="Email"
                        type="text"
                        icon={FiMail}
                        onChange={event => setEmail(event.target.value)}
                    />

                    <Input
                        placeholder="Senha"
                        type="password"
                        icon={FiLock}
                        onChange={event => setPassword(event.target.value)}
                    />
                    <Button title="Entrar" onClick={handleLogin} />
                </Form>

                {/* <Link to="/register">
                Criar conta
            </Link> */}
            </Background>
        </Container>
    )
}
//functions
