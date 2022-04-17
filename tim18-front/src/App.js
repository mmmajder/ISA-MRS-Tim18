import React from 'react';
import ResortRenterNavbar from './layouts/navbar/RessortRenterNavbar.js';
import CreateResortForm from './components/forms/CreateResortForm.js'
import ProfileInfoBlock from './components/profile/ProfileInfoBlock.js';
import {Container} from 'react-bootstrap'
import { Row, Col  } from 'react-bootstrap';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import ResortDetailedView from './components/asset/ResortDetailedView.js';
import createUtilityClassName from 'react-bootstrap/esm/createUtilityClasses';

function App() {

  const user1 = {
    firstName: "Zema",
    lastName: "Moreplovac",
    address: "Topolska 18",
    city: "Backa Topola",
    state: "Srbija",
    phoneNumber: "064 1231231",
    userType: "FISHINGINSTRUCTOR",
    birthDate:  "23.11.2000.",
    profilePic: "../assets/images/blue_profile_pic.jpg",
    mark: 4.2
  }


  const resortForm = <CreateResortForm />
  const profile = <Row className="pt-5">
                    <Col sm='3'>
                      <ProfileInfoBlock id={2}/>
                    </Col>
                    <Col sm='9'/>
                  </Row>
  const resortView = <ResortDetailedView />
        
  return (
    <Router>
      <div>
        <body>
          <ResortRenterNavbar/>
          <Container>
            <Routes>
                {/* Creating/Registrating Resorts/Boats */}
              <Route path="/createResort" element={resortForm} /> 
                {/* For other's Profile page */}
              <Route path="/profile" element={profile} /> 
              <Route path="/resorts" element={resortView} /> 
            </Routes>
          </Container>
        </body>
      </div>
    </Router>
  );
}

export default App;
