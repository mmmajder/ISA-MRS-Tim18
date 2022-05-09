import {api} from "../Configs.js"

export async function createDeleteRequest(id, reason, callback){
    await api.post(`/renters/deleteionRequest/${id}`, reason)
             .then((responseData) => {callback(responseData.data)})
             .catch(()=> {callback(false)});
  }


export async function getRenterByID(id){
    try {
        console.log("stigao")
        console.log(id)
        const responseData = await api.get(`/users/${id}`, {
          headers: {
              'Content-Type': 'application/json',
          }
      });
        console.log(responseData)
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
  }
  
  export async function getRenter(id){
    try {
        const responseData = await api.get(`/users/${id}`);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
  }

  export async function updateRenter(data){
    try {
        const responseData = await api.put(`/renters`, data);
        return responseData;
    } catch (err) {
        console.log(err.message);
        return err.message
    }
  }