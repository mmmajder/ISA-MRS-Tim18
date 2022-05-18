import React from 'react'
import {Nav, Navbar, NavDropdown} from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faHome, faHouseChimney, faPlus, faRightToBracket, faBook, faChartLine, faFishFins, faShip, faUserSlash, faAnchor, faCircleQuestion, faSackDollar, faBell, faUserPlus } from '@fortawesome/free-solid-svg-icons'

const UsersOffersDropdown = ({userType}) => {
    let assetsName;
    let assetsIcon;
    
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
        case "Admin":
            return (<Nav>
                <Nav.Link href="/adminRegistrationReq"><FontAwesomeIcon icon={faCircleQuestion} /> Registration requests</Nav.Link>
                <Nav.Link href="/adminProfileDeletionReq"><FontAwesomeIcon icon={faUserSlash} /> Profile delete requests</Nav.Link>
                <Nav.Link href="/adminNotifications"><FontAwesomeIcon icon={faBell} /> Notifications</Nav.Link>
                <Nav.Link href="/adminFinancialPreview"><FontAwesomeIcon icon={faSackDollar} /> Financial preview</Nav.Link>
                <Nav.Link href="/adminRegister"><FontAwesomeIcon icon={faUserPlus} /> Register new admin</Nav.Link>
            </Nav>
            )
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