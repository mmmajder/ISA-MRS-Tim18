import {getApiCall} from "../Configs.js"

export async function unsubscribeFromAsset(assetId, clientId){
    const subscriptionJSON = JSON.stringify({assetId: assetId, clientId: clientId});
    await getApiCall().put(`/subscription/unsubscribe`, subscriptionJSON)
            .then((responseData) => {console.log(responseData.data)})
            .catch(()=> {console.log(false)});
}

export async function subscribeToAsset(assetId, clientId){
    const subscriptionJSON = JSON.stringify({assetId: assetId, clientId: clientId});
    await getApiCall().post(`/subscription/subscribe`, subscriptionJSON)
            .then((responseData) => {console.log(responseData.data);})
            .catch(()=> {console.log(false)});
}

export async function hasSubscription(callback, assetId, clientId){
    await getApiCall().get(`/subscription/hasSubscription/${assetId}/${clientId}`)
            .then((responseData) => {console.log("responseData.data");console.log(responseData.data);callback(responseData.data)})
            .catch(()=> {callback(false)});
}

export async function getMySubscriptions(callback, clientId, assetType){
    await getApiCall().put(`/subscription/mySubscriptions/${clientId}`, assetType)
            .then((responseData) => {callback(responseData.data)})
            .catch(()=> {callback(false)});
}

