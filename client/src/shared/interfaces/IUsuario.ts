export interface IUsuario {
    id: number;
    ativo: boolean;
    nome: string;
    email: string;
    senha: string;
    role: string;
    cpf: string;
    telefone?: string;
    endereco?: IEndereco;
    avatar? : string;
}

export interface IEndereco {
    id: number;
    complemento: string;
    numero: string;
    logradouro: string;
    bairro: string;
    cep: string;
}