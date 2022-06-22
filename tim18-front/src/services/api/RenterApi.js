import {getApiCall} from "../Configs.js"

export async function createDeleteRequest(id, reason, callback){
    await getApiCall().post(`/renters/deleteionRequest/${id}`, reason)
             .then((responseData) => {callback(responseData.data)})
             .catch(()=> {callback(false)});
  }


export async function getRenterByID(id){
    try {
        const responseData = await getApiCall().get(`/users/${id}`, {
          headers: {
              'Content-Type': 'application/json',
          }
      });
        return responseData;
    } catch (err) {
        return err.message
    }
  }
  
  export async function getRenter(id){
    try {
        const responseData = await getApiCall().get(`/users/${id}`);
        return responseData;
    } catch (err) {
        return err.message
    }
  }

  export async function updateRenter(data){
    try {
        const responseData = await getApiCall().put(`/renters`, data);
        return responseData;
    } catch (err) {
        return err.message
    }
  }

  export async function getRenterByAssetId(assetId){
    try {
        const responseData = await getApiCall().get(`/renters/assetId/${assetId}`);
        return responseData;
    } catch (err) {
        return err.message
    }
  }

  export async function doesRenterOwn(renterId, assetId){
    try {
        const responseData = await getApiCall().get(`/renters/owns/${renterId}/${assetId}`);
        return responseData;
    } catch (err) {
        return err.message
    }
  }