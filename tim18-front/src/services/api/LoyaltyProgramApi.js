import {api} from "../Configs.js"

export async function getLoyatyProgram(){
    try {
        const responseData = await api.get(`/loyaltyProgram/all`);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
  }

export async function getReservationFinances(){
    try {
        const responseData = await api.get(`/reservationFinances/all`);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
}


export async function saveLoyaltyProgram(ClientProgram, RenterProgram, reservationTax, pointsPerReservation) {
    try {
        const requestBody = {loyaltyProgram: [...ClientProgram, ...RenterProgram], reservationFinancesDTO: {pointsPerReservation, reservationTax}}
        const responseData = await api.put(`/loyaltyProgram/update`, requestBody);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
}

export async function getCategory(pointCount, userType, callback){
  
    await api.get(`/loyaltyProgram/getCategory/${pointCount}/${userType}`)
              .then((responseData) => { callback(responseData.data)})
              .catch(()=> {});
}


