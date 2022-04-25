import {api} from "../Configs.js"

export async function getDeleteRequestByID(id){
    try {
        const responseData = await api.get(`/deletationRequest/${id}`);
        return responseData;
    } catch (err) {
        return false;
    }
}

  
