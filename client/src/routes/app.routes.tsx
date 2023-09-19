import { Routes, Route } from 'react-router-dom'
import { HeaderPage } from '../pages/logados/HeaderPage'
import { Pedidos } from '../pages/logados/Pedidos'


export function AppRoutes(){
    return (
   <Routes>
       <Route path='/' element={<HeaderPage/>}>
            <Route path='/pedidos' element={<Pedidos/>} />
        </Route>
   </Routes>
    )
}