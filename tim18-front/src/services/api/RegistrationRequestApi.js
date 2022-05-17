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

export async function declineRegistrationRequest(id){
    try {
        const responseData = await api.put(`/regRequests/decline/${id}`);
        console.log(responseData)
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
    }