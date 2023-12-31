import { useEffect, useState } from "react"
import {
  Typography, Box, Button, List, ListItem, TextField, Grid
} from "@mui/material"
import { AbCampoTexto } from "ds-alurabooks"
import { Banner } from "../../../components/Banner"
import { ProdutosDestaque } from "../../../components/ProdutoDestaque"
import { IProduto } from "../../../shared/interfaces/IProduto"
import { Newsletter } from "../../../components/Newsletter"
import { Footer } from "../../../components/Footer"
import { TagsCategorias } from "../../../components/TagsCategorias"
import { lancamentos, maisVendidos } from "./moks"
import './styles.css'
import { HeaderDeslogado } from "../HeaderDeslogado"
import { api } from "services/api"
import { IParametrosBusca } from "shared/interfaces/IParametrosBusca"
import { IPaginacao } from "shared/interfaces/IPaginacao"
import { AxiosRequestConfig } from "axios"
import Produto from "components/Produto"

export function HomeDeslogado() {

  const [produtos, setProdutos] = useState<IProduto[]>([]);
  const [proximaPagina, setProximaPagina] = useState('');
  const [paginaAnterior, setPaginaAnterior] = useState('');
  const [busca, setBusca] = useState('');

  const getDados = (url: string, opcoes: AxiosRequestConfig = {}) => {
    
    api.get<IPaginacao<IProduto>>(url, opcoes)
      .then(resp => {
        setProdutos([...resp.data.content]);
        setProximaPagina(resp.data.last ? '' : `/produto/index?page=${resp.data.number + 1}`);
        setPaginaAnterior(resp.data.first ? '' : `/produto/index?page=${resp.data.number - 1}`);
      })
  }

  const buscar = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (!busca) {
      alert("Digite o nome de um produto");
      return;
    }

    const opcoes = {
      params: {

      } as IParametrosBusca
    }

    if (busca) {
      opcoes.params.nome = busca;
    }
    getDados('/produto/busca', opcoes)
  }

  useEffect(() => {
    getDados("/produto/index");
  }, []);

  return (
    <>
      <section className="home">
        <Banner subtitulo="O melhor loja do mundo fitnes!" titulo="Suplament">


          <Box sx={{ display: 'flex', marginTop: 2, flexDirection: 'row', justifyContent: 'space-between' }}>
            <Box sx={{ width: '55%' }}>
              <Typography component="h1" className="titulo-interno">ÚLTIMOS LANÇAMENTOS</Typography>
              <ProdutosDestaque produtos={lancamentos} />
            </Box>

            <Box sx={{ width: '55%' }}>
              <Typography component="h1" className="titulo-interno">MAIS VENDIDOS</Typography>
              <ProdutosDestaque produtos={maisVendidos} />
            </Box>
          </Box>

          <form onSubmit={buscar} className="buscar">
            <input
              className="buscar-input"
              placeholder="Busque um produto"
              value={busca}
              onChange={evento => setBusca(evento.target.value)}
              type="text"
              required
            />
            <button type='submit' className="buscar-button">Buscar</button>
          </form>

        </Banner>

        <Box maxWidth="100%" overflow="auto" marginLeft={10} marginRight={10} marginTop={3}>
          <Grid container spacing={2}>
            {produtos.map(produto => (
              <Grid item xs={12} sm={6} md={2} key={produto.id}>
                <Produto {...produto} />
              </Grid>
            ))}
          </Grid>


        <Box sx={{ marginTop: 2, marginLeft: 50, marginRight: 50 }}>
          <Button
            className="custom-button"
            onClick={() => getDados(paginaAnterior)}
            disabled={!paginaAnterior}
          >
            Página Anterior
          </Button>
          <Button
            className="custom-button"
            onClick={() => getDados(proximaPagina)}
            disabled={!proximaPagina}
          >
            Próxima página
          </Button>
        </Box>
      </Box>


      <TagsCategorias />
      <Newsletter />
    </section >
    </>
  )
}