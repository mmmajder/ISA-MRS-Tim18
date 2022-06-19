export const setToken = (token) => {
  if(token === null){
    localStorage.setItem("jwt", null);
  }
  else{
    localStorage.setItem("jwt", JSON.stringify(token.accessToken));
  }
}
export const getToken = () => {
   return JSON.parse(localStorage.getItem("jwt"));
}


export const setRole = (role) => {
  localStorage.setItem("role", role);
}
export const getRole = () => {
   return localStorage.getItem("role");
}

