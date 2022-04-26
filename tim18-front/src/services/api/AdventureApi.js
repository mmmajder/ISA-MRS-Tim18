import {api} from "../Configs.js"

export async function crateAdventure(adventureData) {
    try {
        const responseData = await api.post(`/adventures`, adventureData);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
}