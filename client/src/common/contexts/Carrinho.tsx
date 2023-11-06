import { createContext, useContext, useEffect, useState } from 'react';
import { usePagamento } from './Pagamento';
import { UsuarioContext } from './Usuario';
import { AuthProviderProps } from 'shared/interfaces/IAuthContext';
import { IProduto } from 'shared/interfaces/IProduto';

interface CarrinhoItem {
  produto: IProduto;
  qtd: number;
}


interface CarrinhoContextProps {
  carrinho: CarrinhoItem[];
  setCarrinho: (carrinho: CarrinhoItem[]) => void;
  quantidadeCarrinho: number;
  setQuantidadeCarrinho: (quantidadeCarrinho: number) => void;
  valorTotal: number;
  setValorTotal: (valorTotal: number) => void;
}

const CarrinhoContext = createContext<CarrinhoContextProps>({} as CarrinhoContextProps);
CarrinhoContext.displayName = "Carrinho";

export function CarrinhoProvider(  {children} : AuthProviderProps){
  const [carrinho, setCarrinho] = useState<CarrinhoItem[]>([]);
  const [quantidadeCarrinho, setQuantidadeCarrinho] = useState<number>(0);
  const [valorTotal, setValorTotal] = useState<number>(0);

  const { saldo, setSaldo } = useContext(UsuarioContext);
  const { formaPagamento } = usePagamento();
  const mudarQuantidade = (id: number, qtd: number) => carrinho.map(item => {
    if (item.produto.id === id) item.qtd += qtd;
    return item;
  });

  const adicionarProduto = (novoProduto: IProduto) => {
    const temOProduto = carrinho.some(item => item.produto.id === novoProduto.id);
    let novoCarrinho = [...carrinho];
    if (!temOProduto) {
      novoCarrinho.push({ produto: novoProduto, qtd: 1 });
      return setCarrinho(novoCarrinho);
    }
    novoCarrinho = mudarQuantidade(novoProduto.id, 1);
    setCarrinho(novoCarrinho);
  };

  const removerProduto = (id: number) => {
    const produto = carrinho.find(item => item.produto.id === id);
    const ultimo = produto?.qtd === 1;
    let novoCarrinho = [...carrinho];
    if (ultimo) {
      novoCarrinho = novoCarrinho.filter(item => item.produto.id !== id);
      return setCarrinho(novoCarrinho);
    }
    novoCarrinho = mudarQuantidade(id, -1);
    setCarrinho(novoCarrinho);
  };

  const comprar = () => {
    setCarrinho([]);
    setSaldo(saldo - valorTotal);
  };

  useEffect(() => {
    let { novaQuantidade, novoTotal } = carrinho.reduce((contador, novoItem) => ({
      novaQuantidade: contador.novaQuantidade + novoItem.qtd,
      novoTotal: contador.novoTotal + (novoItem.valor * novoItem.qtd)
    }), { novaQuantidade: 0, novoTotal: 0 });
    setQuantidadeCarrinho(novaQuantidade);
    setValorTotal(novoTotal * formaPagamento.juros);
  }, [carrinho, formaPagamento, setQuantidadeCarrinho, setValorTotal]);

  return (
    <CarrinhoContext.Provider
      value={{
        carrinho,
        setCarrinho,
        quantidadeCarrinho,
        setQuantidadeCarrinho,
        valorTotal,
        setValorTotal
      }}
    >
      {children}
    </CarrinhoContext.Provider>
  );
};

export const useCarrinhoContext = () => {
  const {
    carrinho,
    setCarrinho,
    quantidadeCarrinho,
    setQuantidadeCarrinho,
    valorTotal,
    setValorTotal
  } = useContext(CarrinhoContext);

  const { saldo, setSaldo } = useContext(UsuarioContext);
  const { formaPagamento } = usePagamento();

  const mudarQuantidade = (id: number, qtd: number) => carrinho.map(item => {
    if (item.produto.id === id) item.qtd += qtd;
    return item;
  });
  

  const adicionarProduto = (novoProduto: IProduto) => {
    const temOProduto = carrinho.some(item => item.produto.id === novoProduto.id);
    let novoCarrinho = [...carrinho];
    if (!temOProduto) {
      novoCarrinho.push({ produto: novoProduto, qtd: 1 });
      return setCarrinho(novoCarrinho);
    }
    novoCarrinho = novoCarrinho.map(item => {
      if (item.produto.id === novoProduto.id) item.qtd += 1;
      return item;
    });
    setCarrinho(novoCarrinho);
  };

  const removerProduto = (id: number) => {
    const produto = carrinho.find(item => item.produto.id === id);
    const ultimo = produto?.qtd === 1;
    let novoCarrinho = [...carrinho]; // Inicialize novoCarrinho aqui
    if (ultimo) {
      novoCarrinho = novoCarrinho.filter(item => item.produto.id !== id);
      return setCarrinho(novoCarrinho);
    }
    novoCarrinho = novoCarrinho.map(item => {
      if (item.produto.id === id) item.qtd -= 1;
      return item;
    });
    setCarrinho(novoCarrinho);
  };
  

  const comprar = () => {
    setCarrinho([]);
    setSaldo(saldo - valorTotal);
  };

  useEffect(() => {
    let { novaQuantidade, novoTotal } = carrinho.reduce((contador, novoItem) => ({
      novaQuantidade: contador.novaQuantidade + novoItem.qtd,
      novoTotal: contador.novoTotal + (parseFloat(novoItem.produto.valor) * novoItem.qtd)
    }), { novaQuantidade: 0, novoTotal: 0 });
    setQuantidadeCarrinho(novaQuantidade);
    setValorTotal(novoTotal * formaPagamento.juros);
  }, [carrinho, formaPagamento, setQuantidadeCarrinho, setValorTotal]);

  return {
    carrinho,
    adicionarProduto,
    removerProduto,
    quantidadeCarrinho,
    valorTotal,
    comprar
  };
};
