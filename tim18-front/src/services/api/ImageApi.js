import {api} from "../Configs.js"

export async function uploadProfilePhotoToServer(file, userId) {
    let formData = new FormData();
    formData.append("file", file);
    api.post(`/photos/profilePhoto/${userId}`, formData)
     .then((responseData) => {})
     .catch((err)=> alert(err));
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