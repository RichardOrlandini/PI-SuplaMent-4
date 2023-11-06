import styled from 'styled-components';
import Card from '@material-ui/core/Card';

export const Container = styled(Card)`
  align-items: center;
  display: flex;
  justify-content: space-between;
  padding: 20px;
  width: 100%;
  background-color: transparent; 
  div {
    align-items: center;
    display: flex;
    gap: 20px;
    p {
      font-size: 22px;
      font-weight: bold;
      padding: 5px 0 0 5px;
    }
    span {
      font-size: 16px;
    }
  }
`;

export const StyledButton = styled(Button)`
  background-color: #f44336; // cor de fundo do botão
  color: #fff; // cor do texto do botão
  &:hover {
    background-color: #d32f2f; // cor de fundo do botão ao passar o mouse
  }
`;