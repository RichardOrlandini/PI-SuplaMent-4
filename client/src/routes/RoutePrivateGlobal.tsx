import autenticaStore from "common/stores/authentica.store";
import { Navigate, Outlet } from "react-router-dom";

const RoutePrivateGlobal = () => {
    const {isAuthenticated } = autenticaStore;

    return (
        isAuthenticated ? <Outlet /> : <Navigate to="/" />
    )
}

export default RoutePrivateGlobal;