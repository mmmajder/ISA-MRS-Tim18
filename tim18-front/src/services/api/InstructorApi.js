import {getApiCall} from "../Configs.js"

export async function getInstructorByID(id){
  try {
      const responseData = await getApiCall().get(`/users/${id}`, {
        headers: {
            'Content-Type': 'application/json',
        }
    });
      return responseData;
  } catch (err) {
      return err.message
  }
}

export async function getRenter(id){
  try {
      const responseData = await getApiCall().get(`/users/${id}`);
      return responseData;
  } catch (err) {
      return err.message
  }
}

export async function updateInstructor(instructorData){
  try {
      const responseData = await getApiCall().put(`/renters`, instructorData);
      return responseData;
  } catch (err) {
      return err.message
  }
}

export async function deleteInstructor(instructorData){
  try {
      const responseData = await getApiCall().put(`/renters/delete`, instructorData);
      return responseData;
  } catch (err) {
      return err.message
  }
}