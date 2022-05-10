import {api} from "../Configs.js"

export async function loginRequest(userData, callback){
    const userDataJSON = JSON.stringify(userData);
    await api.post(`/auth/login`, userDataJSON)
              .then((responseData) => {callback(responseData.data)})    // userToken
              .catch(()=> {callback(false)});                           // unAuthorised
}

export async function getLogged(callback){
    await api.get(`/users/whoami`)
              .then((responseData) => {callback(responseData.data)})    // user
              .catch(()=> {callback(false)});

}

export async function sendRegistrationRequest(callback, request){
    console.log(request)
    const userDataJSON = JSON.stringify(request);
    await api.post(`/auth/signup`, userDataJSON)
              .then((responseData) => {callback(responseData.data)})    // user
              .catch(()=> {callback(false)});
}
