import {api} from "../Configs.js"

export async function createAsset(data) {
    console.log(data)
    api.post("/assets", data)
     .then((responseData) => alert(responseData))
     .catch((err)=> alert(err));
}