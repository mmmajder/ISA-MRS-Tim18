import {api} from "../Configs.js"

export async function uploadProfilePhotoToServer(file, userId) {
     try {
        let formData = new FormData();
        formData.append("file", file);
        const responseData = await api.post(`/photos/profilePhoto/${userId}`, formData)
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
}

export async function uploadAssetPhotoToServer(file, assetId) {
    try {
        let formData = new FormData();
        formData.append("file", file);
        const responseData = await api.post(`/photos/assetPhoto/${assetId}`, formData)
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
}

export async function getPhotoFromServer(id) {
    try {
        const responseData = await api.get(`/photos/${id}`);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
}

export async function getAssetPhotoIdsFromServer(id) {
    try {
        const responseData = await api.get(`/photos/assetPhotoIds/${id}`);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
}

export async function deletePhotoOnServer(id) {
    try {
        const responseData = await api.delete(`/photos/${id}`);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
}