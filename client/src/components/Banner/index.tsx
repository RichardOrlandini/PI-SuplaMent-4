import { ReactNode } from "react"
import './styles.css'
import fundo from '../../assets/imgs/banner.jpg';

interface BannerProps {
    titulo?: string
    subtitulo?: string
    children?: ReactNode
    fundo?: string;
}

export function Banner({ titulo, subtitulo, children, fundo } : BannerProps) {
    return (<section className="banner-principal" style={{ backgroundImage: `url(${fundo})`}}>
        <h2>{titulo}</h2>
        <h3>{subtitulo}</h3>
        {children}
    </section>)
}
