import {api} from "../Configs.js"

export async function getDeleteRequestByID(id){
    try {
        console.log(id)
        const responseData = await api.get(`/deletationRequest/${id}`);
        return responseData;
    } catch (err) {
        return false;
    }
}

  
