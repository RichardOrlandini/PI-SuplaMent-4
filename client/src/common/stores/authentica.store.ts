import { makeObservable, observable, action } from "mobx";
import { IUsuarioContext } from "shared/interfaces/IUsuario";


class AutenticaStore {

    isAuthenticated = false;
    user: IUsuarioContext = {id : null, email : "", token: "", role: "", nome: "", avatar: ""}

    constructor() {
        makeObservable(this, {
            isAuthenticated: observable,
            user: observable,
            login: action,
            logout: action
        });
    }

    login(usuario : IUsuarioContext) {
        this.isAuthenticated = true;

        this.user = usuario;

        const userToStorage = this.user;
        sessionStorage.setItem("@suplament:usuario", JSON.stringify(userToStorage));
    }

    logout() {
        this.isAuthenticated = false;
        this.user = {id : null, email: "", token: "", role: "",  nome: "", message: "", avatar: ""}
        sessionStorage.removeItem("@suplament:token");
        sessionStorage.removeItem("@suplament:usuario");

    }
}

const autenticaStore = new AutenticaStore();
export default autenticaStore;

// PEGAR USER DA SESSION : const userFromStorage = JSON.parse(sessionStorage.getItem("@suplament:usuario"));
