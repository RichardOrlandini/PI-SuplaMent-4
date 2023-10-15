import { IProduto } from "../../../shared/interfaces/IProduto";

import imagem1 from 'assets/imgs/imagem1.jpg';
import imagem2 from 'assets/imgs/imagem2.jpg';
import imagem3 from 'assets/imgs/imagem3.jpg';
import imagem4 from 'assets/imgs/imagem4.jpg';
import imagem5 from 'assets/imgs/imagem5.jpg';
import imagem6 from 'assets/imgs/imagem6.jpg';


export const lancamentos: IProduto[] = [
    {
      id: 1,
      ativo: true,
      nome: "Big Massas",
      descri: "Mais nutritivas",
      valor: "19.99",
      qtd: "10",
      imagem: imagem1,
    },
    {
      id: 2,
      ativo: true,
      nome: "Produto de Lançamento 2",
      descri: "Descrição do Produto de Lançamento 2",
      valor: "29.99",
      qtd: "8",
      imagem: imagem2,
    },
    {
      id: 3,
      ativo: false,
      nome: "Whey ",
      descri: "Whey",
      valor: "9.99",
      qtd: "15",
      imagem: imagem3,
    },
  ];
  
  export const maisVendidos: IProduto[] = [
    {
      id: 4,
      ativo: true,
      nome: "Whey",
      descri: "Whey mais saboroso",
      valor: "39.99",
      qtd: "12",
      imagem: imagem4,
    },
    {
      id: 5,
      ativo: true,
      nome: "Produto Mais Vendido 2",
      descri: "Descrição do Produto Mais Vendido 2",
      valor: "49.99",
      qtd: "20",
      imagem: imagem5,
    },
    {
      id: 6,
      ativo: true,
      nome: "Whey",
      descri: "Descrição do Produto Mais Vendido 3",
      valor: "59.99",
      qtd: "18",
      imagem: imagem6,
    },
  ];