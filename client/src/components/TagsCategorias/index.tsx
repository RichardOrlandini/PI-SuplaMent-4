import { AbTag } from "ds-alurabooks"

import './styles.css'

export function TagsCategorias() {

    const tags = [
        'Suplemento',
        'Barra serial',
        'Camisetas',
        'Shorts',
    ]

    return (<section className="TagsCategorias">
        <h4>CATEGORIAS MAIS BUSCADAS</h4>
        <div className="container">
            {tags.map(tag => <AbTag texto={tag} key={tag}/>)}
        </div>
    </section>)
}
