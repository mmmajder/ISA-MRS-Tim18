import {getApiCall} from "../Configs.js"

export async function getPastReservationsByType(callback, clientId, assetType){
    await getApiCall().get(`/reservation/history/${clientId}`, {params: { "assetType": assetType}})
            .then((responseData) => {console.log(responseData.data); callback(responseData.data)})
            .catch(()=> {callback(false)});
}

export async function getCurrentReservationsByType(callback, clientId, assetType){
    await getApiCall().get(`/reservation/current/${clientId}`, {params: { "assetType": assetType}})
            .then((responseData) => {console.log(responseData.data); callback(responseData.data)})
            .catch(()=> {callback(false)});
}

export async function getPastRenterReservations(callback, renterId){
    await getApiCall().get(`/reservation/history/renter/${renterId}`) 
            .then((responseData) => {console.log(responseData.data); callback(responseData.data)})
            .catch(()=> {callback(false)});
}

export async function getCurrentRenterReservations(callback, renterId){
    await getApiCall().get(`/reservation/current/renter/${renterId}`) 
            .then((responseData) => {console.log(responseData.data); callback(responseData.data)})
            .catch(()=> {callback(false)});
}

export async function getReservation(reservationId) {
    try {
        const responseData = await getApiCall().get(`/reservation/${reservationId}`);
        return responseData;
    } catch (err) {
        return err.message
    }
}


export async function cancelReservation(reservationId){
    await getApiCall().put(`/reservation/cancel/${reservationId}`)
            .then((responseData) => {console.log(responseData.data)})
            .catch(()=> {console.log(false)});
}


export async function reserveSepcialOfferRequest(callback, reservation){
    await getApiCall().post(`/reservation/reserveSpecialOffer`, reservation)
            .then((responseData) => {callback(responseData.data)})
            .catch(()=> {callback(false)});
}

export async function makeReservation(callback, reservation){
    const reservationJSON = JSON.stringify(reservation);
    await getApiCall().post(`/reservation/makeReservation`, reservationJSON)
            .then((responseData) => {callback(responseData.data)})
            .catch(()=> {callback(false)});
}

