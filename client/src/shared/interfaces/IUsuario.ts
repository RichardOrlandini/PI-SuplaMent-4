export interface IUsuario {
    id: number;
    message?: string;
    ativo?: boolean;
    nome?: string;
    email: string;
    senha?: string;
    role: string;
}

export interface ICliente {
    id?: number;
    ativo?: boolean;
    email: string,
    nome: string,
    senha: string,
    endereco: IEndereco,
    cpf: string;
    telefone?: string;
    avatar? : string;
}


export interface IEndereco {
    id?: number,
    cep: string,
    rua: string,
    numero: string,
    complemento: string,
    estado: string
}