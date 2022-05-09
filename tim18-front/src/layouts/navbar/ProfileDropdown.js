import React from 'react';
import {faChevronCircleRight, faCogs, faUser, faCalendarDays} from '@fortawesome/free-solid-svg-icons'
import {NavDropdown} from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

export default function ProfileDropdown(){
    const profileTitle = <span><FontAwesomeIcon icon={faUser} /> Profile</span>;

    return <NavDropdown title={profileTitle}> 
                <NavDropdown.Item href="/welcome/profile"><FontAwesomeIcon icon={faUser} /> My profile</NavDropdown.Item> 
                <NavDropdown.Item href="/welcome/settings"><FontAwesomeIcon icon={faCogs} /> Settings</NavDropdown.Item>
                <NavDropdown.Item href="/welcome/calendar"><FontAwesomeIcon icon={faCalendarDays} /> Calendar</NavDropdown.Item>
                <NavDropdown.Item href="/welcome/logout"><FontAwesomeIcon icon={faChevronCircleRight} /> Logout</NavDropdown.Item>
            </NavDropdown>
}