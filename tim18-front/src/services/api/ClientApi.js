import {getApiCall} from "../Configs.js"

export async function getClientByID(id){
  try {
      const responseData = await getApiCall().get(`/clients/${id}`);
      return responseData;
  } catch (err) {
      return err.message
  }
}

export async function getAllClients(callback){
  getApiCall().get(`/clients`)
     .then((responseData) => callback(responseData.data))
     .catch((err)=> callback(err));
}

export async function getAllMappedClients(callback){
  getApiCall().get(`/clients`)
     .then((responseData) =>{ const clients = responseData.data.map(client => ({ value: client.id, label: client.firstName + ' ' + client.lastName }));
                              const clientsArray = Object.values(clients); 
                              clientsArray.splice(0, 0, {value: 'None', label: ''}); 
                              callback(clientsArray) })
     .catch((err)=> callback(err));
}

export function addClient(clientData){
  getApiCall().post("/clients", clientData)
     .then((responseData) => alert(responseData))
     .catch((err)=> alert(err));
}

/*export async function deleteClient(id){
  try {
      const responseData = await getApiCall().delete(`/clients/${id}`);
      return responseData;
  } catch (err) {
      console.log(err.message);
      return err.message
  }
}*/
export async function createDeleteRequest(id, reason, callback){
  await getApiCall().post(`/clients/${id}`, reason)
           .then((responseData) => {callback(responseData.data)})
           .catch(()=> {callback(false)});
}

export async function updateClient(clientData, callback){
  
    const clientDataJSON = JSON.stringify(clientData);
    await getApiCall().put(`/clients`, clientDataJSON)
              .then((responseData) => {callback(responseData.data)})
              .catch(()=> {callback(false)});

  
}