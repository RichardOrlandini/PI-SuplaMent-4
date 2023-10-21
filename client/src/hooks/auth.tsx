import { useEffect, useState, createContext, useContext } from 'react';
import { api } from '../services/api';
import { AuthProviderProps, DataProps, IAuthContext } from '../shared/interfaces/IAuthContext';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import * as URL from "../constants/url";
import { IUsuario } from 'shared/interfaces/IUsuario';


interface CreateDataAuthApi {
  status: number,
  newUser: {
    id: number;
    email: string;
    password: string;
  }
}

interface LoginDataAuthAPi {
  status: number;
  accessToken: string;
  message?: string;
}

const AuthContext = createContext<IAuthContext | null>(null);

function AuthProvider({ children }: AuthProviderProps) {
  const [data, setData] = useState<DataProps | null>(null);

  async function signIn(email: string, senha: string) {
    try {
      const respAuth = await axios.post(URL.LOGIN_AUTH_API, { email, password: senha });
      const { accessToken, message: m1 }: LoginDataAuthAPi = respAuth.data;

      if (m1 != null) {
        alert(m1);

      } else {

        const respApi = await api.get(`usuario/email/${email}`, {
          headers: { Authorization: `Bearer ${accessToken}` }
        });

        const dataApi: IUsuario = respApi.data;

        if (dataApi.message != null) {
          alert(dataApi.message);
        } else {
          localStorage.setItem("@suplament:user", JSON.stringify(dataApi));
          localStorage.setItem("@suplament:token", accessToken);
          axios.defaults.headers.common = { 'Authorization': `Bearer ${accessToken}` };
          setData({ token: accessToken, user: dataApi });

          const navigate = useNavigate();
          navigate("/produtos");
        }
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
