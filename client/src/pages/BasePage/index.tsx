import { Header } from "components/Header";
import { HeaderPage } from "pages/logados/HeaderPage";
import { Outlet } from "react-router-dom";



export default function BasePage() {


    return (
        <>
            <HeaderPage/>
            <main>
            <Outlet />
            </main>
        </>
    )
}