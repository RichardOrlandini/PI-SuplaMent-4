export interface IProduto {
    id: number;
    active?: boolean;
    nome: string;
    descri?: string;
    valor: string;
    qtd: string;
    nomeImagem?: string;
}


//novo iProduto
export interface IProdutoDetalhe {
    id: number;
    nome: string;
    qtd: string;
    valor: string;
    fornecedor: {
        id: number;
        nome: string;
    };
    categoria: {
        id: number;
        descricao: string;
    };
    nomeImagem?: string;
    insertion_date: string;
}