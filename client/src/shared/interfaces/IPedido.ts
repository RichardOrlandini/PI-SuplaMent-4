import { IProduto } from "./IProduto";


export interface IPedido {

    id: number;
    insertionDate: Date;
    active: boolean;
    client_id: number;
    produtos: IProduto[];
    total: number;
    dtEntrega: Date;
}