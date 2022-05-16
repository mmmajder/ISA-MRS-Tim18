import React from 'react'
import {Nav, Navbar, NavDropdown} from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faHome, faHouseChimney, faPlus, faRightToBracket, faBook, faChartLine, faFishFins, faShip, faAnchor, faCircleQuestion, faSackDollar, faBell, faUserPlus } from '@fortawesome/free-solid-svg-icons'

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
        case "Admin":
            retData = (<Nav>
                <Nav.Link href="/adminRegistrationReq"><FontAwesomeIcon icon={faCircleQuestion} /> Registration requests</Nav.Link>
                <Nav.Link href="/adminNotifications"><FontAwesomeIcon icon={faBell} /> Notifications</Nav.Link>
                <Nav.Link href="/adminFinancialPreview"><FontAwesomeIcon icon={faSackDollar} /> Financial preview</Nav.Link>
                <Nav.Link href="/adminRegister"><FontAwesomeIcon icon={faUserPlus} /> Register new admin</Nav.Link>
            </Nav>
            )

            /*retData=(<NavDropdown title={<span><FontAwesomeIcon icon={faAnchor} />Actions </span>}>
                <NavDropdown.Item href="/adminRegistrationReq"><FontAwesomeIcon icon={faRightToBracket} />Registration requests</NavDropdown.Item>
                <NavDropdown.Item href="/adminNotifications"><FontAwesomeIcon icon={faPlus} />Notifications</NavDropdown.Item>
                <NavDropdown.Item href="/adminFinancialPreview"><FontAwesomeIcon icon={faShip} />Financial preview</NavDropdown.Item> 
                <NavDropdown.Item href="/adminRegister"><FontAwesomeIcon icon={faShip} />Register new admin</NavDropdown.Item> 
            </NavDropdown>
            )*/
            break;
        default:
            break;
    }
  return retData;
}

export default UsersOffersDropdown