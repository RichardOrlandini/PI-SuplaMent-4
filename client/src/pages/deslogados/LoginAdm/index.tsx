import { useState } from 'react';
//import { FiMail, FiLock } from 'react-icons/fi';

import { Container, Form, Background } from './styles';
import { Button, TextField } from "@mui/material"
import { Link, useNavigate } from 'react-router-dom';
import usePost from 'services/usePost';
import autenticaStore from 'common/stores/authentica.store';
import { ILogin , LoginDataAuthAPi} from 'shared/interfaces/IUsuario';
import { SENHA_AUTH, HOST_AUTH, HOST_API} from 'constants/url';

export function LoginAdm() {

  
  const [email, setEmail] = useState('')
  const [senha, setSenha] = useState('')
  const {cadastrarDados, erro, sucesso, resposta} = usePost();
  const navigate = useNavigate();


  const handleSignIN = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
   
    const usuario: ILogin = {
      email: email,
      senha: SENHA_AUTH,
    }

    try {
      //auth api
      cadastrarDados({ host: HOST_AUTH, url: "/login", dados: usuario})
      const { accessToken, message: m1 }: LoginDataAuthAPi = resposta.data;

      if (m1 != null) {
        alert(m1);
        return;
      } 

      //api
      usuario.senha = senha;

      cadastrarDados({host: HOST_API, url: "/login/usuario", dados: usuario});

      const {id, email, token, role, message}  = resposta.data

      if (message != null) {
        alert(message);
        return;
      }

      autenticaStore.login({id, email, token, role});
      resposta && navigate('/home');

    } catch (erro) {
      erro && alert('Não foi possível fazer login')
    }
  }

  return (
    <Container>
      <Form onSubmit={handleSignIN} >
        <h1>ADM</h1>
        <h2>Faça seu login</h2>
        <TextField
          label="E-mail"
          color="warning"
          placeholder="Digite seu E-mail"
          type="text"
          variant="standard"
          fullWidth
          required
          // icon={FiMail}
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

        <Button title="Entrar" variant="contained"  />
        <Link  to="/">
          Home
        </Link>
      </Form>

      <Background />
    </Container>
  );
}