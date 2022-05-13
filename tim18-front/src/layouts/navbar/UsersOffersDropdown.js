import React from 'react'
import {Nav, Navbar, NavDropdown} from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faHome, faHouseChimney, faPlus, faBook, faChartLine, faFishFins, faShip, faAnchor} from '@fortawesome/free-solid-svg-icons'

const UsersOffersDropdown = ({userType}) => {
    let retData = "";
    switch (userType) {
        case "FishingInstructor":
            retData=(<NavDropdown title={<span><FontAwesomeIcon icon={faFishFins} />Adventures </span>}> 
                <NavDropdown.Item href="/resorts"><FontAwesomeIcon icon={faFishFins} /> My adventures</NavDropdown.Item> 
                <NavDropdown.Item href="/createResort"><FontAwesomeIcon icon={faPlus} /> Add new adventures</NavDropdown.Item>
                <NavDropdown.Item href="#"><FontAwesomeIcon icon={faBook} /> Adventures history</NavDropdown.Item>
                <NavDropdown.Item href="#"><FontAwesomeIcon icon={faChartLine} /> Reports</NavDropdown.Item>
            </NavDropdown>)
            break;
        case "ResortRenter":
            retData=(<NavDropdown title={<span><FontAwesomeIcon icon={faHouseChimney} />Resorts </span>}> 
                <NavDropdown.Item href="/resorts"><FontAwesomeIcon icon={faHouseChimney} /> My resorts</NavDropdown.Item> 
                <NavDropdown.Item href="/createResort"><FontAwesomeIcon icon={faPlus} /> Add new resorts</NavDropdown.Item>
                <NavDropdown.Item href="#"><FontAwesomeIcon icon={faBook} /> Resorts history</NavDropdown.Item>
                <NavDropdown.Item href="#"><FontAwesomeIcon icon={faChartLine} /> Reports</NavDropdown.Item>
            </NavDropdown>)
            break;
        case "BoatRenter":
            retData=(<NavDropdown title={<span><FontAwesomeIcon icon={faAnchor} />Boats </span>}> 
                <NavDropdown.Item href="/resorts"><FontAwesomeIcon icon={faShip} /> My boats</NavDropdown.Item> 
                <NavDropdown.Item href="/createResort"><FontAwesomeIcon icon={faPlus} /> Add new boats</NavDropdown.Item>
                <NavDropdown.Item href="#"><FontAwesomeIcon icon={faBook} /> Boats history</NavDropdown.Item>
                <NavDropdown.Item href="#"><FontAwesomeIcon icon={faChartLine} /> Reports</NavDropdown.Item>
            </NavDropdown>)
            break;
        default:
            break;
    }
  return retData;
}

export default UsersOffersDropdown