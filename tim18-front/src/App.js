import ResortRenterNavbar from './layouts/navbar/RessortRenterNavbar.js';
import CreateResortForm from './components/CreateResortForm.js'
import ProfileInfoBlock from './components/profile/ProfileInfoBlock.js';
import {Container} from 'react-bootstrap'
import { Row, Col  } from 'react-bootstrap';

function App() {
  return (
    <div>
      <ResortRenterNavbar/>
      <body>
        <Container>
          {/* Creating/Registrating Resorts/Boats */}
          <CreateResortForm />
          {/* For other's Profile page */}
          <Row>
            <Col sm='1'>
            </Col>
            <Col sm='3'>
              <ProfileInfoBlock/>
            </Col>
            <Col sm='8'>
            </Col>
          </Row>
        </Container>
      </body>
    </div>
  );
}

export default App;
