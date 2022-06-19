import React from 'react'
import { faHome, faSearch, faSuitcase, faBorderAll, faSailboat, faHouseChimney, faFishFins, faBell, faCircleQuestion} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import '../../assets/styles/nav.css';
import '../../assets/styles/style.css';
import {Nav, Navbar, NavDropdown} from 'react-bootstrap';
import ProfileDropdown from './ProfileDropdown.js';

export default function ClientNavbar({userId}){
    const logo = require('../../assets/images/island_logo.png')
    // mySubscriptions -- na profilu(samo ja vidim)? bell
    // reviews i gave (na istoriji rezervacija prikazem?), 
    // reviews i was given -- isto na profilu?

    return <Navbar bg="darkBlue" variant="dark" sticky='top' expand="md" collapseOnSelect> 
        <Navbar.Brand ><img src={logo}  className="brand" alt="logo" /> Hakuna Matata</Navbar.Brand>
        <Navbar.Toggle />
        <Navbar.Collapse className="ps-2">
            <Nav className="ms-auto">
                <Nav.Link href="/resorts/all"><FontAwesomeIcon icon={faSearch} /> Search</Nav.Link>
                <Nav.Link href="/allReservations"><FontAwesomeIcon icon={faSuitcase} /> My Reservations</Nav.Link>
                <Nav.Link href="/subscriptions"><FontAwesomeIcon icon={faBell} /> Subscriptions </Nav.Link>

                <ProfileDropdown userId={"client"}/>
            </Nav>
        </Navbar.Collapse>
    </Navbar>

}