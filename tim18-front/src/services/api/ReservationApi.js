import {api} from "../Configs.js"

export async function getPastReservationsByType(callback, clientId, assetType){
    await api.get(`/reservation/history/${clientId}`, {params: { "assetType": assetType}})
            .then((responseData) => {console.log(responseData.data); callback(responseData.data)})
            .catch(()=> {callback(false)});
}

export async function getCurrentReservationsByType(callback, clientId, assetType){
    await api.get(`/reservation/current/${clientId}`, {params: { "assetType": assetType}})
            .then((responseData) => {console.log(responseData.data); callback(responseData.data)})
            .catch(()=> {callback(false)});
}

export async function cancelReservation(reservationId){
    await api.put(`/reservation/cancel/${reservationId}`)
            .then((responseData) => {console.log(responseData.data)})
            .catch(()=> {console.log(false)});
}