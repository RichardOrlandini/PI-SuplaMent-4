import axios from "axios";
export const api = axios.create({
  baseURL: 'http://localhost:8000/api',
  headers: {
    Accept: 'application/json',
    Content: 'application/json',
  }
})

//https://github.com/axios/axios#interceptors







