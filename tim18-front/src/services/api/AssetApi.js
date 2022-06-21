import {getApiCall} from "../Configs.js"

export async function createAsset(data) {
    getApiCall().post("/assets", data)
     .then((responseData) => {})
     .catch((err)=> alert(err));
}

export async function deleteAsset(id) {
    console.log(id)
    getApiCall().delete(`/assets/${id}`)
     .then((responseData) => {})
     .catch((err)=> alert(err));
}

export async function deleteAssetAdmin(id) {
    console.log(id)
    getApiCall().delete(`/assets/deleteAssetAdmin/${id}`)
     .then((responseData) => {})
     .catch((err)=> alert(err));
}

export async function getAssetById(id){
  try {
      const responseData = await getApiCall().get(`/assets/${id}`);
      return responseData;
  } catch (err) {
      console.log(err.message);
      return err.message
  }
}

export async function getCallbackAssetById(callback, id){
    getApiCall().get(`/assets/${id}`)
       .then((responseData) => callback(responseData.data))
       .catch((err)=> callback(err));
  }

export async function getAssetsByUserId(userId){
    try {
        const responseData = await getApiCall().get(`/assets/all/${userId}`);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
  }


export async function getAssets(){
  try {
      const responseData = await getApiCall().get(`/assets`);
      return responseData;
  } catch (err) {
      console.log(err.message);
      return err.message
  }
}

export async function getFilteredAssets(assetType, address, numOfPeople, price, mark, startDate, endDate){
    try {
        console.log(assetType, address, numOfPeople, price, mark);
        const responseData = await getApiCall().get(`/assets/search`,  {
            params: {
                "assetType": assetType,
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
        const responseData = await getApiCall().get(`/assets/search/${renterId}`,  {
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
        const responseData = await getApiCall().get(`/assets/renter/${renterId}`);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
  }

export async function createNewAsset(asset){
    try {
        const responseData = await getApiCall().post(`/assets`, asset);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
  }

export async function updateAsset(id, asset){
    try {
        console.log("updateAsset id " + id);
        const responseData = await getApiCall().put(`/assets/${id}`, asset);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
  }

export async function getAllAssetsByUser(userId){
    try {
        const responseData = await getApiCall().get(`/assets/all/${userId}`, {
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

// prices

export async function createNewPriceForAsset(assetId, newPrice) {
     try {
        let formData = new FormData();
        formData.append("newPrice", newPrice);
        const responseData = await getApiCall().post(`/prices/${assetId}`, formData)
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
}

export async function getAssetTodayPrice(assetId) {
    try {
       const responseData = await getApiCall().get(`/prices/today/${assetId}`)
       return responseData;
   } catch (err) {
       console.log(err.message);
       return err.message
   }
}

//reports
export async function getReport(renterId, reportFilters) {
    try {
       const responseData = await getApiCall().get(`/assets/report/${renterId}`, {
        params: {
            "completed" : reportFilters.completed,
            "canceled":  reportFilters.canceled, 
            "potential" : reportFilters.potential, 
            "fromDate" : reportFilters.fromDatee,
            "toDate" : reportFilters.toDatee,
            "period": reportFilters.period,
            "assetId": reportFilters.assetId
        }
      })
       return responseData;
   } catch (err) {
       console.log(err.message);
       return err.message
   }
}


export async function getAssetsByType(callback, assetType){
    await getApiCall().get(`/assets/findByType/${assetType}`)
            .then((responseData) => {console.log(responseData.data); callback(responseData.data)})
            .catch(()=> {callback(false)});
}