import autenticaStore from "common/stores/authentica.store";
import { Navigate, Outlet } from "react-router-dom";

const RoutePrivateCliente = () => {
    const {isAuthenticated, user } = autenticaStore;

    return (
        isAuthenticated &&  user.role === "CLIENT" ? <Outlet /> : <Navigate to="/" />
    )
}

export default RoutePrivateCliente;