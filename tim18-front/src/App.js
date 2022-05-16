
/*import React from 'react';
import ResortRenterNavbar from './layouts/navbar/RessortRenterNavbar.js';
import CreateAssetForm from './components/forms/CreateAssetForm.js'
import {Container} from 'react-bootstrap'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import ProfilePreview from './components/profile/ProfilePreview.js';
import ClientBase from './layouts/ClientBase.js';

import Calendar from './components/calendar/Calendar.js';
import UpdateRenter from './components/forms/UpdateInstructorProfileForm.js';
import { faListSquares } from '@fortawesome/free-solid-svg-icons';
import AssetList from './components/asset/AssetsList.js';
import UpdateResortForm from './components/forms/UpdateResortForm.js';
import { CalendarContent } from '@fullcalendar/react';
import CalendarAsset from './components/forms/calendar/CalendarAsset.js';
import AssetDetailedView from './components/asset/AssetDetailedView.js';
import CreateForm from './components/forms/CreateForm.js';
import UpdateForm from './components/forms/UpdateForm.js';
import AssetsPreview from './components/asset/AssetsPreview.js';*/
import AssetDetailedView from './components/asset/AssetDetailedView.js';
import CreateForm from './components/forms/CreateForm.js';
import UpdateForm from './components/forms/UpdateForm.js';
import AssetsPreview from './components/asset/AssetsPreview.js';
import CalendarAsset from './components/forms/calendar/CalendarAsset.js';
import UpdateRenter from './components/forms/UpdateInstructorProfileForm.js';
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
import Calendar from './components/calendar/Calendar';
import ProfileInfoBlock from './components/profile/ProfileInfo';
import ResortRenterNavbar from './layouts/navbar/RessortRenterNavbar';
import AdminRegistrationReq from './components/admin/AdminRegistrationReq.js';

const AppContainer = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;
function App() {

  // const client = false;
  //  const instructor = true;

  // const user = {
  //   id: "2",
  //   type: "BOAT_RENTER"
  //   // type: "renter"
  // }

// "CLIENT"
// "BOAT_RENTER":
// "FISHERMAN":
// "RESORT_RENTER":

  // localStorage.setItem("userType", user.type)
  // localStorage.setItem("userId", user.id)

  // if(client){
  //   return <ClientBase />
  // }
  // else{
    //const resortForm = <CreateForm userType={localStorage.getItem('userType')} />
    /*const profile = <ProfilePreview userId={localStorage.getItem("userId")}/>
    const resortView = <AssetDetailedView />
    const assetList = <AssetsPreview userType={localStorage.getItem('userType')}/>
    const assetUpdate = <UpdateForm />
    const calendar = <Calendar id={localStorage.getItem("userId")}/>
    const assetCalendar = <CalendarAsset/>
    let updateProfile = <UpdateRenter id={localStorage.getItem("userId")}/>*/
    // return (
    //   <Router>
    //     <div>
    //       <body>
    //         {/* <ResortRenterNavbar/> */}
    //         <ResortRenterNavbar userType={localStorage.getItem('userType')}/>
    //         <Container>
    //           <Routes>
    //               {/* Creating/Registrating Resorts/Boats */}
    //             <Route path="/createResort" element={resortForm} /> 
    //               {/* For other's Profile page */}
    //             <Route path="/profile" element={profile} /> 
    //             <Route exact path="/resorts" element={assetList} /> 
    //             <Route path="/resorts/:id" element={resortView} /> 
    //             <Route path="/resorts/update/:id" element={assetUpdate} />
    //             <Route path="/calendar" element={calendar}/>
    //             <Route path="/settings" element={updateProfile} />
    //             <Route path="/calendarAsset" element={assetCalendar}/>
    //           </Routes>
    //         </Container>
    //       </body>
    //     </div>
    //   </Router>
    // );

  const [user, setUser] = React.useState(getRole());

  const handleLogout = () => {setUser(null); };  
  const handleLogin = (user) => {setUser(user); };  
  const login =<AppContainer> <AccountBox handleLogin={handleLogin}/> </AppContainer>;
  
  const resortForm = <CreateForm  />
  const resortView = <AssetDetailedView />
  const assetList = <AssetsPreview />
  const assetUpdate = <UpdateForm />
  const calendar = <Calendar />
  const assetCalendar = <CalendarAsset/>
  const adminRegistrationReq = <AdminRegistrationReq/>
  const home = <></>

  return  (<Router>
            <Routes>
              <Route path='' element={<ProtectedRoute isAllowedUser={user}> {chooseNavbar(user)} </ProtectedRoute>}>
                <Route path="/home" element={home} /> 
                {/* For other's Profile page */}
                <Route path="/profile" element={ChooseProfile(user)} /> 
                <Route path="/settings" element={ChooseSettings(user)} />

                  {/* Creating/Registrating Resorts/Boats */}
                <Route path="createResort" element={resortForm} /> 
                <Route path="/calendar" element={calendar}/>
                <Route path="/logout" element={<Logout handleLogout={handleLogout}/>} />
                <Route exact path="/resorts" element={assetList} /> 
                <Route path="/resorts/:id" element={resortView} /> 
                <Route path="/resorts/update/:id" element={assetUpdate} />
                <Route path="/calendar" element={calendar}/>
                <Route path="/calendarAsset" element={assetCalendar}/>
                <Route path="/adminRegistrationReq" element={adminRegistrationReq}/>
              </Route>
              
              <Route path="login" element={login} />
              <Route index element={login} />
              <Route path="*" element={<h1>Sorry, this page is not available :( </h1>} />
            </Routes>
          
          </Router>);
        
}
const ProtectedRoute = ({ isAllowedUser, redirectPath = '/login', children}) => {
  if (isAllowedUser==="ResortRenter" || isAllowedUser==='Client' ||
      isAllowedUser==="BoatRenter" || isAllowedUser==='FishingInstructor' || isAllowedUser==="Admin") {
        
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
  if (userType==="BoatRenter" ||userType === 'FishingInstructor' || userType === 'ResortRenter' || userType === 'Admin'){
    return <UpdateRenter />
  }
}

function chooseNavbar(userType){
  let navBar = null
  if(userType === "Client"){
    navBar =  <ClientNavbar />
  }
  else if(userType==="BoatRenter" || userType === "ResortRenter" || userType === 'FishingInstructor' || userType === 'Admin'){
    navBar =  <ResortRenterNavbar userType={userType}/>
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
