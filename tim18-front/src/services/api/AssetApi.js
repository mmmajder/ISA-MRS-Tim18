import {api} from "../Configs.js"

export async function createAsset(data) {
    console.log(data)
    api.post("/assets", data)
     .then((responseData) => alert(responseData))
     .catch((err)=> alert(err));
}

export async function getAssetById(id){
  try {
      const responseData = await api.get(`/assets/${id}`);
      return responseData;
  } catch (err) {
      console.log(err.message);
      return err.message
  }
}

export async function getAssets(){
  try {
      const responseData = await api.get(`/assets`);
      return responseData;
  } catch (err) {
      console.log(err.message);
      return err.message
  }
}

export async function getAssetsByRenter(renterId){
    try {
        const responseData = await api.get(`/assets/renter/${renterId}`);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
  }

export async function updateAsset(id, asset){
    try {
        console.log("updateAsset id " + id);
        const responseData = await api.put(`/assets/${id}`, asset);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
  }

// export function addAsset(asset){
//   api.post("/assets", asset)
//      .then((responseData) => alert(responseData))
//      .catch((err)=> alert(err));
// }
