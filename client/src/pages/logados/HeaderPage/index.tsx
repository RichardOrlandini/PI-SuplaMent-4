import { Link, Outlet } from "react-router-dom"
import './header.css';
import { Header } from "../../../components/Header";

export function HeaderPage(){
    
    return (<>

        <Header/>
        <h1 className="AreaLogada__titulo">Minha conta</h1>
        <section className="AreaLogada">
            <div className="menu">
                <ul className="navegacao">
                    <li>
                        <Link to="/pedidos">Pedidos</Link>
                    </li>
                </ul>
            </div>
            <div className="conteudo">
                <Outlet />
            </div>
        </section>
    </>
    )
}