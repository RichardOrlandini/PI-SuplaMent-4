import autenticaStore from "common/stores/authentica.store";
import { Navigate, Outlet } from "react-router-dom";

const RoutePrivateAdmin = () => {
    const {isAuthenticated, user } = autenticaStore;

    return (
        isAuthenticated &&  user.role === "ADMIN" ? <Outlet /> : <Navigate to="/login" />
    )
}

export default RoutePrivateAdmin;