import { Container } from './styles';

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

  const itemNoCarrinho = carrinho.find(item => item.id === produto.id);
  return (
    <Container>
      <div>
        <img
          src={`/assets/${produto.imagem}.png`}
          alt={`foto de ${produto.nome}`}
        />
        <p>
          {produto.nome} - R$ {Number(produto.valor).toFixed(2)} <span>Kg</span>
        </p>
      </div>
      <div>
        <IconButton
          onClick={() => removerProduto(produto.id)}
          disabled={!itemNoCarrinho || itemNoCarrinho.quantidade === 0}
          color="secondary"
        >
          <RemoveIcon />
        </IconButton>
        {itemNoCarrinho?.quantidade || 0}
        <IconButton
          disabled={valorTotal > saldo}
          onClick={() => adicionarProduto({
            nome: produto.nome,
            imagem: produto.imagem,
            id: produto.id,
            valor: Number(produto.valor),
            quantidade: Number(produto.qtd)
          })}
          color="primary"
        >
          <AddIcon />
        </IconButton>
      </div>
    </Container>
  );
};

export default memo(Produto);
