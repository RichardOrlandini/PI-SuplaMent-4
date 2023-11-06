
import { RiShutDownLine } from 'react-icons/ri';
import { Profile, Logout } from "./styled";
import { Outlet, Link, useNavigate } from 'react-router-dom';
import avatarPlaceholder from "../../assets/avatarPlaceholder.svg";
import autenticaStore from 'common/stores/authentica.store';
import logo from '../../assets/logo.png';
import BotaoNavegacao from "components/BotaoNavegacao"
import styled from "styled-components";
import usuario from '../../assets/usuario.svg';
import ShoppingCartIcon from '@material-ui/icons/ShoppingCart';
import IconButton from '@material-ui/core/IconButton';
import Badge from '@material-ui/core/Badge';
import { Role } from 'constants/url';

import './styles.css';
import { useCarrinhoContext } from 'common/contexts/Carrinho';

export function Header() {
    const { quantidadeCarrinho } = useCarrinhoContext();

    const usuarioContext = autenticaStore.user;
    const isAuthenticated = autenticaStore.isAuthenticated;
    let client: boolean = true;

    if (isAuthenticated) {
        usuarioContext.role === Role.ADMIN
        client = false;
    }

    const navigate = useNavigate();


    function handleSignOut() {
        alert("Sessão terminada!");
        autenticaStore.logout();
        navigate("/");
    }

    const avatarUrl = usuarioContext?.avatar ? `../../../public/images/${usuarioContext.avatar}` : avatarPlaceholder;

    return (
        <>
            <nav className="ab-navbar">

                <h1 className="logo">
                    <Link to="/">
                        <img className="logo" src={logo} width={50} alt="Logo da AluraBooks" />
                    </Link>
                </h1>



                <ul className="acoes">
                    {

                        client && (
                            <>

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


                                <li>
                                    <IconButton
                                        onClick={() => navigate('/app/compra')}
                                        disabled={quantidadeCarrinho === 0}>
                                        <Badge
                                            badgeContent={quantidadeCarrinho}
                                            color="primary"
                                        >
                                            <ShoppingCartIcon />
                                        </Badge>
                                    </IconButton>
                                </li>

                            </>
                        )
                    }

                    {!isAuthenticated && (<>
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
                                onClick={() => navigate("/cadastro")}
                            />
                        </li>

                        <li>
                            <BotaoNavegacao
                                texto="ADMIN"
                                textoAltSrc="Icone representando um usuário"
                                imagemSrc={usuario}
                                onClick={() => navigate("/adm/login")}
                            />
                        </li>

                    </>)}
                    {isAuthenticated &&
                        <>


                            <li>
                                <Profile to="/profile">
                                    <img
                                        src={avatarUrl}
                                        alt={usuarioContext.nome}
                                    />

                                    <div>
                                        <span>Bem vindo</span>
                                        <strong>{usuarioContext?.nome}</strong>
                                    </div>
                                </Profile>
                            </li>

                            <li>
                                <Logout onClick={handleSignOut} >
                                    <RiShutDownLine />
                                </Logout>
                            </li>
                        </>
                    }




                </ul>

            </nav>
        </>

    );
}