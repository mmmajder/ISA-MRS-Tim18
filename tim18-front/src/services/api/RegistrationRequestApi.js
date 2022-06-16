import {api} from "../Configs.js"

export async function getRegRequests(){
    try {
        const responseData = await api.get(`/regRequests/active`);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
  }

export async function acceptRegistrationRequest(id){
try {
    const responseData = await api.put(`/regRequests/accept/${id}`);
    console.log(responseData)
    return responseData;
} catch (err) {
    console.log(err.message);
    return err.message
}
}

export async function declineRegistrationRequest(id, request){
    try {
        const requestJSON = JSON.stringify(request);
        const responseData = await api.put(`/regRequests/decline/${id}`, requestJSON);
        console.log(responseData)
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
    }

export async function registerAdminRequest(requestBody) {
    try {
        const requestJSON = JSON.stringify(requestBody);
        const responseData = await api.put(`/regRequests/registerAdmin/`, requestJSON);
        console.log(responseData)
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
}