import React from 'react';
import { faHome, faHouseChimney, faPlus, faBook, faChartLine, faSearch} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import '../../assets/styles/nav.css';
import '../../assets/styles/style.css';
import {Nav, Navbar, NavDropdown} from 'react-bootstrap';
import ProfileDropdown from './ProfileDropdown.js';
import UsersOffersDropdown from './UsersOffersDropdown';

export default function ResortRenterNavbar({userType, userId}){
    const logo = require('../../assets/images/island_logo.png')
    console.log(userType)

    return <Navbar bg="darkBlue" variant="dark" sticky='top' expand="md" collapseOnSelect> 
        <Navbar.Brand ><img src={logo}  className="brand" alt="logo" /> Hakuna Matata</Navbar.Brand>
        <Navbar.Toggle />
        <Navbar.Collapse className="ps-2">
            <Nav className="ms-auto">
                <Nav.Link href="/home"><FontAwesomeIcon icon={faSearch} /> Search</Nav.Link>
                <UsersOffersDropdown userType={userType}/>
                <ProfileDropdown userId={userId} userType={userType}/>
            </Nav>
        </Navbar.Collapse>
    </Navbar>
}