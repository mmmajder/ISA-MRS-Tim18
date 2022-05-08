import React from 'react';
import ResortRenterNavbar from './layouts/navbar/RessortRenterNavbar.js';
import CreateAssetForm from './components/forms/CreateAssetForm.js'
import {Container} from 'react-bootstrap'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import ProfilePreview from './components/profile/ProfilePreview.js';
import ClientBase from './layouts/ClientBase.js';

import Calendar from './components/calendar/Calendar.js';
import UpdateInstructorProfile from './components/forms/UpdateInstructorProfileForm.js';
import { faListSquares } from '@fortawesome/free-solid-svg-icons';
import AssetList from './components/asset/AssetsList.js';
import UpdateResortForm from './components/forms/UpdateResortForm.js';
import { CalendarContent } from '@fullcalendar/react';
import CalendarAsset from './components/forms/calendar/CalendarAsset.js';
import AssetDetailedView from './components/asset/AssetDetailedView.js';

function App() {
  const client = false;
  const instructor = true;

  const user = {
    id: "1",
    type: "RESORT_RENTER"
    // type: "renter"
  }

// "BOAT_RENTER":
// "FISHERMAN":
// "RESORT_RENTER":

  localStorage.setItem("userType", user.type)
  localStorage.setItem("userId", user.id)

  if(client){
    return <ClientBase />
  }
  else{
    const resortForm = <CreateAssetForm userType={localStorage.getItem('userType')} />
    const profile = <ProfilePreview userId={localStorage.getItem("userId")}/>
    const resortView = <AssetDetailedView />
    const assetList = <AssetList />
    const resortUpdate = <UpdateResortForm />
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
                <Route exact path="/resorts" element={assetList} /> 
                <Route path="/resorts/:id" element={resortView} /> 
                <Route path="/resorts/update/:id" element={resortUpdate} />
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
