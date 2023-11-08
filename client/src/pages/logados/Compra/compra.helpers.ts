import useFetch from "services/useFetch";
import { IEndereco } from "shared/interfaces/IUsuario";

export const useEnderecos = (id: number) => {
    return useFetch<IEndereco[]>({ url: `/${id}/enderecos` });
}



export const useCompra = (id: number) => {
    return useFetch<IEndereco[]>({ url: `/${id}/enderecos` });
}
