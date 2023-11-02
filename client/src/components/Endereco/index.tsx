import CampoDigitacao from "components/CampoDigitacao";
import { IEndereco } from "shared/interfaces/IUsuario";

export function Endereco(endereco: IEndereco) {
    return (
      <div>
        <p>Rua: {endereco.rua}</p>
        <p>Número: {endereco.numero}</p>
        <p>Complemento: {endereco.complemento}</p>
        <p>Estado: {endereco.estado}</p>
        <p>Principal ? : {endereco.principal ? "Sim" : "Não"}</p>
        <p>---------------------------------</p>
      </div>
    );
  }
  