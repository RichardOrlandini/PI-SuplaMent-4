import { IProduto } from "./IProduto";

export default interface ICategoria {
  id: number;
  nome: string;
  produtos: IProduto[];
}