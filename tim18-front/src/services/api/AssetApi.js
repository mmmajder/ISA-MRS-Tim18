import {api} from "../Configs.js"

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

export async function getAssetsByRenter(){
    try {
        const responseData = await api.get(`/assets/renter/${renterId}`);
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