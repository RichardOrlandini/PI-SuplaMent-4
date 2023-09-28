import { createContext, useState } from 'react';

interface UsuarioContextProps {
  nome: string;
  setNome: (nome: string) => void;
  saldo: number;
  setSaldo: (saldo: number) => void;
}

export const UsuarioContext = createContext<UsuarioContextProps>({} as UsuarioContextProps);
UsuarioContext.displayName = "Usuário";

const UsuarioProvider: React.FC = ({ children }: any) => {
  const [nome, setNome] = useState('');
  const [saldo, setSaldo] = useState(0);
  return (
    <UsuarioContext.Provider
      value={{
        nome,
        setNome,
        saldo,
        setSaldo
      }}
    >
      {children}
    </UsuarioContext.Provider>
  );
};

export default UsuarioProvider;
