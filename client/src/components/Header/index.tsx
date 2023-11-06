
import { RiShutDownLine } from 'react-icons/ri';
import { useAuth } from '../../hooks/auth';
import { Container, Profile, Logout } from "./styled";
import { Outlet, useNavigate } from 'react-router-dom';
import avatarPlaceholder from "../../assets/avatar_placeholder.svg";

export function Header() {

    const context = useAuth();
    const usuario = context?.data?.user;
    const navigation = useNavigate();

    function handleSignOut() {
        alert("Sessão terminada!");
        context?.signOut();
        navigation("/");
    }

    const avatarUrl = avatarPlaceholder;

    return (
        <>
            <Container>
                <Profile to="/profile">
                    <img
                        src={avatarUrl}
                        alt={usuario?.nome}
                    />

                    <div>
                        <span>Bem vindo</span>
                        <strong>{usuario?.nome}</strong>
                    </div>
                </Profile>

<<<<<<< Updated upstream
                <Logout onClick={handleSignOut} >
                    <RiShutDownLine />
                </Logout>
            </Container>
=======


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
>>>>>>> Stashed changes
        </>

    );
}