import { useState } from "react"
import {
  Typography, Box
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


export function HomeDeslogado() {

  const [busca, setBusca] = useState("");
  const token = localStorage.getItem("@suplamente:token");
  
  return (

    <>
      <HeaderDeslogado/>
      <section className="home">
        <Banner subtitulo="Encontre em nossa estante o que precisa para seu desenvolvimento!" titulo="Já sabe por onde começar?">
          <form className="buscar">
            <AbCampoTexto
              placeholder="Qual será seu próximo produto?"
              value={busca}
              onChange={setBusca}
              darkmode={true}
              placeholderAlign="center"
            />
          </form>
        </Banner>

        <Box sx={{ display: 'flex', marginTop: 10, flexDirection: 'row', justifyContent: 'space-between' }}>
          <Box sx={{ width: '55%' }}>
            <Typography component="h1" className="titulo-interno">ÚLTIMOS LANÇAMENTOS</Typography>
            <ProdutosDestaque produtos={lancamentos} />
          </Box>

          <Box sx={{ width: '55%' }}>
            <Typography component="h1" className="titulo-interno">MAIS VENDIDOS</Typography>
            <ProdutosDestaque produtos={maisVendidos} />
          </Box>
        </Box>



        <TagsCategorias />
        <Newsletter />
        <Footer />
      </section>
    </>

  )
}