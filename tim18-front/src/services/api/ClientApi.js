import {api} from "../Configs.js"

export async function getClientByID(id){
  try {
      const responseData = await api.get(`/clients/${id}`);
      return responseData;
  } catch (err) {
      console.log(err.message);
      return err.message
  }
}

export async function getAllClients(callback){
  api.get(`/clients`)
     .then((responseData) => callback(responseData.data))
     .catch((err)=> callback(err));
}

export async function getAllMappedClients(callback){
  api.get(`/clients`)
     .then((responseData) => callback(responseData.data.map(client => ({ value: client.id, label: client.firstName + ' ' + client.lastName }))))
     .catch((err)=> callback(err));
}

export function addClient(clientData){
  api.post("/clients", clientData)
     .then((responseData) => alert(responseData))
     .catch((err)=> alert(err));
}

/*export async function deleteClient(id){
  try {
      const responseData = await api.delete(`/clients/${id}`);
      return responseData;
  } catch (err) {
      console.log(err.message);
      return err.message
  }
}*/
export async function createDeleteRequest(id, reason, callback){
  await api.post(`/clients/${id}`, reason)
           .then((responseData) => {callback(responseData.data)})
           .catch(()=> {callback(false)});
}

export async function updateClient(clientData, callback){
  
    const clientDataJSON = JSON.stringify(clientData);
    await api.put(`/clients`, clientDataJSON)
              .then((responseData) => {callback(responseData.data)})
              .catch(()=> {callback(false)});

  
}


/* STARE FJE 
  // Add Client
const addClientFetch = async (client) => {
  const res = await fetch('http://localhost:8000/clients', 
                          {method: 'POST',
                          headers: {
                            'Content-type' : 'application/json'
                          },
                          body: JSON.stringify(client)
                          })
  const data =await res.json()
  return data     

  // Delete client
const deleteClient = async (id) => {

  await fetch(`http://localhost:5000/clients/${id}`, {method: 'DELETE'})

  // UI deletation
  //setClients(clients.filter((client) => client.id !== id))
}


//
const UpdatePassword = async (id, password) => {

  const client = await getClientByID(id)
  const updatedClient = {...client, password: password}//an event
  const res = await fetch(`http://localhost:5000/clients/${id}`, {method: 'PUT',
                                                                headers: {
                                                                  'Content-type': 'application/json'
                                                                },
                                                                body: JSON.stringify(updatedClient)
                                                               })

  const data = await res.json()
  return data
  //setClients(clients.map(
  //  (client)=> client.id ===id ? 
  //    {...client, reminder: data.reminder} : 
  //    client
  //  ))
  }
}*/