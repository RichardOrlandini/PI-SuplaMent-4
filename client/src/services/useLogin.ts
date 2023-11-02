
import { IProduto } from "shared/interfaces/IProduto";
import useFetch from "./useFetch";
import { IUsuario } from "shared/interfaces/IUsuario";

const useLogin = (email : string) => {
    return useFetch<IUsuario>({ url: `email/${email}` });
}

export default useLogin;