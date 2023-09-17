import { BrowserRouter } from 'react-router-dom';
import { useAuth } from '../hooks/auth';

import { AdmRoutes } from './adm.routes';
import { AppRoutes } from './app.routes';
import { AuthRoutes } from './auth.routes';

export function Routes() {
    const  context = useAuth(); //poderiamos peg
    const usuario = context?.data?.user
    return (
        <BrowserRouter>
            {   
                usuario?.role === "admin" ? <AdmRoutes/> : (usuario?.role !== "admin" ? <AppRoutes/> : <AuthRoutes/>)
            }
        </BrowserRouter>
    )
}
