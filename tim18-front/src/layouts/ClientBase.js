import React from 'react'
import ClientNavbar from '../layouts/navbar/ClientNavbar.js';
import {Container} from 'react-bootstrap'
import {BrowserRouter as Router, Route, Routes, Outlet} from 'react-router-dom';
import ProfilePreview from '../components/profile/ProfilePreview.js';
import ClientProfilePreview from '../components/profile/ClientProfilePreview.js';
import UpdateClientProfile from '../components/forms/UpdateClientProfile.js'
import { Form, Row, Col, Button} from 'react-bootstrap';

export default function ClientBase(){
    return (
        <div>
            <ClientNavbar />
            {/* This Outlet is the place in which react-router will render your components that you need with the navbar */}
            <Outlet /> 
            
        </div>
    );
}