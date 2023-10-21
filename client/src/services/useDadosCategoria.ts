
import useFetch from "./useFetch";
import ICategoria from "shared/interfaces/ICategoria";

const useDadosConsulta = () => {
    return useFetch<ICategoria[]>({ url: 'categoria' });
}

export default useDadosConsulta;