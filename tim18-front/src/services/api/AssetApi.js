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

export async function getCallbackAssetById(callback, id){
    api.get(`/assets/${id}`)
       .then((responseData) => callback(responseData.data))
       .catch((err)=> callback(err));
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

export async function getFilteredAssets(assetType, address, numOfPeople, price, mark, startDate, endDate){
    try {
        console.log(assetType, address, numOfPeople, price, mark);
        const responseData = await api.get(`/assets/search`,  {
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

export async function createNewAsset(asset){
    try {
        const responseData = await api.post(`/assets`, asset);
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

// prices

export async function createNewPriceForAsset(assetId, newPrice) {
     try {
        let formData = new FormData();
        formData.append("newPrice", newPrice);
        const responseData = await api.post(`/prices/${assetId}`, formData)
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
}

export async function getAssetTodayPrice(assetId) {
    try {
       const responseData = await api.get(`/prices/today/${assetId}`)
       return responseData;
   } catch (err) {
       console.log(err.message);
       return err.message
   }
}

//reports

export async function getReport(renterId, reportFilters) {
    try {
       const responseData = await api.get(`/assets/report/${renterId}`, {
        params: {
            "completed" : reportFilters.completed,
            "canceled":  reportFilters.canceled, 
            "potential" : reportFilters.potential, 
            "fromDate" : reportFilters.fromDatee,
            "toDate" : reportFilters.toDatee,
            "period": reportFilters.period
        }
      })
       return responseData;
   } catch (err) {
       console.log(err.message);
       return err.message
   }
}

export async function getAssetReport(assetId, reportFilters) {
    try {
       const responseData = await api.get(`/assets/report/asset/${assetId}`, {
        params: {
            "completed" : reportFilters.completed,
            "canceled":  reportFilters.canceled, 
            "potential" : reportFilters.potential, 
            "fromDate" : reportFilters.fromDatee,
            "toDate" : reportFilters.toDatee,
            "period": reportFilters.period
        }
      })
       return responseData;
   } catch (err) {
       console.log(err.message);
       return err.message
   }
}

export async function getMonthlyReportById(assetId) {
    try {
       const responseData = await api.get(`/assets/report/monthly/${assetId}`)
       return responseData;
   } catch (err) {
       console.log(err.message);
       return err.message
   }
}