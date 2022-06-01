import {api} from "../Configs.js"

export async function saveNewPassword(user, password){
  
    await api.put(`/admin/savePassword/${user.id}`, JSON.stringify(password))
              .then((responseData) => { console.log(responseData.data)})
              .catch(()=> {});

   
}


