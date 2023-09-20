
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

                <Logout onClick={handleSignOut} >
                    <RiShutDownLine />
                </Logout>
            </Container>
        </>

    );
}