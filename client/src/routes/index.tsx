import { BrowserRouter } from 'react-router-dom';
import { useAuth } from '../hooks/auth';

import { AdmRoutes } from './adm.routes';
import { AppRoutes } from './app.routes';
import { AuthRoutes } from './auth.routes';
import { UsuarioProvider } from 'common/contexts/Usuario';
import { PagamentoProvider } from 'common/contexts/Pagamento';
import { CarrinhoProvider } from 'common/contexts/Carrinho';

export function Routes() {
    const context = useAuth();
    const usuario = context?.data?.user;
    return (
        <BrowserRouter>
            <PagamentoProvider>
                <UsuarioProvider>
                    <CarrinhoProvider>
                        {
                            usuario?.role === "ADMIN" ? <AdmRoutes /> : (usuario?.role === undefined ? <AuthRoutes /> : <AppRoutes />)
                        }
                    </CarrinhoProvider>
                </UsuarioProvider>
            </PagamentoProvider>

        </BrowserRouter>
    )
}
