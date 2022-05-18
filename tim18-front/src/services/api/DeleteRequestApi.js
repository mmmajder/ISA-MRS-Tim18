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

export async function getPendingDeleteProfileRequests() {
    try {
        const responseData = await api.get(`/deletationRequest/pending`);
        return responseData;
    } catch (err) {
        return false;
    }
}

export async function acceptDeletionRequest(id) {
    try {
        const responseData = await api.put(`/deletationRequest/accept/${id}`);
        return responseData;
    } catch (err) {
        return false;
    }
}

export async function declineDeletionRequest(id) {
    try {
        const responseData = await api.put(`/deletationRequest/decline/${id}`);
        return responseData;
    } catch (err) {
        return false;
    }
}

  
