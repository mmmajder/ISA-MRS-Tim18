import React, { useState, useEffect, useRef } from 'react';
import { AccountBox } from "./components/forms/Login";
import styled from "styled-components";
import '../src/assets/styles/login.css'
import {BrowserRouter as Router, Route, Routes, Navigate, Outlet} from 'react-router-dom';
import RenterBase from './layouts/RenterBase';
import { getRole, getToken } from './services/AuthService/AuthService';
import ClientNavbar from "./layouts/navbar/ClientNavbar"
import ProfilePreview from './components/profile/ProfilePreview';
import ClientProfilePreview from './components/profile/ClientProfilePreview';
import UpdateClientProfile from './components/forms/UpdateClientProfile';
import ClientBase from './layouts/ClientBase';

const AppContainer = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;
function App() {
  const [user, setUser] = React.useState(getRole());

  const handleLogout = () => setUser(null);
  const handleLogin = (user) => {setUser(user); };  
  const login =<AppContainer> <AccountBox handleLogin={handleLogin}/> </AppContainer>;

  const profile = <ProfilePreview profileComponent={<ClientProfilePreview />}/>
  const updateProfile = <UpdateClientProfile/> 
  return  (<Router>
            <Routes>
              <Route path='welcome/' element={<ProtectedRoute isAllowed={user==="ResortRenter" || user==='Client'}>  
                                                      {user==="ResortRenter" && <RenterBase handleLogout={handleLogout}/> } 
                                                      {user==="Client" && <ClientBase handleLogout={handleLogout}/> } 
                                                </ProtectedRoute>}>
                                        
                  <Route path="/welcome/profile" element={profile} /> 
                  <Route path="/welcome/settings" element={updateProfile} />
              </Route>
              
              <Route path="login" element={login} />
              <Route index element={login} />
              <Route path="*" element={<h1>Sorry, this page is not available :( </h1>} />
            </Routes>
          
          </Router>);
        
}
const ProtectedRoute = ({ isAllowed, redirectPath = '/login', children}) => {
  if (!isAllowed) {
    return <Navigate to={redirectPath} replace />;
  }

  return children// ? children : <Outlet />;
};


function LayoutsWithNavbar() {
  return (
    <>
      <ClientNavbar />

      {/* This Outlet is the place in which react-router will render your components that you need with the navbar */}
      <Outlet /> 
    </>
  );
}

export default App;
