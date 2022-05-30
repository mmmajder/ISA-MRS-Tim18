import React from 'react'
import {Nav, Navbar, NavDropdown} from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faHome, faHouseChimney, faPlus, faRightToBracket, faBook, faChartLine, faFishFins, faShip, faUserSlash, faAnchor, faCircleQuestion, faSackDollar, faBell, faUserPlus, faMoneyBillTrendUp, faMoneyBill1Wave, faStarHalfStroke, faFaceFrown } from '@fortawesome/free-solid-svg-icons'

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
        case "Admin":
            return <>
            <NavDropdown title={<span><FontAwesomeIcon icon={faUserPlus} /> User management </span>}>
                <NavDropdown.Item href="adminRegistrationReq"><FontAwesomeIcon icon={faCircleQuestion}/> Registration requests</NavDropdown.Item>
                <NavDropdown.Item href="adminProfileDeletionReq"><FontAwesomeIcon icon={faUserSlash}/> Profile delete requests</NavDropdown.Item>
                <NavDropdown.Item href="adminRegister"><FontAwesomeIcon icon={faUserPlus}/> Register new admin</NavDropdown.Item>
            </NavDropdown>
            <NavDropdown title={<span><FontAwesomeIcon icon={faSackDollar} /> Finance</span>}>
                <NavDropdown.Item href="adminLoyaltyProgram"><FontAwesomeIcon icon={faMoneyBill1Wave}/> Loyalty program</NavDropdown.Item>
                <NavDropdown.Item href="adminFinancialReports"><FontAwesomeIcon icon={faMoneyBillTrendUp}/> Financial reports</NavDropdown.Item>
            </NavDropdown>
            <NavDropdown title={<span><FontAwesomeIcon icon={faBell} /> Reviews/complaints</span>}>
                <NavDropdown.Item href="adminReviews"><FontAwesomeIcon icon={faStarHalfStroke}/> Reviews</NavDropdown.Item>
                <NavDropdown.Item href="adminClientsComplaints"><FontAwesomeIcon icon={faFaceFrown}/> Clients complaints</NavDropdown.Item>
                <NavDropdown.Item href="adminRentersComplaints"><FontAwesomeIcon icon={faFaceFrown}/> Renters complaints</NavDropdown.Item>
            </NavDropdown>
            </>
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