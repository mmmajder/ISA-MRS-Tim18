import {api} from "../Configs.js"
import { useNavigate } from "react-router-dom";

export async function loginRequest(userData, callback){
    const userDataJSON = JSON.stringify(userData);
    await api.post(`/auth/login`, userDataJSON)
              .then((responseData) => {callback(responseData.data)})    // userToken
              .catch(()=> {callback(false)});                           // unAuthorised
}

export async function getLogged(callback){
    await api.get(`/users/whoami`)
              .then((responseData) => {callback(responseData.data)})    // user
              .catch(()=> {/*navigate('/login')*/});

}

export async function sendRegistrationRequest(callback, request){
    console.log(request)
    const userDataJSON = JSON.stringify(request);
    console.log(userDataJSON);
    await api.post(`/auth/signup`, userDataJSON)
              .then((responseData) => {callback(responseData.data); })    // user
              .catch((responseData)=> {callback(responseData.data);});
}


export async function getVerificationCode(callback, code){
    await api.get(`/users/verify/${code}`)
              .then((responseData) => {callback(responseData.data)})    // user
              .catch((responseData)=> {callback(responseData.data)});

}