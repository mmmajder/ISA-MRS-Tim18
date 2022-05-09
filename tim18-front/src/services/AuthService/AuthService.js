export const getHeaders = () => {
  const token = getToken();
  if(!!token){
    return {
      "Authorization" : `Bearer ${token}`,
      "Content-Type": "application/json"
    };
  }
  return {
    "Content-Type": "application/json"
  };
}



export const setToken = (token) => {
   localStorage.setItem("jwt", JSON.stringify(token));
}
export const getToken = () => {
    let text = localStorage.getItem("jwt");
    if(!!text){
      let obj = JSON.parse(text);
      if(!!obj){
        return obj.accessToken;
      }
    }
    return '';
}


export const setRole = (role) => {
  localStorage.setItem("role", role);
}
export const getRole = () => {
   return localStorage.getItem("role");
}

