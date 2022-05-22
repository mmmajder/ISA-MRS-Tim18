import {api} from "../Configs.js"

export async function createAppointment(data) {
    console.log(data)
    api.post("/calendar", data, {
        headers: {
            'Content-Type': 'application/json',
        }
    })
     .then((responseData) => console.log(responseData))
     .catch((err)=> alert(err));
}

export async function removePeriodOfAvailability(data){
    console.log(data)
    api.post("/calendar/remove", data, {
        headers: {
            'Content-Type': 'application/json',
        }
    })
     .then((responseData) => console.log(responseData))
     .catch((err)=> alert(err));
}


export async function getCalendarData(id){
    try {
        const responseData = await api.get(`/calendar/allCalendarsForUser/${id}`);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
  }

export async function getAssetCalendarData(idAsset){
try {
    const responseData = await api.get(`/calendar/assetCalendar/${idAsset}`);
    return responseData;
} catch (err) {
    console.log(err.message);
    return err.message
}
}
