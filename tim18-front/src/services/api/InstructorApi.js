import {api} from "../Configs.js"

export async function getInstructorByID(id){
  try {
      console.log("stigao")
      console.log(id)
      const responseData = await api.get(`/users/${id}`);
      console.log(responseData)
      return responseData;
  } catch (err) {
      console.log(err.message);
      return err.message
  }
}

export async function updateInstructor(instructorData){
  try {
      const responseData = await api.put(`/fishingInstructors`, instructorData);
      return responseData;
  } catch (err) {
      console.log(err.message);
      return err.message
  }
}


/*export async function getAllClients(){
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

export async function deleteClient(id){
  try {
      const responseData = await api.delete(`/clients/${id}`);
      return responseData;
  } catch (err) {
      console.log(err.message);
      return err.message
  }
}

export async function updateClient(clientData){
  try {
      const responseData = await api.put(`/clients`, clientData);
      return responseData;
  } catch (err) {
      console.log(err.message);
      return err.message
  }
}*/
