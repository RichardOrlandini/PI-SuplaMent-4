import { BrowserRouter } from 'react-router-dom';
import { useAuth } from '../hooks/auth';

import { AdmRoutes } from './adm.routes';
import { AppRoutes } from './app.routes';
import { AuthRoutes } from './auth.routes';

export function Routes() {
    const  context = useAuth(); 
    const usuario = context?.data?.user;
    return (
        <BrowserRouter>
            {   
                usuario?.role === "ADMIN" ? <AdmRoutes/> : (usuario?.role === undefined ? <AuthRoutes/> : <AppRoutes/>)
            }
        </BrowserRouter>
    )
}
