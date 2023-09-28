import { createContext, useContext, useEffect, useState } from 'react';
import { usePagamento } from './Pagamento';
import { UsuarioContext } from './Usuario';
import { AuthProviderProps } from 'shared/interfaces/IAuthContext';

interface CarrinhoItem {
  nome: string
  imagem?: string;
  id: number;
  quantidade: number;
  valor: number;
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

  const mudarQuantidade = (id: number, quantidade: number) => carrinho.map(item => {
    if (item.id === id) item.quantidade += quantidade;
    return item;
  });

  const adicionarProduto = (novoProduto: CarrinhoItem) => {
    const temOProduto = carrinho.some(item => item.id === novoProduto.id);
    let novoCarrinho = [...carrinho];
    if (!temOProduto) {
      novoProduto.quantidade = 1;
      novoCarrinho.push(novoProduto);
      return setCarrinho(novoCarrinho);
    }
    novoCarrinho = mudarQuantidade(novoProduto.id, 1);
    setCarrinho(novoCarrinho);
  };

  const removerProduto = (id: number) => {
    const produto = carrinho.find(item => item.id === id);
    const ultimo = produto?.quantidade === 1;
    let novoCarrinho;
    if (ultimo) {
      novoCarrinho = carrinho.filter(item => item.id !== id);
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
      novaQuantidade: contador.novaQuantidade + novoItem.quantidade,
      novoTotal: contador.novoTotal + (novoItem.valor * novoItem.quantidade)
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

  const mudarQuantidade = (id: number, quantidade: number) => carrinho.map(item => {
    if (item.id === id) item.quantidade += quantidade;
    return item;
  });

  const adicionarProduto = (novoProduto: CarrinhoItem) => {
    const temOProduto = carrinho.some(item => item.id === novoProduto.id);
    let novoCarrinho = [...carrinho];
    if (!temOProduto) {
      novoProduto.quantidade = 1;
      novoCarrinho.push(novoProduto);
      return setCarrinho(novoCarrinho);
    }
    novoCarrinho = mudarQuantidade(novoProduto.id, 1);
    setCarrinho(novoCarrinho);
  };

  const removerProduto = (id: number) => {
    const produto = carrinho.find(item => item.id === id);
    const ultimo = produto?.quantidade === 1;
    let novoCarrinho;
    if (ultimo) {
      novoCarrinho = carrinho.filter(item => item.id !== id);
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
      novaQuantidade: contador.novaQuantidade + novoItem.quantidade,
      novoTotal: contador.novoTotal + (novoItem.valor * novoItem.quantidade)
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
