import axios from 'axios'
import { getToken } from './AuthService/AuthService';

export const api = axios.create({
    baseURL: "http://localhost:8000",
    headers:  {"Authorization" : `Bearer ${getToken()}`,
    "Content-Type": "application/json;charset=UTF-8"}
});


