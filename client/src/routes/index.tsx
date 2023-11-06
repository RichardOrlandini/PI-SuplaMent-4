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
<<<<<<< Updated upstream
            {   
                usuario?.role === "ADMIN" ? <AdmRoutes/> : (usuario?.role === undefined ? <AuthRoutes/> : <AppRoutes/>)
            }
=======
            <Routes>

                <Route path="*" element={<Error404Container />} />


                <Route element={<BaseForm />}>
                    <Route path="/login" element={<Signln />} />
                    <Route path="/adm/login" element={<LoginAdm />} />
                    <Route path="/cadastro" element={<Cadastro />} />
                </Route>


                <Route element={<BasePage />}>
                    <Route index path='/' element={< HomeDeslogado />} />
                    <Route path="/app/compra" element={<Compra />} />



                    <Route element={<RoutePrivateGlobal />}>
                        <Route path="/perfil" element={<Profile />} />
                    </Route>


                    <Route element={<RotaPrivadaCliente />}>
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
>>>>>>> Stashed changes
        </BrowserRouter>
    )
}
