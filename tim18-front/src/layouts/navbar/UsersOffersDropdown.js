import React from 'react'
import {Nav, Navbar, NavDropdown} from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faHome, faHouseChimney, faPlus, faBook, faChartLine, faFishFins, faShip, faAnchor} from '@fortawesome/free-solid-svg-icons'

const UsersOffersDropdown = ({userType}) => {
    let assetsName;
    let assetsIcon;
    
    console.log("looo "+userType);

    switch (userType) {
        case "FishingInstructor":
            assetsName = "Adventure";
            assetsIcon = faFishFins;
            break;
        case "ResortRenter":
            assetsName = "Resort";
            assetsIcon = faHouseChimney;
            break;
        case "BoatRenter":
            assetsName = "Boat";
            assetsIcon = faShip;
            break;
        default:
            break;
    }

  return (
    <NavDropdown title={<span><FontAwesomeIcon icon={assetsIcon} /> {assetsName}s</span>}> 
        <NavDropdown.Item href="/resorts"><FontAwesomeIcon icon={assetsIcon} /> My {assetsName}s</NavDropdown.Item> 
        <NavDropdown.Item href="/createResort"><FontAwesomeIcon icon={faPlus} /> Add new {assetsName}</NavDropdown.Item>
        <NavDropdown.Item href="#"><FontAwesomeIcon icon={faBook} /> {assetsName}s history</NavDropdown.Item>
        <NavDropdown.Item href="#"><FontAwesomeIcon icon={faChartLine} /> Reports</NavDropdown.Item>
    </NavDropdown>
    );
}

export default UsersOffersDropdown