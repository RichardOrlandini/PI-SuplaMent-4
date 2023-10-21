
import { IProduto } from "shared/interfaces/IProduto";
import useFetch from "./useFetch";

const useDadosConsulta = () => {
    return useFetch<IProduto[]>({ url: 'produto' });
}

export default useDadosConsulta;