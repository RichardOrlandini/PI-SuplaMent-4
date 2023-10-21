import { makeObservable, observable, action } from "mobx";

interface IUser {
    email: string,
    token: string
}

class AutenticaStore {

    isAuthenticated = false;
    user: IUser = {email : "", token: ""}

    constructor() {
        makeObservable(this, {
            isAuthenticated: observable,
            user: observable,
            login: action,
            logout: action
        });
    }

    login({email, token} : IUser) {
        this.isAuthenticated = true;
        this.user = {email, token};
    }

    logout() {
        this.isAuthenticated = false;
        this.user = {email: "", token: ""}
    }
}

const autenticaStore = new AutenticaStore();
export default autenticaStore;