import { faHome, faSearch, faSuitcase, faBorderAll, faSailboat, faHouseChimney, faFishFins, faBell, faCircleQuestion} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import '../../assets/styles/nav.css';
import '../../assets/styles/style.css';
import {Nav, Navbar, NavDropdown} from 'react-bootstrap';
import ProfileDropdown from './ProfileDropdown.js';

export default function ResortRenterNavbar(){
    const logo = require('../../assets/images/island_logo.png')
    // myReservations (all, boats, resorts, adventures)-- history, current
    // mySubscriptions -- na profilu(samo ja vidim)? bell
    // reviews i gave (na istoriji rezervacija prikazem?), 
    // reviews i was given -- isto na profilu?

    return <Navbar bg="darkBlue" variant="dark" sticky='top' expand="md" collapseOnSelect> 
        <Navbar.Brand ><img src={logo}  className="brand" alt="logo" /> Hakuna Matata</Navbar.Brand>
        <Navbar.Toggle />
        <Navbar.Collapse className="ps-2">
            <Nav className="ms-auto">
                <Nav.Link href="#"><FontAwesomeIcon icon={faHome} /> Home</Nav.Link>
                <Nav.Link href="#"><FontAwesomeIcon icon={faSearch} /> Search</Nav.Link>

                
                <NavDropdown title={<span><FontAwesomeIcon icon={faSuitcase} /> Reservations</span>}> 
                    <NavDropdown.Item href="#"><FontAwesomeIcon icon={faBorderAll} /> All </NavDropdown.Item> 
                    <NavDropdown.Item href="#"><FontAwesomeIcon icon={faHouseChimney} /> Resorts </NavDropdown.Item>
                    <NavDropdown.Item href="#"><FontAwesomeIcon icon={faSailboat} /> Boats</NavDropdown.Item>
                    <NavDropdown.Item href="#"><FontAwesomeIcon icon={faFishFins} /> Fishing adventures </NavDropdown.Item>
                </NavDropdown>

                <Nav.Link href="#"><FontAwesomeIcon icon={faBell} /> Subscriptions </Nav.Link>

                <ProfileDropdown />
                
                <Nav.Link href="#"><FontAwesomeIcon icon={faCircleQuestion} /> Help </Nav.Link>
            </Nav>
        </Navbar.Collapse>
    </Navbar>

}