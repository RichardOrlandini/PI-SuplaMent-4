interface Cores {
    branco: string;
    atencao: string;
    focus: string;
    primarias: {
        a: string;
        b: string;
        c: string;
    };
    secundarias: {
        a: string;
        b: string;
        c: string;
    };
    neutras: {
        a: string;
        b: string;
        c: string;
        d: string;
    };
    dark: {
        a: string;
        b: string;
    };
}

interface Espacamentos {
    xs: string;
    s: string;
    l: string;
    xl: string;
}

export default interface StylesType {
    cores: Cores;
    espacamentos: Espacamentos;
    fontFamily: string;
}