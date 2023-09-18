import { Container } from "./styles";



export function Input({ icon : any,  ...rest}){
    return (
        <Container>
            {Icon && <Icon size={20} />}

            <input  {...rest} /> 
        </Container>
    )
}