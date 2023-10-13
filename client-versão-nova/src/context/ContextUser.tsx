import { IUser } from 'interfaces/IUser';
import { ReactNode, createContext, useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';


const userInitial: IUser = {
    nome: '',
    role: '',
    email: '',
    senha: '',
}

export const ContextUser = createContext({
    user: userInitial,
    errors: {},
    setRole: () => null,
    setNome: () => null,
    setSenha: () => null,
    setEmail: () => null,
    setSenhaConfirmada: () => null,
    handleSubmit: () => null,
});
ContextUser.displayName = "User_Context";

export const useContextUser = () => {
    return useContext(ContextUser);
}

interface Props {
    children: ReactNode;
}
export const RegisterUserProvider = ({children}: Props) => {

    const navigate = useNavigate();

    const [user, setUser] = useState<IUser>(userInitial);
    const [role, setRole] = useState<string>('');
    const [nome, setNome] = useState<string>('');
    const [email, setEmail] = useState<string>('');
    const [senha, setSenha] = useState<string>('');
    const [senhaConfirmada, setSenhaConfirmada] = useState<string>('');


    const handleSubmit = () => {
        if (user.senha.length < 8) {
            return
        }

        if (user.senha !== senhaConfirmada) {
            return
        }
        navigate('/cadastro/concluido')
    }

    const contexto = {
        user,
        setRole,
        setNome,
        setEmail,
        setSenha,
        setSenhaConfirmada,
        handleSubmit,
    }

    return (
        <ContextUser.Provider value={contexto} >
            {children}
        </ContextUser.Provider>
    )


}