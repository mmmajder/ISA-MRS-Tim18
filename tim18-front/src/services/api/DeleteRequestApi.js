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

export async function acceptDeletionRequest(id, reason) {
    const requestJSON = JSON.stringify(reason);
    try {
        const responseData = await api.put(`/deletationRequest/accept/${id}`, requestJSON);
        return responseData;
    } catch (err) {
        return false;
    }
}

export async function declineDeletionRequest(id, reason) {
    const requestJSON = JSON.stringify(reason);
    try {
        const responseData = await api.put(`/deletationRequest/decline/${id}`, requestJSON);
        return responseData;
    } catch (err) {
        return false;
    }
}

export async function deleteUser(id) {
    try {
        console.log(id)
        console.log("id")
        const responseData = await api.put(`/deletationRequest/deleteUser/${id}`);
        return responseData;
    } catch (err) {
        return false;
    }
}

