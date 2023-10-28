
import { RiShutDownLine } from 'react-icons/ri';
import { Container, Profile, Logout } from "./styled";
import { Outlet, useNavigate } from 'react-router-dom';
import avatarPlaceholder from "../../assets/avatarPlaceholder.svg";
import autenticaStore from 'common/stores/authentica.store';

export function Header() {

    
    const usuario = autenticaStore.user;
    const navigation = useNavigate();

    function handleSignOut() {
        alert("Sessão terminada!");
        autenticaStore.logout();
        navigation("/");
    }

    const avatarUrl = avatarPlaceholder;

    return (
        <>
            <Container>
                <Profile to="/profile">
                    <img
                        src={avatarUrl}
                        alt={usuario.nome}
                    />

                    <div>
                        <span>Bem vindo</span>
                        <strong>{usuario?.nome}</strong>
                    </div>
                </Profile>

                <Logout onClick={handleSignOut} >
                    <RiShutDownLine />
                </Logout>

            </Container>
        </>

    );
}