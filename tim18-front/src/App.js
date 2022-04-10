import ResortRenterNavbar from './layouts/navbar/RessortRenterNavbar.js';
import CreateResortForm from './components/CreateResortForm.js'
import {Container} from 'react-bootstrap'

function App() {
  return (
    <div>
      <ResortRenterNavbar/>
      <body>
        <Container>
          <CreateResortForm />
        </Container>
      </body>
    </div>
  );
}

export default App;
