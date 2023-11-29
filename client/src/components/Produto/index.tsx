import { Card, CardContent, CardMedia, Typography } from '@material-ui/core';
import { IconButton } from '@material-ui/core';
import AddIcon from '@material-ui/icons/Add';
import RemoveIcon from '@material-ui/icons/Remove';
import { memo, useContext } from 'react';
import { useCarrinhoContext } from 'common/contexts/Carrinho';
import { UsuarioContext } from 'common/contexts/Usuario';
import { IProduto } from 'shared/interfaces/IProduto';
import { useNavigate } from 'react-router-dom';

const Produto: React.FC<IProduto> = (produto: IProduto) => {
  const { carrinho, adicionarProduto, removerProduto, valorTotal } = useCarrinhoContext();
  const { saldo } = useContext(UsuarioContext);
  const navigate = useNavigate();
  const pathImages = "../../../public/images";
  const itemNoCarrinho = carrinho.find(item => item.produto.id === produto.id);


  const handleDetailsProdutos = (id : number) => {
    navigate(`/${id}`);
  }

  return (
    <Card>
      <CardMedia
        component="img"
        onClick={() => handleDetailsProdutos(produto.id)}
        image={`${pathImages}/${produto.nomeImagem}`}
        alt={`foto de ${produto.nome}`}
        style={{ height: '200px', width: '200px', objectFit: 'cover' }} // define um tamanho fixo para todas as imagens
      />
      <CardContent>
        <Typography variant="h6" component="div">
          {produto.nome} - R$ {Number(produto.valor).toFixed(2)}
        </Typography>
        <Typography variant="body2" color="primary">
          Quantidade: {produto?.qtd || 0}
        </Typography>
        <IconButton
          onClick={() => removerProduto(produto.id)}
          disabled={!itemNoCarrinho || itemNoCarrinho.qtd === 0}
          color="secondary"
        >
          <RemoveIcon />
        </IconButton>
        <IconButton
          onClick={() => adicionarProduto({
            nome: produto.nome,
            nomeImagem: produto.nomeImagem,
            id: produto.id,
            valor: produto.valor,
            qtd: produto.qtd
          })}
          color="primary"
        >
          <AddIcon />
        </IconButton>
      </CardContent>
    </Card>
  );
};

export default memo(Produto);
