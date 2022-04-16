import ClientNavbar from '../layouts/navbar/ClientNavbar.js';
import {Container} from 'react-bootstrap'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import ProfilePreview from '../components/profile/ProfilePreview.js';
import ClientProfilePreview from '../components/profile/ClientProfilePreview.js';

export default function ClientBase(){

    // TODO: logged user ID
    
    const profile = <ProfilePreview profileComponent={<ClientProfilePreview id={3} />}/>
    
    return (
        <Router>
        <div>
            <body>
            <ClientNavbar/>
            <Container>
                <Routes>
                    {/* For other's Profile page */}
                <Route path="/profile" element={profile} /> 
                </Routes>
            </Container>
            </body>
        </div>
        </Router>
    );
  
}