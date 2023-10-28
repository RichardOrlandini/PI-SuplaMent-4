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

    login(user : IUsuarioContext) {
        this.isAuthenticated = true;
        this.user = user;
    }

    logout() {
        this.isAuthenticated = false;
        this.user = {id : null, email: "", token: "", role: "",  nome: "", message: "", avatar: ""}
    }
}

const autenticaStore = new AutenticaStore();
export default autenticaStore;