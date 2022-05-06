import {api} from "../Configs.js"

export async function createAsset(data) {
    api.post("/assets", data)
     .then((responseData) => {})
     .catch((err)=> alert(err));
}

export async function getAllAssetsByUser(userId){
    try {
        const responseData = await api.get(`/assets/all/${userId}`, {
          headers: {
              'Content-Type': 'application/json',
          }
      });
        console.log(responseData)
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
  }

