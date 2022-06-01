import React from 'react';
import {faChevronCircleRight, faCogs, faUser, faCalendarDays} from '@fortawesome/free-solid-svg-icons'
import {NavDropdown} from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { getLogged } from '../../services/api/LoginApi.js';
import {useState, useEffect} from 'react';

export default function ProfileDropdown({userId}){

    const profileTitle = <span><FontAwesomeIcon icon={faUser} /> Profile</span>;

    return <NavDropdown title={profileTitle}> 
                <NavDropdown.Item href="/profile"><FontAwesomeIcon icon={faUser} /> My profile</NavDropdown.Item> 
                <NavDropdown.Item href="/settings"><FontAwesomeIcon icon={faCogs} /> Settings</NavDropdown.Item>
                <NavDropdown.Item href="/calendar"><FontAwesomeIcon icon={faCalendarDays} /> Calendar</NavDropdown.Item>
                <NavDropdown.Item href="/logout"><FontAwesomeIcon icon={faChevronCircleRight} /> Logout</NavDropdown.Item>
            </NavDropdown>
}