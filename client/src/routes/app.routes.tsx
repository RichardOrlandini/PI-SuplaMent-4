import { Routes, Route } from 'react-router-dom'
import { HeaderPage } from '../pages/logados/HeaderPage'
import { Pedidos } from '../pages/logados/Pedidos'
import BasePage from 'pages/BasePage'
import HomeLogado from 'pages/logados/Home'


export function AppRoutes() {
    return (

        <Route path="/" element={<BasePage />}>
            <Route index element={<HomeLogado />} />
            <Route path="/pedidos" element={<Pedidos />} />
        </Route>
    )
}