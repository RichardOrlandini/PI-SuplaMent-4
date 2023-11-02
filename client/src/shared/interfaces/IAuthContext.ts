import { ReactNode } from "react";
import { IUsuario } from "./IUsuario";

export interface IAuthContext {
  data: DataProps | null;
  signIn: (email: string, senha: string) => Promise<void>;
  signOut: () => void;
}

export interface DataProps {
  token: string;
  user: IUsuario;
}

export interface AuthProviderProps {
  children: React.ReactElement;
}