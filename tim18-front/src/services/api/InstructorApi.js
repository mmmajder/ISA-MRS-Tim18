import {api} from "../Configs.js"

export async function getInstructorByID(id){
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

export async function updateInstructor(instructorData){
  try {
      const responseData = await api.put(`/renters`, instructorData);
      return responseData;
  } catch (err) {
      console.log(err.message);
      return err.message
  }
}

export async function deleteInstructor(instructorData){
  try {
      const responseData = await api.put(`/renters/delete`, instructorData);
      return responseData;
  } catch (err) {
      console.log(err.message);
      return err.message
  }
}