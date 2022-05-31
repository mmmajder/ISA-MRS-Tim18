import {api} from "../Configs.js"

export async function getReview(id) {
    try {
        const responseData = await api.get(`/review/${id}`);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
}

export async function getReviews(userId) {
    try {
        const responseData = await api.get(`/review/user/${userId}`);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
}

export async function getAssetReviews(assetId) {
    try {
        const responseData = await api.get(`/review/asset/${assetId}`);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
}

export async function createReview(reservationId, review){
    try {
        const responseData = await api.post(`/review/${reservationId}`, review);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
  }