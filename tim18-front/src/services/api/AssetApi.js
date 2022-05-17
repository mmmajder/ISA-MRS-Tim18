import {api} from "../Configs.js"

export async function createAsset(data) {
    api.post("/assets", data)
     .then((responseData) => {})
     .catch((err)=> alert(err));
}

export async function deleteAsset(id) {
    console.log(id)
    api.delete(`/assets/${id}`)
     .then((responseData) => {})
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

export async function getAssetsByUserId(userId){
    try {
        const responseData = await api.get(`/assets/all/${userId}`);
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

export async function getFilteredAssets(address, numOfPeople, price, mark, startDate, endDate){
    try {
        console.log(address, numOfPeople, price, mark);
        const responseData = await api.get(`/assets/search`,  {
            params: {
                "address" : address,
                "numOfPeople":  numOfPeople, 
                "price" : price, 
                "mark" : mark, 
                "startDate" : startDate, 
                "endDate" : endDate
            }
          });
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
  }

  export async function getFilteredAssetsForRenter(renterId, address, numOfPeople, price, mark, startDate, endDate){
    try {
        console.log(address, numOfPeople, price, mark);
        console.log( startDate, endDate)
        const responseData = await api.get(`/assets/search/${renterId}`,  {
            params: {
                "address" : address,
                "numOfPeople":  numOfPeople, 
                "price" : price, 
                "mark" : mark,
                "startDate" : startDate, 
                "endDate" : endDate
            }
          });
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

