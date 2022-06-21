import {getApiCall} from "../Configs.js"

export async function getReview(id) {
    try {
        const responseData = await getApiCall().get(`/review/${id}`);
        return responseData;
    } catch (err) {
        return err.message
    }
}

export async function getClientsReviews() {
    try {
        const responseData = await getApiCall().get(`/review/clients/pending`);
        return responseData;
    } catch (err) {
        return err.message
    }
}

export async function getRentersReviews() {
    try {
        const responseData = await getApiCall().get(`/review/renters/pending`);
        return responseData;
    } catch (err) {
        return err.message
    }
}

export async function getReviews(userId, acceptedOnly) {
    try {
        const responseData = await getApiCall().get(`/review/user/${userId}`, {
            params: {
                "acceptedOnly":  acceptedOnly
            }
          });
        return responseData;
    } catch (err) {
        return err.message
    }
}

export async function getAssetReviews(assetId, acceptedOnly) {
    try {
        const responseData = await getApiCall().get(`/review/asset/${assetId}`, {
            params: {
                "acceptedOnly":  acceptedOnly
            }
          });
        return responseData;
    } catch (err) {
        return err.message
    }
}

export async function getRating(userId) {
    try {
        const responseData = await getApiCall().get(`/review/userRating/${userId}`);
        return responseData;
    } catch (err) {
        return err.message
    }
}

export async function getAssetRating(assetId) {
    try {
        const responseData = await getApiCall().get(`/review/assetRating/${assetId}`);
        return responseData;
    } catch (err) {
        return err.message
    }
}

export async function createReview(reservationId, review){
    try {
        const responseData = await getApiCall().post(`/review/${reservationId}`, review);
        return responseData;
    } catch (err) {
        return err.message
    }
  }

  export async function cancelClientsComplaint(id, callback){
    await getApiCall().put(`/review/cancelClientsComplaint/${id}`)
    .then((responseData) => {callback(responseData.data)})    
    .catch(()=> {callback(false)});
    // try {
    //     const responseData = await getApiCall().put(`/review/cancelClientsComplaint/${id}`);
    //     return responseData;
    // } catch (err) {
    //     return err.message
    // }
  }

  export async function sendCommentOnComplaint(id, mailClient, mailRenter, callback){
      const reqData = {mailClient, mailRenter}
      await getApiCall().put(`/review/sendCommentOnComplaint/${id}`, reqData)
    .then((responseData) => {callback(responseData.data)})    
    .catch(()=> {callback(false)});
    // try {
    //     const responseData = await getApiCall().put(`/review/sendCommentOnComplaint/${id}`, reqData);
    //     return responseData;
    // } catch (err) {
    //     return err.message
    // }
  } 
  
  export async function addPoint(request, callback){
    await getApiCall().put(`/review/addPoint/${request.id}`)
    .then((responseData) => {callback(responseData.data)})    
    .catch(()=> {callback(false)});
    // try {
    //     console.log(request)
    //     const responseData = await getApiCall().put(`/review/addPoint/${request.id}`);
    //     return responseData;
    // } catch (err) {
    //     return err.message
    // }
}

export async function declineAddPoint(request, callback){
    await getApiCall().put(`/review/declineAddPoint/${request.id}`)
    .then((responseData) => {callback(responseData.data)})    
    .catch(()=> {callback(false)});
    // try {
    //     const responseData = await getApiCall().put(`/review/declineAddPoint/${request.id}`);
    //     return responseData;
    // } catch (err) {
    //     return err.message
    // }
}


export async function getPendingNonComplaintReviews() {
    try {
        const responseData = await getApiCall().get(`/review/pendingNonComplaint`);
        return responseData;
    } catch (err) {
        return err.message
    }
}

export async function acceptdeclineReview(request, isAccepted, callback){
    await getApiCall().put(`/review/acceptdeclineReview/${request.id}`, isAccepted)
    .then((responseData) => {callback(responseData.data)})  
    .catch(()=> {callback(false)});   
}

