import { useEffect, useState, createContext, useContext } from 'react';
import { api } from '../services/api';
import { AuthProviderProps, DataProps, IAuthContext } from '../shared/interfaces/IAuthContext';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';


const AuthContext = createContext<IAuthContext | null>(null);

function AuthProvider({ children }: AuthProviderProps) {
  const [data, setData] = useState<DataProps | null>(null);

  async function signIn(email: string, senha: string) {
    try {
      //BUG DE RELOAD COM TOKEN NO HEADER DA api, POR ISSO FOI OPTADO POR USAR OUTRA INSTÂNCIA DO AXIOS.
      const resp = await axios.post("http://localhost:8000/api/login", { email, senha });
      const { user, token } = resp.data;

      localStorage.setItem("@suplament:user", JSON.stringify(user));
      localStorage.setItem("@suplament:token", token.token);
      api.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      setData({ token, user });

      const tokenLocal = localStorage.getItem('@suplament:token');

      if (tokenLocal) {
        api.interceptors.request.use(function (config) {
            api.defaults.headers.common['Authorization'] = "Bearer " + tokenLocal;
          return config;
        }, function (error) {
          console.log('Erro no interceptor do axios')
          return Promise.reject(error);
        });
      }
      
    } catch (error: any) {
      if (error.response) {
        alert(error.response.data.message);
      } else {
        alert("Não foi possível entrar.");
      }
    }
  }

  function signOut(): void {
    localStorage.removeItem("@suplament:token");
    localStorage.removeItem("@suplament:user");
    setData(null);
  }

  useEffect(() => {
    const token = localStorage.getItem("@suplament:token");
    const user = localStorage.getItem("@suplament:user");
    if (token && user) {
      api.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      setData({
        token,
        user: JSON.parse(user)
      })
    }
  }, []);

  return (
    <AuthContext.Provider value={{
      signIn,
      signOut,
      data: data,
    }}>
      {children}
    </AuthContext.Provider>
  )
}

function useAuth(): IAuthContext | null {
  const context = useContext(AuthContext);
  return context;
}

export { AuthProvider, useAuth };
