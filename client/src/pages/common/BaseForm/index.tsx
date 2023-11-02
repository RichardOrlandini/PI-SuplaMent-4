import styled from "styled-components";
import { Outlet } from "react-router-dom";

const ContainerPrincipal = styled.div`
background-size: cover;
width: 100vw;
height: 100vh;
display: flex;
flex-direction: column;
align-items: center;
`

const Container = styled.div`
width: 50vw;
height: 100%;
display: flex;
flex-direction: column;
align-items: center;
`

export default function BaseForm() {
    return (
        <ContainerPrincipal>
            <Container>
                <Outlet />
            </Container>
        </ContainerPrincipal>
    )
}