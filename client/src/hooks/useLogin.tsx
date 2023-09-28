import axios from "axios";

export interface SignInProps {
    email: string;
    senha: string;
}

export async function signIn({ email, senha }: SignInProps): Promise<SignInProps | null> {
    try {
        //BUG DE RELOAD COM TOKEN NO HEADER DA api, POR ISSO FOI OPTADO POR USAR OUTRA INSTÂNCIA DO AXIOS.
        const resp = await axios.post("http://localhost:8000/api/login", { email, senha });
        const { user, token } = resp.data;

        localStorage.setItem("@suplament:user", JSON.stringify(user));
        localStorage.setItem("@suplament:token", token.token);

        return { email, senha }
    } catch (error: any) {
        if (error.response) {
            alert(error.response.data.message);
        } else {
            alert("Não foi possível entrar.");
        }
        return null;
    }
}

export function signOut(): void {
    localStorage.removeItem("@suplament:token");
    localStorage.removeItem("@suplament:user");
}