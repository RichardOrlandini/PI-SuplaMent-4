export interface IUsuario {
    id: number;
    active: boolean;
    nome: string;
    email: string;
    senha: string;
    role: string;
    telefone?: string;
    endereco?: IEndereco;
}

export interface IEndereco {
    id: number;
    complemento: string;
    numero: string;
    logradouro: string;
    bairro: string;
    cep: string;
}