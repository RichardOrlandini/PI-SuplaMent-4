import { Routes, Route } from 'react-router-dom'
import {PageHeaderAdmin } from '../pages/admin/PageHeaderAdmin'
import { CategoriaAdmin } from '../pages/admin/CategoriaAdmin'

export function AdmRoutes() {
    return (
        <Routes>
            <Route path='/admin' element={<PageHeaderAdmin/>}>
                <Route path="categorias" element={<CategoriaAdmin />} />
                <Route path="categorias/novo" element={<FormAdmin />} />
                <Route path="categorias/:id" element={<FormAdmin />} />
                <Route path="produtos" element={<FormProduto />} />
            </Route>
        </Routes>
    )
}