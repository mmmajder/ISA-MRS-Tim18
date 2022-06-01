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

export async function getClientsReviews() {
    try {
        const responseData = await api.get(`/review/clients/pending`);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
}

export async function getReviews(userId, acceptedOnly) {
    try {
        const responseData = await api.get(`/review/user/${userId}`, {
            params: {
                "acceptedOnly":  acceptedOnly
            }
          });
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
}

export async function getAssetReviews(assetId, acceptedOnly) {
    try {
        const responseData = await api.get(`/review/asset/${assetId}`, {
            params: {
                "acceptedOnly":  acceptedOnly
            }
          });
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
}

export async function getRating(userId) {
    try {
        const responseData = await api.get(`/review/userRating/${userId}`);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
}

export async function getAssetRating(assetId) {
    try {
        const responseData = await api.get(`/review/assetRating/${assetId}`);
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

  export async function cancelClientsComplaint(id){
    try {
        const responseData = await api.put(`/review/cancelClientsComplaint/${id}`);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
  }

  export async function sendCommentOnComplaint(id, mailClient, mailRenter){
      const reqData = {mailClient, mailRenter}
    try {
        const responseData = await api.put(`/review/sendCommentOnComplaint/${id}`, reqData);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
  } 
  