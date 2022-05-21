import {api} from "../Configs.js"

// export async function getAllPastReservations(callback, clientId){
//     await api.get(`/reservation/history/${clientId}`)
//               .then((responseData) => {console.log(responseData.data); callback(responseData.data)})
//               .catch(()=> {callback(false)});
// }

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

// export async function getCurrentReservations(callback){
//     await api.get(`/reservation/current`)
//             .then((responseData) => {callback(responseData.data)})
//             .catch(()=> {callback(false)});
// }