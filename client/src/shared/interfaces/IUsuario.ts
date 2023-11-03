export interface IUsuario {
    id: number;
    message?: string;
    active?: boolean;
    nome?: string;
    email: string;
    senha?: string;
    role: string;
    avatar?: string;
}

export interface IUsuarioContext {
    id: number | null;
    message?: string;
    nome?: string;
    email: string;
    role: string;
    token?: string;
    avatar?: string;
}

export interface CreateDataAuthApi {
    status: number,
    newUser: {
        id: number;
        email: string;
        password: string;
    }
}

export interface LoginDataAuthAPi {
    status: number;
    accessToken: string;
    message?: string;
}

export interface ILogin {
    email: string;
    senha: string;
    token?: string;
}

export interface ResponseLoginApiSucess {
    id: number;
    nome: string;
    email: string;
    role: string;
}


export interface ICliente {
    id?: number;
    ativo?: boolean;
    email: string,
    nome: string,
    senha: string,
    enderecos: IEndereco[],
    cpf: string;
    telefone?: string;
    avatar?: string;
}


export interface IEndereco {
    id?: number,
    cep: string,
    rua: string,
    numero: string,
    estado: string
    complemento: string,
    principal: boolean,
}



