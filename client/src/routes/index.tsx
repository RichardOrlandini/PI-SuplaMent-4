import { Routes, Route } from 'react-router-dom';
import { Signln } from '../pages/deslogados/Signln';
import { Cadastro } from '../pages/deslogados/Cadastro';
import BasePage from 'pages/BasePage';
import { Home } from '@material-ui/icons';
import { Profile } from 'pages/logados/Profile';
import { Pedidos } from 'pages/logados/Pedidos';
import Compra from 'pages/logados/Compra';
import RotaPrivadaCliente from './RoutePrivateCliente';
import RotaPrivadaAdmin from './RoutePrivateAdmin';
import { TableCategoria } from '../pages/admin/Categoria/TableCategoria'
import { FormCategoria } from '../pages/admin/Categoria/FormCategoria'
import { TableProduto } from '../pages/admin/Produto/TableProduto'
import { TableUsuario } from '../pages/admin/Usuario/TableUsuario'
import { FormUsuario } from '../pages/admin/Usuario/FormUsuario'
import { FormProduto } from '../pages/admin/Produto/FormProduto'
import RoutePrivateGlobal from './RoutePrivateGlobal';
import BaseForm from 'pages/BaseForm';
import ToggleBar from 'pages/logados/ToggleBar';
import { BrowserRouter } from 'react-router-dom';
import { PagamentoProvider } from 'common/contexts/Pagamento';
import { UsuarioProvider } from 'common/contexts/Usuario';
import { CarrinhoProvider } from 'common/contexts/Carrinho';
import { LoginAdm } from 'pages/deslogados/LoginAdm';


export function RoutesApp() {
    return (

        <BrowserRouter>
            <Routes>


//rotas de login
                <Route path="/" element={<BaseForm />}>
                    <Route index path="/login" element={<Signln />} />
                    <Route index path="/adm/login" element={<LoginAdm />} />
                    <Route path="/cadastro" element={<Cadastro />} />
                </Route>


//rotas que eu quero que o base page fica pra todas paginas :

        //rotas de cliente 
                <Route path="/" element={<BasePage />}>
                    <Route index element={<Home />} />

                    <Route element={<RotaPrivadaCliente />}>
                        <Route path="/" element={<ToggleBar />}>
                            <Route path="/app/compra" element={<Compra />} />
                            <Route path="/app/pedidos" element={<Pedidos />} />
                        </Route>
                    </Route>


                    <Route element={<RoutePrivateGlobal />}>
                        <Route path="/perfil" element={<Profile />} />
                    </Route>


// rotas de admin: 
                     <Route path='/admin' element={<RotaPrivadaAdmin />}>
                        <Route path="/usuarios" element={<TableUsuario />} />
                        <Route path="/usuarios/novo" element={<FormUsuario />} />
                        <Route path="/usuarios/:id" element={<FormUsuario />} />

                        <Route path="/categorias" element={<TableCategoria />} />
                        <Route path="/categorias/novo" element={<FormCategoria />} />
                        <Route path="/categorias/:id" element={<FormCategoria />} />

                        <Route index path="/produtos" element={<TableProduto />} />
                        <Route path="/produtos/novo" element={<FormProduto />} />
                        <Route path="/produtos/:id" element={<FormProduto />} />
                    </Route>
                </Route>

            </Routes>
        </BrowserRouter>
    )
}



//

<Route path="*" component={Error404Container} />
