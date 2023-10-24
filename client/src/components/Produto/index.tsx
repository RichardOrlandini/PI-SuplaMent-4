import { Card, CardContent, CardMedia, Typography } from '@material-ui/core';
import { IconButton } from '@material-ui/core';
import AddIcon from '@material-ui/icons/Add';
import RemoveIcon from '@material-ui/icons/Remove';
import { memo, useContext } from 'react';
import { useCarrinhoContext } from 'common/contexts/Carrinho';
import { UsuarioContext } from 'common/contexts/Usuario';
import { IProduto } from 'shared/interfaces/IProduto';

const Produto: React.FC<IProduto> = (produto: IProduto) => {
  const { carrinho, adicionarProduto, removerProduto, valorTotal } = useCarrinhoContext();
  const { saldo } = useContext(UsuarioContext);

 const  pathImages = "../../../public/images";
 
  const itemNoCarrinho = carrinho.find(item => item.id === produto.id);
  console.log(`${pathImages}/${produto.nomeImagem}`);

  return (
    <Card>
      <CardMedia
        component="img"
        image={`${pathImages}/${produto.nomeImagem}`}
        alt={`foto de ${produto.nome}`}
      />
      <CardContent>
        <Typography variant="h6" component="div">
          {produto.nome} - R$ {Number(produto.valor).toFixed(2)}
        </Typography>
        <Typography variant="body2" color="secondary">
          Quantidade: {itemNoCarrinho?.quantidade || 0}
        </Typography>
        <IconButton
          onClick={() => removerProduto(produto.id)}
          disabled={!itemNoCarrinho || itemNoCarrinho.quantidade === 0}
          color="secondary"
        >
          <RemoveIcon />
        </IconButton>
        <IconButton
          onClick={() => adicionarProduto({
            nome: produto.nome,
            imagem: produto.nomeImagem,
            id: produto.id,
            valor: Number(produto.valor),
            quantidade: Number(produto.qtd)
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
