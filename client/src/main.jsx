import React from 'react'
import ReactDOM from 'react-dom/client'
import { ThemeProvider } from 'styled-components'
import GlobalStyle from './styles/global'
import theme from './styles/theme';
// import {} from './routes/index';

import { CarrinhoProvider } from './common/contexts/Carrinho';
import { UsuarioProvider } from './common/contexts/Usuario';
import { PagamentoProvider } from './common/contexts/Pagamento';

import { RoutesApp } from './routes/index';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <ThemeProvider theme={theme}>
      <GlobalStyle />
      <PagamentoProvider>
        <UsuarioProvider>
          <CarrinhoProvider>
            <RoutesApp />
          </CarrinhoProvider>
        </UsuarioProvider>
      </PagamentoProvider>
    </ThemeProvider>
  </React.StrictMode>
)