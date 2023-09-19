

export interface IPedido {

    id: number;
    insertionDate: Date;
    active: boolean;
    client_id: number;
    produto_id: number;
    total: number;
    dtEntrega: Date;
}