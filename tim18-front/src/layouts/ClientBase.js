import React from 'react'
import ClientNavbar from '../layouts/navbar/ClientNavbar.js';
import {Container} from 'react-bootstrap'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import ProfilePreview from '../components/profile/ProfilePreview.js';
import ClientProfilePreview from '../components/profile/ClientProfilePreview.js';
import UpdateClientProfile from '../components/forms/UpdateClientProfile.js'
import { Form, Row, Col, Button} from 'react-bootstrap';

export default function ClientBase(){

    // TODO: logged user ID
    const id = 3;
    const profile = <ProfilePreview profileComponent={<ClientProfilePreview id={id} />}/>
    const updateProfile = <UpdateClientProfile id={id}/>
    return (
        <Router>
        <div>
            <body>
            <ClientNavbar/>
            <Routes>
                {/* For other's Profile page */}
            <Route path="/profile" element={profile} /> 
            <Route path="/settings" element={updateProfile} /> 
            </Routes>
            
            </body>
        </div>
        </Router>
    );
}