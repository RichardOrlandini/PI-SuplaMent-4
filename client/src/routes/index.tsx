import { Routes, Route } from 'react-router-dom';
import { Signln } from '../pages/deslogados/Signln';
import { Cadastro } from '../pages/deslogados/Cadastro';
import BasePage from 'pages/common/BasePage';
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
import BaseForm from 'pages/common/BaseForm';
import ToggleBar from 'pages/logados/ToggleBar';
import { BrowserRouter } from 'react-router-dom';
import { LoginAdm } from 'pages/deslogados/LoginAdm';
import { HomeDeslogado } from 'pages/deslogados/Home';
import { Error404Container } from 'pages/common/Error404Container';
import { AddIcCall } from '@material-ui/icons';
import { CarrinhoProvider } from 'common/contexts/Carrinho';
import { PageAdmin } from 'pages/admin/PageHeaderAdmin';


export function RoutesApp() {
    return (

        <BrowserRouter>
            <Routes>

                <Route path="*" element={<Error404Container />} />


                <Route element={<BaseForm />}>
                    <Route path="/login" element={<Signln />} />
                    <Route path="/adm/login" element={<LoginAdm />} />
                    <Route path="/cadastro" element={<Cadastro />} />
                </Route>


                <Route element={<BasePage />}>
                    <Route index path='/' element={< HomeDeslogado />} />


                    <Route element={<RoutePrivateGlobal />}>
                        <Route path="/perfil" element={<Profile />} />
                    </Route>


                    <Route element={<RotaPrivadaCliente />}>
                        <Route path="/app/compra" element={<Compra />} />
                        <Route path="/app/pedidos" element={<Pedidos />} />
                    </Route>



                    <Route element={<RotaPrivadaAdmin />}>
                        <Route element={<PageAdmin />} >
                            <Route path="/admin/usuarios" element={<TableUsuario />} />
                            <Route path="/admin/usuarios/novo" element={<FormUsuario />} />
                            <Route path="/admin/usuarios/:id" element={<FormUsuario />} />

                            <Route path="/admin/categorias" element={<TableCategoria />} />
                            <Route path="/admin/categorias/novo" element={<FormCategoria />} />
                            <Route path="/admin/categorias/:id" element={<FormCategoria />} />

                            <Route path="/admin/produtos" element={<TableProduto />} />
                            <Route path="/admin/produtos/novo" element={<FormProduto />} />
                            <Route path="/admin/produtos/:id" element={<FormProduto />} />
                        </Route>
                    </Route>


                </Route>



            </Routes>
        </BrowserRouter>
    )
}







//                        <Route path="/" element={<ToggleBar />}>

// /*




// * /}