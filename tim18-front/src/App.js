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
import './assets/styles/style.css';
import {Container} from 'react-bootstrap'
import { getLogged } from './services/api/LoginApi.js';
import GuestNavbar from './layouts/navbar/GuestNavbar';
import { Confirmation } from './components/forms/Login/Confirmation';
import  AllReservations from './components/reservations/AllReservations';
import AdminRegistrationReq from './components/admin/AdminRegistrationReq.js';
import AdminDeletionRequestsPreview from './components/admin/AdminDeletionRequestsPreview.js';
import UpdateProfilePhoto from './components/forms/UpdateProfilePhoto.js';
import UpdateAssetPhotos from './components/forms/UpdateAssetPhotos.js';
import UpdateAssetPrice from  './components/forms/UpdateAssetPrice.js';
import AdminFinancialPreview from './components/admin/AdminFinancialPreview.js';
import AdminRegister from './components/admin/AdminRegister.js';
import Home from './components/forms/Home.js';
import AdminClientsComplaints from './components/admin/AdminClientsComplaints.js';
import Subscriptions from './components/reservations/Subscriptions';
import Reviews from './components/reservations/Reviews.js';
import MyProfile from './components/profile/MyProfile.js';
import OtherProfile from './components/profile/OtherProfile.js';
import AdminRentersComplaints from './components/admin/AdminRentersComplaints.js';

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
  const [userId, setUserId] = React.useState();

  const handleLogout = () => {setUser(null); localStorage.clear();};  
  const handleLogin = (user) => {setUser(user);};  
  const login =<AppContainer> <AccountBox handleLogin={handleLogin} /> </AppContainer>;
  
  const resortForm = <Container><CreateForm  /></Container>
  const resortView = <Container><AssetDetailedView /></Container>
  const assetList = <Container><AssetsPreview isSearch={false}/></Container>
  const assetListAll = <Container><AssetsPreview isSearch={true}/></Container>
  const assetUpdate = <Container><UpdateForm /></Container>
  const calendar = <Container><Calendar /></Container>
  const assetCalendar = <Container><CalendarAsset/></Container>
  const home = <Container><Home/></Container>
  const confirmation = <Container><Confirmation/></Container>
  const allReservations = <Container><AllReservations/></Container>
  const adminRegistrationReq = <Container><AdminRegistrationReq/></Container>
  const adminProfileDeletionReq = <Container><AdminDeletionRequestsPreview/></Container>
  const updateProfilePhoto = <Container><UpdateProfilePhoto /></Container>
  const updateAssetPhotos = <Container><UpdateAssetPhotos /></Container>
  const updateAssetPrice = <Container><UpdateAssetPrice /></Container>
  const adminFinancialPreview = <Container><AdminFinancialPreview/></Container>
  const adminRegister = <Container><AdminRegister/></Container>
  const adminClientsComplaints = <Container><AdminClientsComplaints/></Container>
  const SubscriptionList = <Container><Subscriptions /></Container>
  const reviews = <Container><Reviews/></Container>
  const adminRentersComplaints = <Container><AdminRentersComplaints/></Container>

  return  (<Router>
            {/* <ResortRenterNavbar userType={localStorage.getItem('userType')}/> */}
              <Routes>
                <Route path='' element={<ProtectedRoute isAllowedUser={user}>{chooseNavbar(user)} </ProtectedRoute>}>
                  <Route path="/home" element={home} /> 
                  {/* For other's Profile page */}
                  <Route exact path="/profile" element={<Container><MyProfile /></Container>} /> 
                  <Route path="/profile/:id" element={<Container><OtherProfile /></Container>} /> 
                  <Route path="/settings" element={<Container>{ChooseSettings(user)}</Container>} />
                  <Route path="/updateProfilePhoto" element={updateProfilePhoto} />

                    {/* Creating/Registrating Resorts/Boats */}
                  <Route path="createResort" element={resortForm} /> 
                  <Route path="/calendar" element={calendar}/>
                  <Route path="/logout" element={<Container><Logout handleLogout={handleLogout}/></Container>} />
                  <Route exact path="/resorts" element={assetList} /> 
                  <Route path="/resorts/all" element={assetListAll} /> 
                  <Route path="/resorts/:id" element={resortView} /> 
                  <Route path="/resorts/update/:id" element={assetUpdate} />
                  <Route path="/calendar" element={calendar}/>
                  <Route path="/calendarAsset" element={assetCalendar}/>
                  <Route path="/allReservations" element={allReservations}/>
                  <Route path="/adminRegistrationReq" element={adminRegistrationReq}/>
                  <Route path="/adminProfileDeletionReq" element={adminProfileDeletionReq}/>
                  <Route path="/updateAssetPhotos/:id" element={updateAssetPhotos} />
                  <Route path="/updateAssetPrice/:id" element={updateAssetPrice} />
                  <Route path="/adminLoyaltyProgram" element={adminFinancialPreview}/>
                  <Route path="/adminRegister" element={adminRegister}/>
                  <Route path="/adminClientsComplaints" element={adminClientsComplaints}/>
                  <Route path="/adminFinancialPreview" element={adminFinancialPreview}/>
                  <Route exact path="/subscriptions" element={SubscriptionList} /> 
                  <Route path="/reviews/:reservationId" element={reviews}/>
                  <Route path="/adminRentersComplaints" element={adminRentersComplaints}/>
                </Route>
                <Route path="login" element={login} />
                <Route path="verify/:code" element={confirmation} />
                <Route index element={login} />
                <Route path="*" element={<h1>Sorry, this page is not available :( </h1>} />
              </Routes>
          </Router>);
        
}
const ProtectedRoute = ({ isAllowedUser, redirectPath = '/login', children}) => {
  if (isAllowedUser==="ResortRenter" || isAllowedUser==='Client' ||isAllowedUser==='Guest' ||
      isAllowedUser==="BoatRenter" || isAllowedUser==='FishingInstructor' || isAllowedUser==="Admin") {

    return children;
  }

  return <Navigate to={redirectPath} replace />;
  
};

function ChooseSettings(userType){
  if (userType === 'Client'){
    return <UpdateClientProfile/> 
  }
  if (userType==="BoatRenter" ||userType === 'FishingInstructor' || userType === 'ResortRenter' || userType === 'Admin'){
    return <UpdateRenter />
  }
  return <></>;
}

function chooseNavbar(userType){
  console.log("choosenavbar"+userType);
  let navBar = <></>
  if(userType === "Client"){
    navBar =  <ClientNavbar />
  }
  else if(userType==="BoatRenter" || userType === "ResortRenter" || userType === 'FishingInstructor' || userType === 'Admin'){
    navBar =  <ResortRenterNavbar userType={userType}/>
  }
  else if(userType === 'Guest'){
    navBar = <GuestNavbar />
  }
  else{
    navBar = <></>
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
