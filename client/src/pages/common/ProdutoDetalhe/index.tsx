
import { Header } from 'components/Header';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { api } from 'services/api';
import { IProduto, IProdutoDetalhe } from 'shared/interfaces/IProduto';

import { Typography, Paper, Grid, CardMedia, Box, Button } from '@material-ui/core';
import Produto from 'components/Produto';
import { useCarrinhoContext } from 'common/contexts/Carrinho';
const ProdutoDetalhe = () => {
    const { id } = useParams();

    const [produto, setProduto] = useState<IProdutoDetalhe | undefined>(undefined);


    const { adicionarProduto } = useCarrinhoContext();


    const handleAdicionarAoCarrinho = (produto: IProdutoDetalhe) => {
        adicionarProduto({
          nome: produto.nome,
          nomeImagem: produto.nomeImagem,
          id: produto.id,
          valor: produto.valor,
          qtd: produto.qtd
        });
      };


    useEffect(() => {

        const fetchproduto = async () => {
            try {
                const response = await api.get(`/produto/${id}`);
                setProduto(response.data);
            } catch (error) {
                console.error('Erro ao buscar detalhes do produto:', error);
            }
        };

        fetchproduto();
    }, [id]);



    return (
        <div>
            {produto ? (
                <Paper elevation={3} style={{ padding: '20px', margin: '20px', maxWidth: '1200px', display: 'flex' }}>
                    <Grid container spacing={3}>
                        <Grid item xs={12} md={6}>
                            <CardMedia
                                component="img"
                                alt={produto.nome}
                                height="100%"
                                image={`../../../../public/images/${produto.nomeImagem}`}
                            />
                        </Grid>
                        <Grid item xs={12} md={6}>
                            <Typography variant="h4" gutterBottom>
                                {produto.nome}
                            </Typography>
                            <Typography variant="subtitle1" gutterBottom>
                                Categoria: {produto.categoria.descricao}
                            </Typography>
                            <Typography variant="subtitle1" gutterBottom>
                                Fornecedor: {produto.fornecedor.nome}
                            </Typography>
                            <Typography variant="subtitle1" gutterBottom>
                                Quantidade disponível: {produto.qtd}
                            </Typography>
                            <Typography variant="subtitle1" gutterBottom>
                                Data de Inserção: {produto.insertion_date}
                            </Typography>

                        </Grid>
                    </Grid>
                    <Box mt={2}>
                        <Produto {...produto} nomeImagem={`../../../../public/images/${produto.nomeImagem}`} />

                        <Button variant="contained" color="primary" onClick={() => handleAdicionarAoCarrinho(produto)}>
                            Comprar
                        </Button>

                    </Box>
                </Paper>
            ) : (
                <p>Carregando...</p>
            )}
        </div>
    );
};

export default ProdutoDetalhe;

