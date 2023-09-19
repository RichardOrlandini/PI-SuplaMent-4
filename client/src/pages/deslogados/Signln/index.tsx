import { useState } from 'react';
//import { FiMail, FiLock } from 'react-icons/fi';


import { Container, Form, Background } from './styles';
import { Button, TextField } from "@mui/material"
import { IAuthContext } from '../../../shared/interfaces/IAuthContext';
import { useAuth } from '../../../hooks/auth';

export function Signln() {
  const [email, setEmail] = useState('')
  const [senha, setSenha] = useState('')
  const context: IAuthContext | null = useAuth();

  function handleSignIN() {
    context?.signIn(email, senha);
  }

  return (
    <Container>
      <Form>
        <h1>Supla-Ment</h1>
        <p>Compre os melhores suplementos</p>

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

        <Button title="Entrar"  variant="contained" onClick={handleSignIN} />
      </Form>

      <Background />
    </Container>
  );
}