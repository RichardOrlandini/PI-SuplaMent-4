import { FC } from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import { PrivateRoute } from './PrivateRoute';
import { RoleBasedRoute } from './RoleBasedRoute';
import { Signln } from '../pages/deslogados/Signln';
import { Home } from '@material-ui/icons';
import { Profile } from 'pages/logados/Profile';
import { Pedidos } from 'pages/logados/Pedidos';
import Compra from 'pages/logados/Compra';


const AppRoutes: FC = () => {
  return (
    <Router>
        <PrivateRoute url="/perfil" component={Profile} />
        <RoleBasedRoute path="/app/compra" component={Compra} role="USER" />
        <RoleBasedRoute path="/app/pedidos" component={Pedidos} role="USER" />
        <Route path="/login" element={<Signln/>} />
    </Router>
  );
};

export default AppRoutes;

// import { Cadastro } from '../pages/deslogados/Cadastro';

// import BasePage from 'pages/BasePage';
// import RotaPrivadaCliente from './RoutePrivateCliente';
// import RotaPrivadaAdmin from './RoutePrivateAdmin';
// import { TableCategoria } from '../pages/admin/Categoria/TableCategoria'
// import { FormCategoria } from '../pages/admin/Categoria/FormCategoria'
// import { TableProduto } from '../pages/admin/Produto/TableProduto'
// import { TableUsuario } from '../pages/admin/Usuario/TableUsuario'
// import { FormUsuario } from '../pages/admin/Usuario/FormUsuario'
// import { FormProduto } from '../pages/admin/Produto/FormProduto'
// import RoutePrivateGlobal from './RoutePrivateGlobal';
// import BaseForm from 'pages/BaseForm';
// import ToggleBar from 'pages/logados/ToggleBar';
// import { BrowserRouter } from 'react-router-dom';
// import { PagamentoProvider } from 'common/contexts/Pagamento';
// import { UsuarioProvider } from 'common/contexts/Usuario';
// import { CarrinhoProvider } from 'common/contexts/Carrinho';
// import { LoginAdm } from 'pages/deslogados/LoginAdm';