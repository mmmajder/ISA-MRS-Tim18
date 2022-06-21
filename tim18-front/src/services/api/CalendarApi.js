import {getApiCall} from "../Configs.js"

export async function createAppointment(data) {
    getApiCall().post("/calendar", data, {
        headers: {
            'Content-Type': 'application/json',
        }
    })
     .then((responseData) => console.log(responseData))
     .catch((err)=> alert(err));
}

export async function removePeriodOfAvailability(data){
    getApiCall().post("/calendar/remove", data, {
        headers: {
            'Content-Type': 'application/json',
        }
    })
     .then((responseData) => console.log(responseData))
     .catch((err)=> alert(err));
}


export async function getCalendarData(id){
    try {
        const responseData = await getApiCall().get(`/calendar/allCalendarsForUser/${id}`);
        return responseData;
    } catch (err) {
        return err.message
    }
  }

export async function getAssetCalendarData(idAsset){
try {
    const responseData = await getApiCall().get(`/calendar/assetCalendar/${idAsset}`);
    return responseData;
} catch (err) {
    return err.message
}
}

export async function getSpecialOffer(id) {
    try {
        const responseData = await getApiCall().get(`/calendar/specialOffer/${id}`);
        return responseData;
    } catch (err) {
        return err.message
    }
}