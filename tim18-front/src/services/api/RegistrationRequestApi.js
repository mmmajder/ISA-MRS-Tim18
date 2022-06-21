import {getApiCall} from "../Configs.js"

export async function getRegRequests(){
    try {
        const responseData = await getApiCall().get(`/regRequests/active`);
        return responseData;
    } catch (err) {
        return err.message
    }
  }

export async function acceptRegistrationRequest(id, callback){
    getApiCall().put(`/regRequests/accept/${id}`)
       .then((responseData) => callback(responseData.data))
       .catch((err)=> callback(false));
}

export async function declineRegistrationRequest(id, request, callback){
    const requestJSON = JSON.stringify(request);
    getApiCall().put(`/regRequests/decline/${id}`, requestJSON)
       .then((responseData) => callback(responseData.data))
       .catch((err)=> callback(false));

    }

export async function registerAdminRequest(requestBody) {
    try {
        const requestJSON = JSON.stringify(requestBody);
        const responseData = await getApiCall().put(`/regRequests/registerAdmin/`, requestJSON);
        return responseData;
    } catch (err) {
        return err.message
    }
}