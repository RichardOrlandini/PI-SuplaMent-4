import axios, { AxiosInstance } from "axios";
import { useAuth } from "../hooks/auth";

const baseURL: string = "http://localhost:8000/api";

export const api: AxiosInstance = axios.create({
  baseURL,
  headers: {
    "Access-Control-Allow-Origin": "http://localhost:5173",
    "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE",
    "Access-Control-Allow-Headers": "Content-Type",
  }
});

export const existingHeaders = api.defaults.headers.common;
