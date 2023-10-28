import React from 'react'
import ReactDOM from 'react-dom/client'
import { ThemeProvider } from 'styled-components'
import GlobalStyle from './styles/global'
import theme from './styles/theme';
// import {} from './routes/index';

import  AppRoutes   from './routes/index.routes'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <ThemeProvider theme={theme}>
      <GlobalStyle />
        <AppRoutes />
    </ThemeProvider>
  </React.StrictMode>
)