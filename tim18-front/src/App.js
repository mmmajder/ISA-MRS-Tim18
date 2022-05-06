import React from 'react';
import ResortRenterNavbar from './layouts/navbar/RessortRenterNavbar.js';
import CreateAssetForm from './components/forms/CreateAssetForm.js'
import {Container} from 'react-bootstrap'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import ResortDetailedView from './components/asset/ResortDetailedView.js';
import ProfilePreview from './components/profile/ProfilePreview.js';
import ClientBase from './layouts/ClientBase.js';
import ProfileInfoBlock from './components/profile/ProfileInfoBlock.js';
import Calendar from './components/calendar/Calendar.js';
import UpdateInstructorProfile from './components/forms/UpdateInstructorProfileForm.js';
import { faListSquares } from '@fortawesome/free-solid-svg-icons';
import { CalendarContent } from '@fullcalendar/react';
import CalendarAsset from './components/forms/calendar/CalendarAsset.js';

function App() {
  const client = false;
  const instructor = true;

  const user = {
    id: "1",
    type: "instructor"
  }

  localStorage.setItem("userType", user.type)
  if(client){
    return <ClientBase />
  }
  else{
    localStorage.setItem("userId", 2)
    const resortForm = <CreateAssetForm userType={localStorage.getItem('userType')} />
    const profile = <ProfilePreview profileComponent={<ProfileInfoBlock id={localStorage.getItem("userId")}/>}/>
    const resortView = <ResortDetailedView />
    const calendar = <Calendar id={localStorage.getItem("userId")}/>
    const assetCalendar = <CalendarAsset/>
    let updateProfile;
    if (instructor){
      updateProfile = <UpdateInstructorProfile id={localStorage.getItem("userId")}/>
    }
    return (
      <Router>
        <div>
          <body>
            {/* <ResortRenterNavbar/> */}
            <ResortRenterNavbar userType={localStorage.getItem('userType')}/>
            <Container>
              <Routes>
                  {/* Creating/Registrating Resorts/Boats */}
                <Route path="/createResort" element={resortForm} /> 
                  {/* For other's Profile page */}
                <Route path="/profile" element={profile} /> 
                <Route path="/resorts" element={resortView} /> 
                <Route path="/calendar" element={calendar}/>
                <Route path="/settings" element={updateProfile} />
                <Route path="/calendarAsset" element={assetCalendar}/>
              </Routes>
            </Container>
          </body>
        </div>
      </Router>
    );
  }
}

export default App;
