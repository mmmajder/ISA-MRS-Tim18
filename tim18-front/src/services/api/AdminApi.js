import {getApiCall} from "../Configs.js"

export async function saveNewPassword(user, password){
  
    await getApiCall().put(`/admin/savePassword/${user.id}`, JSON.stringify(password))
              .then((responseData) => { console.log(responseData.data)})
              .catch(()=> {});
}


export async function getReportAdmin(renterId, reportFilters) {
    try {
       const responseData = await getApiCall().get(`/admin/report`, {
        params: {
            "completed" : reportFilters.completed,
            "canceled":  reportFilters.canceled, 
            "potential" : reportFilters.potential, 
            "fromDate" : reportFilters.fromDatee,
            "toDate" : reportFilters.toDatee,
            "period": reportFilters.period,
            "assetType": reportFilters.assetType
        }
      })
       return responseData;
   } catch (err) {
       console.log(err.message);
       return err.message
   }
}