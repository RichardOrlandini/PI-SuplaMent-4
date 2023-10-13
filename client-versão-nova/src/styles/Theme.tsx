import { ThemeProvider } from "@emotion/react";
import { ReactNode } from "react";
import { Global } from "@emotion/react";
import StylesType from "types/StylesType";


const theme: StylesType = {
  cores: {
    branco: "#fff",
    atencao: "",
    focus: "#b009ff",
    primarias: {
      a: "#5754ed",
      b: "#d93114",
      c: "",
    },
    secundarias: {
      a: "#f8f8fd",
      b: "",
      c: "",
    },
    neutras: {
      a: "#373737",
      b: "",
      c: "",
      d: "",
    },
    dark: {
      a: "",
      b: "#b61b00",
    },
  },
  espacamentos: {
    xs: "8px",
    s: "16px",
    l: "32px",
    xl: "48px",
  },
  fontFamily: "'montserrat', sans-serif",
};

interface Props {
  children: ReactNode;
}

export const Theme = ({ children }: Props) => {
  return <ThemeProvider theme={theme}>{children}</ThemeProvider>;
};

const estilos = (theme: StylesType) => {
  return {
    html: {
      fontFamily: theme.fontFamily,
    },
    body: {
      margin: 0,
    },
  };
};

export const GlobalStyles = () => {
  return <Global styles={estilos(theme)} />;
};
