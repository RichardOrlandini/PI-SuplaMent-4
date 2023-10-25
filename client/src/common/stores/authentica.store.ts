import { makeObservable, observable, action } from "mobx";

interface IUser {
    id: number | null,
    email: string,
    token: string,
    role: string,

}

class AutenticaStore {

    isAuthenticated = false;
    user: IUser = {id : null, email : "", token: "", role: ""}

    constructor() {
        makeObservable(this, {
            isAuthenticated: observable,
            user: observable,
            login: action,
            logout: action
        });
    }

    login({id, email, token, role} : IUser) {
        this.isAuthenticated = true;
        this.user = {id,email, token, role};
    }

    logout() {
        this.isAuthenticated = false;
        this.user = {id : null, email: "", token: "", role: ""}
    }
}

const autenticaStore = new AutenticaStore();
export default autenticaStore;