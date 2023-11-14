import { useCarrinhoContext } from "common/contexts/Carrinho";
import Produto from "components/Produto";
import { useContext, useEffect, useMemo, useState } from "react";
import { useNavigate } from "react-router-dom";
import { TotalContainer, PagamentoContainer } from './styles';
import { usePagamento } from "common/contexts/Pagamento";
import ArrowBackIcon from '@material-ui/icons/ArrowBack';
import {
    Container, Drawer,
    Typography, Box, List, ListItem, ListItemText, ListItemSecondaryAction,
    IconButton, Button, Grid, MenuItem, Select, Snackbar, InputLabel
} from '@material-ui/core';
import MuiAlert from '@material-ui/lab/Alert';
import { UsuarioContext } from "common/contexts/Usuario";
import { useEnderecos } from "./compra.helpers";
import { IEndereco } from "shared/interfaces/IUsuario";
import autenticaStore from "common/stores/authentica.store";


export default function Compra() {
    const {
        carrinho,
        quantidadeCarrinho,
        comprar,
        valorTotal = 0
    } = useCarrinhoContext();
    const { saldo = 10 } = useContext(UsuarioContext);
    const {
        formaPagamento,
        mudarFormaPagamento,
        tiposPagamento
    } = usePagamento();

    const [openSnackbar, setOpenSnackbar] = useState(false);
    const navigate = useNavigate();
    const [enderecoSelecionado, setEnderecoSelecionado] = useState<number | null>(null);

    const total = useMemo(() => valorTotal - saldo + 10, [saldo, valorTotal]);

    const [enderecos, setEnderecos] = useState<IEndereco[] | null>(null);

    useEffect(() => {
        // const usuarioContext = autenticaStore.user;
        // const id = usuarioContext.id;
        //  if (id) {

        // const { dados, erro } =   useEnderecos(1);

        // if (!erro) {
        //     setEnderecos(dados);
        // }
        //  }
        setEnderecos(mok);

    }, []);

    const mok = [
        {
            id: 1,
            cep: '12345-678',
            rua: 'Rua Exemplo',
            numero: '123',
            estado: 'UF',
            complemento: 'Apto 101',
            principal: true,
        },
        {
            id: 2,
            cep: '54321-876',
            rua: 'Avenida Teste',
            numero: '456',
            estado: 'UF',
            complemento: 'Casa',
            principal: false,
        },
        {
            id: 3,
            cep: '98765-432',
            rua: 'Travessa Amostra',
            numero: '789',
            estado: 'UF',
            complemento: 'Escritório',
            principal: false,
        }
    ]

    const handleComprar = () => {
        comprar();
        setOpenSnackbar(true);
        // navigate("/");
    }
    return (
        <Container>
            <Button
                variant="contained"
                color="primary"
                onClick={() => navigate("/")}
                style={{ marginBottom: '20px' }}
            >
                Voltar
            </Button>

            <Box maxWidth="100%" overflow="auto" marginLeft={10} marginRight={10} marginTop={3}>
                <Typography variant="h4" component="h1" align="center" style={{ marginBottom: '20px' }} >
                    Carrinho
                </Typography>

                <Grid container spacing={2}>
                    {carrinho.map(item => (
                        <Grid item xs={12} sm={6} md={2} key={item.produto.id}>
                            <Produto {...item.produto} />
                        </Grid>
                    ))}
                </Grid>

            </Box>

            <PagamentoContainer>
                <InputLabel> Forma de Pagamento </InputLabel>
                
                <Select
                    value={formaPagamento.id}
                    onChange={(event) => mudarFormaPagamento(Number(event.target.value))}
                >
                    {tiposPagamento.map(pagamento => (
                        <MenuItem
                            value={pagamento.id}
                            key={pagamento.id}
                        >
                            {pagamento.name}
                        </MenuItem>
                    ))}
                </Select>
            </PagamentoContainer>
            <TotalContainer>
                <div>
                    <h2>Total no Carrinho: </h2>
                    <span>Frete R$ {10}</span>
                    <span>Valor R$ {valorTotal.toFixed(2)}</span>
                    <span>Total R$ {total.toFixed(2)}</span>

                </div>

            </TotalContainer>
            <Button
                onClick={handleComprar}
                disabled={valorTotal <= 0}
                color="primary"
                variant="contained"
            >
                Comprar
            </Button>
            <Snackbar
                anchorOrigin={
                    {
                        vertical: 'top',
                        horizontal: 'right'
                    }
                }
                open={openSnackbar}
                onClose={() => setOpenSnackbar(false)}
            >
                <MuiAlert
                    onClose={() => setOpenSnackbar(false)}
                    severity="success"
                >
                    Compra feita com sucesso!
                </MuiAlert>
            </Snackbar>


            {enderecos && (
                <Box display="flex" flexDirection="column" alignItems="flex-start" style={{ marginTop: '100px' }}>

                    <Typography variant="h6" component="h2" align="center" style={{ marginBottom: '20px' }} >
                        Endereços
                    </Typography>


                    <Typography variant="h6" component="h6" align="left" style={{ marginBottom: '20px' }} >
                        Selecione um endereço de Entrega
                    </Typography>

                    <List>
                        {enderecos.map((endereco, index) => (
                            <ListItem
                                key={endereco.id}
                                style={endereco.id === enderecoSelecionado ? { backgroundColor: '#b56702' } : {}}
                                onClick={() => setEnderecoSelecionado(endereco.id)}
                            >
                                <ListItemText
                                    primary={`Rua: ${endereco.rua}, Número: ${endereco.numero}`}
                                    secondary={`CEP: ${endereco.cep}, Estado: ${endereco.estado}, Complemento: ${endereco.complemento}`}
                                />
                            </ListItem>
                        ))}
                    </List>

                </Box>
            )}



        </Container>
    )

}