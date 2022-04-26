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

export async function deleteInstructor(instructorData){
  try {
      const responseData = await api.put(`/fishingInstructors/delete`, instructorData);
      return responseData;
  } catch (err) {
      console.log(err.message);
      return err.message
  }
}

export async function createDeleteRequest(id, reason, callback){
  await api.post(`/clients/${id}`, reason)
           .then((responseData) => {callback(responseData.data)})
           .catch(()=> {callback(false)});
}