import { useState } from 'react';

import { Container, Form } from './styles';
import { Button, TextField } from "@mui/material"
import { Link, useNavigate } from 'react-router-dom';
import usePost from 'services/usePost';
import autenticaStore from 'common/stores/authentica.store';
import { ILogin, IUsuario, LoginDataAuthAPi, IUsuarioContext } from 'shared/interfaces/IUsuario';

import { SENHA_AUTH, HOST_AUTH, HOST_API } from 'constants/url';
import useFetch from 'services/useFetch';
import useLogin from 'services/useLogin';
import { api } from 'services/api';

export function LoginAdm() {

  const [email, setEmail] = useState('')
  const [senha, setSenha] = useState('')
  const { cadastrarDados, erro, sucesso, resposta } = usePost();
  const navigate = useNavigate();


  const handleSignIN = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const usuarioAut = {
      email: email,
      password: SENHA_AUTH,
    }

    const usuario: ILogin = {
      email: email,
      senha: senha,
    }

    try {
     // auth api
      cadastrarDados({ host: HOST_AUTH, url: "user/auth", dados: usuarioAut})
      const { accessToken, message }: any = resposta;

      if (message != null) {
        alert(message);
        return;
      } 

      //api
      usuario.senha = senha;
      cadastrarDados({ host: HOST_API, url: "login/usuario", dados: usuario });
      
      const { id, nome, email, role, message: m2 } : any = resposta;

      if (m2) {
        alert(m2);
        return
      }

      const user : IUsuarioContext = {id, nome, email, role, token: accessToken, avatar: '' }
      
      autenticaStore.login(user);
      resposta && navigate('/admin/produtos');

    } catch (erro) {
      console.log(erro)
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

        <Button title="Entrar" variant="contained" type='submit' />
        <Link to="/">
          Home
        </Link>
      </Form>

    </Container>
  );
}