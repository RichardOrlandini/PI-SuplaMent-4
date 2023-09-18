import axios from "axios";
export const api = axios.create({
  baseURL: 'http://localhost:8000/api',
  headers: {
    Accept: 'application/json',
    Content: 'application/json',
  }
})

//https://github.com/axios/axios#interceptors

api.interceptors.request.use(function (config) {
  const token = localStorage.getItem('@suplament:token');
  if (token) {
    api.defaults.headers.common['Authorization'] = "Bearer " + token;
  }
  return config;
}, function (error) {
  console.log('Erro no interceptor do axios')
  return Promise.reject(error);
});




