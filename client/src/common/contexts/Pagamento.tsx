import { ReactNode, createContext, useContext, useState } from 'react';
import { AuthProviderProps } from 'shared/interfaces/IAuthContext';

interface Pagamento {
  name: string;
  juros: number;
  id: number;
}

interface PagamentoContextProps {
  formaPagamento: Pagamento;
  setFormaPagamento: (formaPagamento: Pagamento) => void;
  tiposPagamento: Pagamento[];
}

const PagamentoContext = createContext<PagamentoContextProps>({} as PagamentoContextProps);
PagamentoContext.displayName = "Pagamento";

export function PagamentoProvider(  {children} : AuthProviderProps){
  const tiposPagamento: Pagamento[] = [{
    name: "Boleto",
    juros: 1,
    id: 1
  }, {
    name: "Cartão de crédito",
    juros: 1.3,
    id: 2
  }, {
    name: "PIX",
    juros: 1,
    id: 3
  }, {
    name: "Crediário",
    juros: 1.5,
    id: 4
  }];
  const [formaPagamento, setFormaPagamento] = useState<Pagamento>(tiposPagamento[0]);

  return (
    <PagamentoContext.Provider value={{
      formaPagamento,
      setFormaPagamento,
      tiposPagamento
    }}>
      {children}
    </PagamentoContext.Provider>
  );
};

export const usePagamento = () => {
  const {
    formaPagamento,
    setFormaPagamento,
    tiposPagamento
  } = useContext(PagamentoContext);

  const mudarFormaPagamento = (id: number) => {
    const formaNova = tiposPagamento.find(pagamento => pagamento.id === id);
    if (formaNova) {
      setFormaPagamento(formaNova);
    }
  };

  return {
    formaPagamento,
    mudarFormaPagamento,
    tiposPagamento
  };
};
