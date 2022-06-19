import {getApiCall} from "../Configs.js"

export async function getDeleteRequestByID(id){
    try {
        const responseData = await getApiCall().get(`/deletationRequest/${id}`);
        return responseData;
    } catch (err) {
        return false;
    }
}

export async function getPendingDeleteProfileRequests() {
    try {
        const responseData = await getApiCall().get(`/deletationRequest/pending`);
        return responseData;
    } catch (err) {
        return false;
    }
}

export async function acceptDeletionRequest(id, reason) {
    const requestJSON = JSON.stringify(reason);
    try {
        const responseData = await getApiCall().put(`/deletationRequest/accept/${id}`, requestJSON);
        return responseData;
    } catch (err) {
        return false;
    }
}

export async function declineDeletionRequest(id, reason) {
    const requestJSON = JSON.stringify(reason);
    try {
        const responseData = await getApiCall().put(`/deletationRequest/decline/${id}`, requestJSON);
        return responseData;
    } catch (err) {
        return false;
    }
}

export async function deleteUser(id) {
    try {
        const responseData = await getApiCall().put(`/deletationRequest/deleteUser/${id}`);
        return responseData;
    } catch (err) {
        return false;
    }
}

