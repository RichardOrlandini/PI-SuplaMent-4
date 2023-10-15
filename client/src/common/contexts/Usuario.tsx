import { ReactNode, createContext, useState, FC } from 'react';

interface UsuarioContextProps {
  nome: string;
  setNome: (nome: string) => void;
  saldo: number;
  setSaldo: (saldo: number) => void;
}

export const UsuarioContext = createContext<UsuarioContextProps>({} as UsuarioContextProps);
UsuarioContext.displayName = "Usuário";

interface UsuarioProviderProps {
  children: ReactNode;
}

export const UsuarioProvider: FC<UsuarioProviderProps> = ({ children }) => {
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
