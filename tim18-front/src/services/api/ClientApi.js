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

export async function getAllClients(){
  try {
      const responseData = await api.get(`/clients`);
      return responseData;
  } catch (err) {
      console.log(err.message);
      return err.message
  }
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
export function createDeleteRequest(id, reason){
  api.post(`/clients/${id}`, reason)
     .then((responseData) => {return responseData})
     .catch(()=> {return false});
}

export async function updateClient(clientData){
  try {
      const clientDataJSON = JSON.stringify(clientData);
      const responseData = await api.put(`/clients`, clientDataJSON);
      return responseData;
  } catch (err) {
      console.log(err.message);
      return false;
  }
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