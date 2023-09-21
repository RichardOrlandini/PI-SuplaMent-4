import { Routes, Route } from 'react-router-dom';
import { Signln } from '../pages/deslogados/Signln';
import { SignUp } from '../pages/deslogados/SignUp';
//import { SignUp } from '../pages/SignUp';

export function AuthRoutes(){
    return (
        <Routes>
            <Route  path='/' element={<Signln/>} />
            <Route  path='/register' element={<SignUp />} />
        </Routes>
    )
}