import {getApiCall} from "../Configs.js"

export async function getRegRequests(){
    try {
        const responseData = await getApiCall().get(`/regRequests/active`);
        return responseData;
    } catch (err) {
        return err.message
    }
  }

export async function acceptRegistrationRequest(id){
try {
    const responseData = await getApiCall().put(`/regRequests/accept/${id}`);
    return responseData;
} catch (err) {
    return err.message
}
}

export async function declineRegistrationRequest(id, request){
    try {
        const requestJSON = JSON.stringify(request);
        const responseData = await getApiCall().put(`/regRequests/decline/${id}`, requestJSON);
        console.log(responseData)
        return responseData;
    } catch (err) {
        return err.message
    }
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