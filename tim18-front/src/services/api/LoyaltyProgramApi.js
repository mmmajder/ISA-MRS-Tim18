import {getApiCall} from "../Configs.js"

export async function getLoyatyProgram(){
    try {
        const responseData = await getApiCall().get(`/loyaltyProgram/all`);
        return responseData;
    } catch (err) {
        return err.message
    }
  }

export async function getReservationFinances(){
    try {
        const responseData = await getApiCall().get(`/reservationFinances/all`);
        return responseData;
    } catch (err) {
        return err.message
    }
}


export async function saveLoyaltyProgram(ClientProgram, RenterProgram, reservationTax, pointsPerReservation) {
    try {
        const requestBody = {loyaltyProgram: [...ClientProgram, ...RenterProgram], reservationFinancesDTO: {pointsPerReservation, reservationTax}}
        const responseData = await getApiCall().put(`/loyaltyProgram/update`, requestBody);
        return responseData;
    } catch (err) {
        return err.message
    }
}

export async function getCategory(pointCount, userType, callback){
  
    await getApiCall().get(`/loyaltyProgram/getCategory/${pointCount}/${userType}`)
              .then((responseData) => { callback(responseData.data)})
              .catch(()=> {});
}


