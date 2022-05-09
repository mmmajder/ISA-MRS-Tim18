import React, { useState, useEffect, useRef } from 'react';
import { AccountBox } from "./components/forms/Login";
import styled from "styled-components";
import '../src/assets/styles/login.css'
import {BrowserRouter as Router, Route, Routes, Navigate, Outlet} from 'react-router-dom';
import { getRole, getToken, setRole, setToken } from './services/AuthService/AuthService';
import ClientNavbar from "./layouts/navbar/ClientNavbar"
import ProfilePreview from './components/profile/ProfilePreview';
import ClientProfilePreview from './components/profile/ClientProfilePreview';
import UpdateClientProfile from './components/forms/UpdateClientProfile';
import CreateResortForm from './components/forms/CreateResortForm';
import ResortDetailedView from './components/asset/ResortDetailedView';
import Calendar from './components/calendar/Calendar';
import ProfileInfoBlock from './components/profile/ProfileInfo';
import UpdateInstructorProfileForm from './components/forms/UpdateInstructorProfileForm'
import ResortRenterNavbar from './layouts/navbar/RessortRenterNavbar';

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

  const handleLogout = () => {setUser(null); };  
  const handleLogin = (user) => {setUser(user); };  
  const login =<AppContainer> <AccountBox handleLogin={handleLogin}/> </AppContainer>;
  
  const resortForm = <CreateResortForm />  
  const resortView = <ResortDetailedView />
  const calendar = <Calendar/>

  return  (<Router>
            <Routes>
              <Route path='welcome/' element={<ProtectedRoute isAllowedUser={user}> {chooseNavbar(user)} </ProtectedRoute>}>
                                        
                  {/* For other's Profile page */}
                  <Route path="/welcome/profile" element={ChooseProfile(user)} /> 
                  <Route path="/welcome/settings" element={ChooseSettings(user)} />

                   {/* Creating/Registrating Resorts/Boats */}
                  <Route path="/welcome/createResort" element={resortForm} /> 
                  <Route path="/welcome/resorts" element={resortView} /> 
                  <Route path="/welcome/calendar" element={calendar}/>
                  <Route path="/welcome/logout" element={<Logout handleLogout={handleLogout}/>} />
              </Route>
              
              <Route path="login" element={login} />
              <Route index element={login} />
              <Route path="*" element={<h1>Sorry, this page is not available :( </h1>} />
            </Routes>
          
          </Router>);
        
}
const ProtectedRoute = ({ isAllowedUser, redirectPath = '/login', children}) => {
  if (isAllowedUser==="ResortRenter" || isAllowedUser==='Client' ||
      isAllowedUser==="BoatRenter" || isAllowedUser==='FishingInstructor') {
        
    return children;
  }
  return <Navigate to={redirectPath} replace />;
  
};

function ChooseProfile(userType) {
  const profilePreview = userType === 'Client' ? <ClientProfilePreview /> : <ProfileInfoBlock/>
  return <ProfilePreview profileComponent={profilePreview} />
}

function ChooseSettings(userType){
  if (userType === 'Client'){
    return <UpdateClientProfile/> 
  }
  if (userType==="BoatRenter" ||userType === 'FishingInstructor' || userType === 'ResortRenter'){
    return <UpdateInstructorProfileForm/> 
  }
}

function chooseNavbar(userType){
  let navBar = null
  if(userType === "Client"){
    navBar =  <ClientNavbar />
  }
  else if(userType==="BoatRenter" || userType === "ResortRenter" || userType === 'FishingInstructor'){
    navBar =  <ResortRenterNavbar/>
  }
  return (
    <div>
        {navBar}
        {/* This Outlet is the place in which react-router will render your components that you need with the navbar */}
        <Outlet /> 
        
    </div>
  );
}

const Logout = ({handleLogout}) => {
  setRole(null); 
  setToken(null);
  handleLogout();
  return <Navigate to='/login' replace />;
}

export default App;
