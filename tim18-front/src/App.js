import ResortRenterNavbar from './layouts/navbar/RessortRenterNavbar.js';
import CreateResortForm from './components/forms/CreateResortForm.js'
import ProfileInfoBlock from './components/profile/ProfileInfoBlock.js';
import {Container} from 'react-bootstrap'
import { Row, Col  } from 'react-bootstrap';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import ResortDetailedView from './components/asset/ResortDetailedView.js';

function App() {
  const resortForm = <CreateResortForm />
  const profile = <Row className="pt-5">
                    <Col sm='1'/>
                    <Col sm='3'>
                      <ProfileInfoBlock/>
                    </Col>
                    <Col sm='8'/>
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
