import { useCarrinhoContext } from "common/contexts/Carrinho";
import Produto from "components/Produto";
import { useContext, useMemo, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Container, TotalContainer, PagamentoContainer } from './styles';
import { usePagamento } from "common/contexts/Pagamento";
import ArrowBackIcon from '@material-ui/icons/ArrowBack';

import { Button, MenuItem, Select, Snackbar, InputLabel } from '@material-ui/core';
import MuiAlert from '@material-ui/lab/Alert';
import { UsuarioContext } from "common/contexts/Usuario";




export default function Compra() {
    const {
        carrinho,
        quantidadeCarrinho,
        comprar,
        valorTotal = 0
    } = useCarrinhoContext();
    const { saldo = 0 } = useContext(UsuarioContext);
    const {
        formaPagamento,
        mudarFormaPagamento,
        tiposPagamento
    } = usePagamento();


    const [openSnackbar, setOpenSnackbar] = useState(false);
    const navigate = useNavigate(); // useNavigate em vez de useHistory
    const total = useMemo(() => saldo - valorTotal, [saldo, valorTotal]);
    return (
        <Container>
            <button onClick={() => navigate(-1)}>
            </button>
            <h2>
                Carrinho
            </h2>
            {carrinho.map((item) => (
                <Produto
                    {...item.produto}
                    key={item.produto.id}
                />
            ))}

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
                    <span>R$ {valorTotal.toFixed(2)}</span>
                </div>
                <div>
                    <h2> Saldo: </h2>
                    <span> R$ {saldo.toFixed(2)} </span>
                </div>
                <div>
                    <h2> Saldo Total: </h2>
                    <span> R$ {total.toFixed(2)} </span>
                </div>
            </TotalContainer>
            <Button
                onClick={() => {
                    comprar();
                    setOpenSnackbar(true);
                }}
                disabled={quantidadeCarrinho === 0 || total < 0}
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
        </Container>
    )


}