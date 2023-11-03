import { useState } from 'react';

import { Container, Form } from './styles';
import { Button, TextField } from "@mui/material"
import { Link, useNavigate } from 'react-router-dom';
import usePost from 'services/usePost';
import autenticaStore from 'common/stores/authentica.store';
import { ILogin, LoginDataAuthAPi, IUsuarioContext, ResponseLoginApiSucess } from 'shared/interfaces/IUsuario';
import { SENHA_AUTH, HOST_AUTH, HOST_API } from 'constants/url';

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
      cadastrarDados({ host: HOST_AUTH, url: "user/auth", dados: usuarioAut });
      const { accessToken, status, message: m1 }: LoginDataAuthAPi = resposta;
      if (m1) {
        alert(m1);
        return;
      }

      //api
      usuario.senha = senha;

      const data = await fetch(`http://localhost:${HOST_API}/login/usuario`, {
        headers : {
          'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(usuario)
      });

      const dataConverted = await data.json();
      const { id, nome, email, role } = dataConverted;

      if (!id) {
        alert("Usuario não encontrado");
        return;
      }
      //TODO, incluir avatar no response de usuario details.
      const user : IUsuarioContext = {id, nome, email, role, token: accessToken, avatar: '' }
      alert("Bem vindo!");

      sessionStorage.setItem("@suplament:token", accessToken);
      autenticaStore.login(user);
      navigate('/admin/produtos');

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