import { AbBotao, AbCard } from "ds-alurabooks"
import { useState } from "react"
import './styles.css'
import { IProduto } from "../../shared/interfaces/IProduto"
interface ProdutosDestaqueProps {
    produtos: IProduto[];
}

export function ProdutosDestaque({ produtos }: ProdutosDestaqueProps) {

    const [selecionado, selecionarProduto] = useState<IProduto>(produtos[0])

    return (<section className="LivrosDestaque">
        <div>
            <ul className="livros">
                {produtos.map(produto => {
                    return (
                        <li
                            key={produto.id}
                            onClick={() => selecionarProduto(produto)}
                            className={selecionado?.nome === produto.nome ? 'selecionado' : ''}
                        >
                            <img src={produto.nomeImagem} alt={`Nome do produto:  ${produto.nome}`} />
                        </li>)
                })}
            </ul>
        </div>

        <AbCard>
            <div className="selecionado-detalhes">
                <header>
                    <h5>Sobre o Produto:</h5>
                </header>
                <h6>{selecionado.nome}</h6>
                <p>{selecionado.descri}</p>
                <footer>
                    <div className="preco">
                        <em>A partir de:</em>
                        <strong>{Intl.NumberFormat('pt-br', { style: 'currency', currency: 'BRL' }).format(Number(selecionado.valor))}</strong>
                    </div>
                    <div>
                        <AbBotao texto="Comprar" />
                    </div>
                </footer>
            </div>
        </AbCard>
    </section>)

}

