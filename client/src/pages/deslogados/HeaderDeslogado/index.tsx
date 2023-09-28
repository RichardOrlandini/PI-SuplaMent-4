import { useState } from "react"
import { Link, useNavigate } from "react-router-dom"
import logo from 'assets/logo.png';
import usuario from 'assets/usuario.svg';
import ShoppingCartIcon from '@material-ui/icons/ShoppingCart';
import IconButton from '@material-ui/core/IconButton';
import Badge from '@material-ui/core/Badge';

import './styles.css'
import BotaoNavegacao from "components/BotaoNavegacao"
import { useCarrinhoContext } from "common/contexts/Carrinho";

export const HeaderDeslogado = () => {
    const { quantidadeCarrinho } = useCarrinhoContext();
    let navigate = useNavigate();

    const token = sessionStorage.getItem('token')

    const [usuarioEstaLogado, setUsuarioEstaLogado] = useState<boolean>(token != null)


    return (<nav className="ab-navbar">
        <h1 className="logo">
            <Link to="/">
                <img className="logo" src={logo} width={50} alt="Logo da AluraBooks" />
            </Link>
        </h1>



        <ul className="navegacao">
            <li>
                <a href="#!">Categorias</a>
                <ul className="submenu">
                    <li>
                        <Link to="/">
                            x
                        </Link>
                    </li>
                    <li>
                        <Link to="/">
                            x
                        </Link>
                    </li>
                    <li>
                        <Link to="/">
                            x
                        </Link>
                    </li>
                    <li>
                        <Link to="/">
                            x
                        </Link>
                    </li>
                    <li>
                        <Link to="/">
                            x
                        </Link>
                    </li>
                </ul>
            </li>
        </ul>
        <ul className="acoes">
            {!usuarioEstaLogado && (<>
                <li>
                    <BotaoNavegacao
                        texto="Login"
                        textoAltSrc="Icone representando um usuário"
                        imagemSrc={usuario}
                        onClick={() => navigate("/login")}
                    />
                </li>
                <li>
                    <BotaoNavegacao
                        texto="Cadastrar-se"
                        textoAltSrc="Icone representando um usuário"
                        imagemSrc={usuario}
                        onClick={() => navigate("/register")}
                    />
                </li>
            </>)}
            {usuarioEstaLogado &&
                <>
                    <li>
                        <Link to="/">Minha conta</Link>
                    </li>
                    <li>
                        <BotaoNavegacao
                            texto="Logout"
                            textoAltSrc="Icone representando um usuário"
                            imagemSrc={usuario}
                        />
                    </li>
                </>
            }
        </ul>

        


        <IconButton
            onClick={() => navigate('/carrinho')}
            disabled={quantidadeCarrinho === 0}>
            <Badge
                badgeContent={quantidadeCarrinho}
                color="primary"
            >
                <ShoppingCartIcon />
            </Badge>
        </IconButton>
    </nav>)
}
