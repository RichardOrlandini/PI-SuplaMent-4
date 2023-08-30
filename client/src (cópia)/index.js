import React from 'react';
import ReactDOM from 'react-dom/client';
import GlobalStyle from './css/global'
import { ThemeProvider } from 'styled-components';
import { Routes } from './routes';
import theme from './css/theme';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <ThemeProvider theme={theme}>
      <GlobalStyle>
      <Routes/>
      </GlobalStyle>
    </ThemeProvider>
  </React.StrictMode>
);