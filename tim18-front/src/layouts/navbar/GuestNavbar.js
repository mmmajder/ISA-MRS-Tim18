import React from 'react'
import { faHome, faSearch,faUserLock, faChevronCircleRight, faCogs, faUser, faCalendarDays, faCircleQuestion} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import '../../assets/styles/nav.css';
import '../../assets/styles/style.css';
import {Nav, Navbar, NavDropdown} from 'react-bootstrap';
import ProfileDropdown from './ProfileDropdown.js';

export default function GuestNavbar(){
    const logo = require('../../assets/images/island_logo.png')
    const profileTitle = <span><FontAwesomeIcon icon={faUser} /> Profile</span>;
    return <Navbar bg="darkBlue" variant="dark" sticky='top' expand="md" collapseOnSelect> 
        <Navbar.Brand ><img src={logo}  className="brand" alt="logo" /> Hakuna Matata</Navbar.Brand>
        <Navbar.Toggle />
        <GuestNote/>
        <Navbar.Collapse className="ps-2">
            <Nav className="ms-auto">
                <Nav.Link href="/home"><FontAwesomeIcon icon={faHome} /> Home</Nav.Link>
                <Nav.Link href="/resorts/all"><FontAwesomeIcon icon={faSearch} /> Search</Nav.Link>
                <Nav.Link href="/logout"><FontAwesomeIcon icon={faChevronCircleRight} /> Login/Register</Nav.Link>
                
                <Nav.Link href="#"><FontAwesomeIcon icon={faCircleQuestion} /> Help </Nav.Link>
            </Nav>
        </Navbar.Collapse>
    </Navbar>
}

const GuestNote = () => {
    return (<div style={{'color': 'white'}}>
                <FontAwesomeIcon icon={faUserLock} />
                You are currently here as a guest, we hope you enjoy and join us. 
            </div>);
}