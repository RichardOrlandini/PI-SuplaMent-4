import { Routes, Route } from 'react-router-dom'
import {PageHeaderAdmin } from '../pages/admin/PageHeaderAdmin'
import { CategoriaAdmin } from '../pages/admin/CategoriaAdmin'
import { FormAdmin } from '../pages/admin/FormAdmin'
import { FormProduto } from '../pages/admin/FormProduto'

export function AdmRoutes() {
    return (
        <Routes>
            <Route path='/' element={<PageHeaderAdmin/>}>
                <Route path="/admin/categorias" element={<CategoriaAdmin />} />
                <Route path="/admin/categorias/novo" element={<FormAdmin/>} />
                <Route path="/admin/categorias/:id" element={<FormAdmin />} />
                <Route path="/admin/produtos" element={<FormProduto />} />
            </Route>
        </Routes>
    )
}