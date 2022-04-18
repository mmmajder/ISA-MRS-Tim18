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

function App() {
  const client = false;
  if(client){
    return <ClientBase />
  }
  else{
    const resortForm = <CreateResortForm />
    localStorage.setItem("userId", 2)
    const profile = <ProfilePreview profileComponent={<ProfileInfoBlock id={localStorage.getItem("userId")}/>}/>
    const resortView = <ResortDetailedView />
    const calendar = <Calendar/>
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
              </Routes>
            </Container>
          </body>
        </div>
      </Router>
    );
  }
}

export default App;
