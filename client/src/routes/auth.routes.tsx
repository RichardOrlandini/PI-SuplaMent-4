import { Routes, Route } from 'react-router-dom';
import { Signln } from '../pages/deslogados/Signln';
import { Cadastro } from '../pages/deslogados/Cadastro';
import { HomeDeslogado } from 'pages/deslogados/Home';
import { UsuarioProvider } from 'common/contexts/Usuario';
import { PagamentoProvider } from 'common/contexts/Pagamento';
import { CarrinhoProvider } from 'common/contexts/Carrinho';

export function AuthRoutes() {
    return (
        <Routes>
            <Route index path='/' element={<HomeDeslogado />} />
            <Route path='/login' element={<Signln />} />
            <Route path='/register' element={<Cadastro />} />
            <Route path='/carrinho' element={<HomeDeslogado />} />
        </Routes>
    )
}
