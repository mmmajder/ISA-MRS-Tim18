import {api} from "../Configs.js"

export async function createAppointment(data) {
    console.log(data)
    api.post("/calendar", data, {
        headers: {
            'Content-Type': 'application/json',
        }
    })
     .then((responseData) => alert(responseData))
     .catch((err)=> alert(err));
}