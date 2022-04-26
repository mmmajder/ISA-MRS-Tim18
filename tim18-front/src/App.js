import React from 'react';
import ResortRenterNavbar from './layouts/navbar/RessortRenterNavbar.js';
import CreateResortForm from './components/forms/CreateResortForm.js'
import {Container} from 'react-bootstrap'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import ResortDetailedView from './components/asset/ResortDetailedView.js';
import ProfilePreview from './components/profile/ProfilePreview.js';
import ClientBase from './layouts/ClientBase.js';
import ProfileInfoBlock from './components/profile/ProfileInfoBlock.js';
import Calendar from './components/calendar/Calendar.js';
import UpdateInstructorProfileForm from './components/forms/UpdateInstructorProfileForm.js';
import { AccountBox } from "./components/forms/Login";
import styled from "styled-components";
import '../src/assets/styles/login.css'

const AppContainer = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;
function App() {
  const client = true;  //<ClientBase />
  const instructor = true;
  if(client){
    return  <AppContainer>
              <AccountBox />
          </AppContainer>
  }
  else{
    const resortForm = <CreateResortForm />
    localStorage.setItem("userId", 2)
    const profile = <ProfilePreview profileComponent={<ProfileInfoBlock id={localStorage.getItem("userId")}/>}/>
    const resortView = <ResortDetailedView />
    const calendar = <Calendar/>
    let updateProfile;
    if (instructor){
      updateProfile = <UpdateInstructorProfileForm id={localStorage.getItem("userId")}/>
    }
    return (
      <Router>
        <div>
          <body>
            {/* <ResortRenterNavbar/> */}
            <ResortRenterNavbar/>
            <Container>
              <Routes>
                  {/* Creating/Registrating Resorts/Boats */}
                <Route path="/createResort" element={resortForm} /> 
                  {/* For other's Profile page */}
                <Route path="/profile" element={profile} /> 
                <Route path="/resorts" element={resortView} /> 
                <Route path="/calendar" element={calendar}/>
                <Route path="/settings" element={updateProfile} />
              </Routes>
            </Container>
          </body>
        </div>
      </Router>
    );
  }
}

export default App;
