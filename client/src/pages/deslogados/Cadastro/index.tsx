import styled from "styled-components";
import fundo from "../../../assets/logo.png";


import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { ICliente, IEndereco } from 'shared/interfaces/IUsuario';
import { Container as ContainerStyles, Formulario, Imagem, Titulo } from './styles';
import { Step, StepLabel, Stepper, Container, Box, Grid, TextField, Button, Checkbox } from "@mui/material";

import ButtonCustumer from "../../../components/ButtonCustumer";
import CampoDigitacao from "../../../components/CampoDigitacao";
import usePost from "services/usePost";
import { HOST_API, HOST_AUTH } from "constants/url";
import { Endereco } from "components/Endereco";



const ListaEnderecos = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
`;

export const StepCustomizada = styled.div<PropsCustomizadas>`
    background-color: ${({ cor }) => cor};
    width: 16px;
    height: 16px;
    border-radius: 50%;
`

export const BotaoCustomizado = styled(ButtonCustumer)`
  width: 50%;
`;

interface PropsCustomizadas {
    cor: string
}

export function Cadastro() {
    const { cadastrarDados, erro, sucesso } = usePost();
    const navigate = useNavigate();
    const [etapaAtiva, setEtapaAtiva] = useState(0);

    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');
    const [senhaVerificada, setSenhaVerificada] = useState('');
    const [nome, setNome] = useState('');
    const [cpf, setCpf] = useState('');

    const [telefone, setTelefone] = useState('');
    const [cep, setCep] = useState('');
    const [rua, setRua] = useState('');
    const [numero, setNumero] = useState('');
    const [estado, setEstado] = useState('');
    const [complemento, setComplemento] = useState('');


    const [enderecos, setEnderecos] = useState<IEndereco[]>([]);
    const [enderecoPrincipal, setEnderecoPrincipal] = useState<number | null>(null);


    const addEndereco = () => {
        const newEndereco: IEndereco = {
            cep,
            rua,
            numero,
            complemento,
            estado,
            principal: enderecoPrincipal === enderecos.length
        };

        if (newEndereco.principal) {
            const enderecosAtualizados = enderecos.map(endereco => ({ ...endereco, principal: false }));
            setEnderecos([...enderecosAtualizados, newEndereco]);
        } else {
            setEnderecos([...enderecos, newEndereco]);
        }
    }

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        const cliente: ICliente = {
            email,
            nome,
            senha,
            enderecos,
            cpf,
            telefone,
        }

        if (etapaAtiva !== 0) {
            try {
                const dataAuth = { email, password: 123456 };

                cadastrarDados({ host: HOST_AUTH, url: 'api/user', dados: dataAuth });
                if (erro) {
                    alert(erro);
                }

                cadastrarDados({ host: HOST_API, url: 'cliente', dados: cliente });
                if (sucesso) {
                    alert(sucesso);
                }

                navigate('/login');
            } catch (e) {
                e && alert('Erro ao cadastrar os dados: ' + erro);
            }
        }

        setEtapaAtiva(etapaAtiva + 1); // atualiza o estado da etapa para a próxima etapa
    }

    return (
        <>

            <Stepper activeStep={etapaAtiva}>
                <Step>
                    <StepLabel
                        StepIconComponent={(props) => (
                            <StepCustomizada cor={props.active ? 'lightblue' : 'lightgray'} />
                        )}
                    />
                </Step>
                <Step>
                    <StepLabel
                        StepIconComponent={(props) => (
                            <StepCustomizada cor={props.active ? 'lightblue' : 'lightgray'} />
                        )}
                    />
                </Step>
            </Stepper>

            {etapaAtiva === 0 ? (
                <>
                    <Titulo>Primeiro, alguns dados básicos:</Titulo>
                    <Formulario onSubmit={handleSubmit}>
                        <CampoDigitacao
                            tipo="text"
                            label="Nome"
                            valor={nome}
                            placeholder="Insira seu nome"
                            onChange={setNome}
                        />
                        <CampoDigitacao
                            tipo="text"
                            label="CPF"
                            valor={cpf}
                            placeholder="Insira seu cpf"
                            onChange={setCpf}
                        />
                        <CampoDigitacao
                            tipo="email"
                            label="Email"
                            valor={email}
                            placeholder="Insira o endereço de e-mail para login"
                            onChange={setEmail}
                        />
                        <CampoDigitacao
                            tipo="password"
                            label="Senha"
                            valor={senha}
                            placeholder="Digite sua senha"
                            onChange={setSenha}
                        />
                        <CampoDigitacao
                            tipo="password"
                            label="Confirme a senha"
                            valor={senhaVerificada}
                            placeholder="Confirme sua senha"
                            onChange={setSenhaVerificada}
                        />
                        <BotaoCustomizado type="submit">Avançar</BotaoCustomizado>
                    </Formulario>
                </>) : (
                <>
                    <Titulo>Agora, os dados técnicos:</Titulo>
                    <Formulario onSubmit={handleSubmit}>
                        <CampoDigitacao
                            tipo="tel"
                            label="Telefone"
                            valor={telefone}
                            placeholder="(DDD) XXXXX-XXXX"
                            onChange={setTelefone}
                        />
                        <CampoDigitacao
                            tipo="number"
                            label="CEP"
                            valor={cep}
                            placeholder="Insira o CEP"
                            onChange={setCep}

                        />
                        <CampoDigitacao
                            tipo="text"
                            label="Rua"
                            valor={rua}
                            placeholder="Rua"
                            onChange={setRua}
                        />
                        <ContainerStyles>
                            <CampoDigitacao
                                tipo="number"
                                valor={numero}
                                placeholder="Número"
                                onChange={setNumero}
                            />
                            <CampoDigitacao
                                tipo="text"
                                valor={complemento}
                                placeholder="Complemento"
                                onChange={setComplemento}
                            />
                            <CampoDigitacao
                                tipo="text"
                                valor={estado}
                                placeholder="Estado"
                                onChange={setEstado}
                            />


                            <Checkbox
                                aria-label="Endereço principal?"
                                checked={enderecoPrincipal === enderecos.length}
                                onChange={() => setEnderecoPrincipal(enderecos.length)}
                            />

                        </ContainerStyles>

                        <BotaoCustomizado onClick={addEndereco}>Adicionar Endereço</BotaoCustomizado>
                        <BotaoCustomizado type="submit">Cadastrar</BotaoCustomizado>


                        <ListaEnderecos>
                            {enderecos.map((endereco, index) => (
                                <Endereco key={index} {...endereco} />
                            ))}
                        </ListaEnderecos>

                    </Formulario>
                </>
            )
            }

            <Link to="/">
                Home
            </Link>

            <Link to="/login">
                Login
            </Link>
        </>
    )

    // function handleSignUp() {
    //     if (!nome || !email || !senha || !cofSenha) {
    //         return alert("Preencha todos os campos");
    //     }

    //     if (senha !== cofSenha) {
    //         return alert("Preencha as senhas iguais");
    //     }

    //     api.post("/clientes", { nome, email, senha })
    //         .then(() => {
    //             alert("usuário cadastrado com sucesso!");
    //             navigate("/");
    //         })
    //         .catch(error => {
    //             if (error.response) {
    //                 alert(error.response.data.message);
    //             } else {
    //                 alert("Não foi possivel cadastrar");
    //             }
    //         });
    // }

    // return (
    //     <Container>
    //         <Background />

    //         <Form>
    //             <h1>Supla-Ment</h1>
    //             <p>Compre os melhores suplementos</p>

    //             <h2>Cadastro</h2>
    //             <TextField
    //                 label="Nome"
    //                 color="warning"
    //                 placeholder="digite sua senha"
    //                 type="text"
    //                 variant="standard"
    //                 fullWidth
    //                 required
    //                 //icon={FiLock}
    //                 onChange={e => setNome(e.target.value)}
    //             />

    //             <TextField
    //                 label="Email"
    //                 color="warning"
    //                 placeholder="digite sua senha"
    //                 type="email"
    //                 variant="standard"
    //                 fullWidth
    //                 required
    //                 //icon={FiLock}
    //                 onChange={e => setEmail(e.target.value)}
    //             />

    //             <TextField
    //                 label="Senha"
    //                 color="warning"
    //                 placeholder="digite sua senha"
    //                 type="password"
    //                 variant="standard"
    //                 fullWidth
    //                 required
    //                 //icon={FiLock}
    //                 onChange={e => setSenha(e.target.value)}
    //             />

    //             <TextField
    //                 label="Confirme a senha"
    //                 color="warning"
    //                 placeholder="Confirme sua senha"
    //                 type="password"
    //                 variant="standard"
    //                 fullWidth
    //                 required
    //                 //icon={FiLock}
    //                 onChange={e => setCofSenha(e.target.value)}
    //             />

    //             <Button title="Cadastrar" onClick={handleSignUp} />

    //             <Link to="/">
    //                 Voltar para o login
    //             </Link>


    //             <Link to="/">
    //                 Home
    //             </Link>

    //         </Form>


    //     </Container>
    // );
}