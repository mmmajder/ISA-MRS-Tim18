import {getApiCall} from "../Configs.js"

export async function uploadProfilePhotoToServer(file, userId) {
     try {
        let formData = new FormData();
        formData.append("file", file);
        const responseData = await getApiCall().post(`/photos/profilePhoto/${userId}`, formData)
        return responseData;
    } catch (err) {
        return err.message
    }
}

export async function uploadAssetPhotoToServer(file, assetId) {
    try {
        let formData = new FormData();
        formData.append("file", file);
        const responseData = await getApiCall().post(`/photos/assetPhoto/${assetId}`, formData)
        return responseData;
    } catch (err) {
        return err.message
    }
}

export async function getPhotoFromServer(id) {
    try {
        const responseData = await getApiCall().get(`/photos/${id}`);
        return responseData;
    } catch (err) {
        return err.message
    }
}

export async function getAssetPhotoIdsFromServer(id) {
    try {
        const responseData = await getApiCall().get(`/photos/assetPhotoIds/${id}`);
        return responseData;
    } catch (err) {
        return err.message
    }
}

export async function deletePhotoOnServer(id) {
    try {
        const responseData = await getApiCall().delete(`/photos/${id}`);
        return responseData;
    } catch (err) {
        return err.message
    }
}