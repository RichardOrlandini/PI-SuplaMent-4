import { Routes, Route } from 'react-router-dom';
import { Signln } from '../pages/deslogados/Signln';
import { SignUp } from '../pages/deslogados/SignUp';
import { HomeDeslogado } from 'pages/deslogados/Home';
//import { SignUp } from '../pages/SignUp';

export function AuthRoutes(){
    return (
        <Routes>
            <Route  path='/login' element={<Signln/>} />
            <Route  path='/register' element={<SignUp />} />
            <Route  path='/' element={<HomeDeslogado/>}/>
        </Routes>
    )
}