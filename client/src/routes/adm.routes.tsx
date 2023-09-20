import { Routes, Route } from 'react-router-dom'
import {PageHeaderAdmin } from '../pages/admin/PageHeaderAdmin'
import { TableCategoria } from '../pages/admin/Categoria/TableCategoria'
import { FormCategoria } from '../pages/admin/Categoria/FormCategoria'
import { TableProduto } from '../pages/admin/Produto/TableProduto'
import { TableUsuario } from '../pages/admin/Usuario/TableUsuario'
import { FormUsuario } from '../pages/admin/Usuario/FormUsuario'
import { FormProduto } from '../pages/admin/Produto/FormProduto'

export function AdmRoutes() {
    return (
        <Routes>
            <Route path='/' element={<PageHeaderAdmin/>}>
                <Route path="/admin/usuarios" element={<TableUsuario />} />
                <Route path="/admin/usuarios/novo" element={<FormUsuario />} />
                <Route path="/admin/usuarios/:id" element={<FormUsuario />} />

                <Route path="/admin/categorias" element={<TableCategoria />} />
                <Route path="/admin/categorias/novo" element={<FormCategoria/>} />
                <Route path="/admin/categorias/:id" element={<FormCategoria />} />

                <Route path="/admin/produtos" element={<TableProduto />} />
                <Route path="/admin/produtos/novo" element={<FormProduto />} />
                <Route path="/admin/produtos/:id" element={<FormProduto />} />

            </Route>
        </Routes>
    )
}