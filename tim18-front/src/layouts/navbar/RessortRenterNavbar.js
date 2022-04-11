import { faHome, faChevronCircleRight, faCogs, faUser, faHouseChimney, faPlus, faBook, faChartLine, faSearch} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import '../../assets/styles/nav.css';
import '../../assets/styles/style.css';
import {Nav, Navbar, NavDropdown} from 'react-bootstrap';
import ProfileDropdown from './ProfileDropdown.js';

export default function ResortRenterNavbar(){
    const logo = require('../../assets/images/island_logo.png')

    return <Navbar bg="darkBlue" variant="dark" sticky='top' expand="md" collapseOnSelect> 
        <Navbar.Brand ><img src={logo}  className="brand" alt="logo" /> Hakuna Matata</Navbar.Brand>
        <Navbar.Toggle />
        <Navbar.Collapse className="ps-2">
            <Nav className="ms-auto">
                <Nav.Link href="#"><FontAwesomeIcon icon={faHome} /> Home</Nav.Link>
                <Nav.Link href="#"><FontAwesomeIcon icon={faSearch} /> Search</Nav.Link>
                <NavDropdown title={<span><FontAwesomeIcon icon={faHouseChimney} /> Resorts</span>}> 
                    <NavDropdown.Item href="/resorts"><FontAwesomeIcon icon={faHouseChimney} /> My resorts</NavDropdown.Item> 
                    <NavDropdown.Item href="/createResort"><FontAwesomeIcon icon={faPlus} /> Add new resort</NavDropdown.Item>
                    <NavDropdown.Item href="#"><FontAwesomeIcon icon={faBook} /> Rents history</NavDropdown.Item>
                    <NavDropdown.Item href="#"><FontAwesomeIcon icon={faChartLine} /> Reports</NavDropdown.Item>
                </NavDropdown>
                <ProfileDropdown />
            </Nav>
        </Navbar.Collapse>
    </Navbar>
    
    
    
    // <nav className="navbar">
    //     <a className="navbar-brand navbar-expand-lg navbar-dark bg-dark" href="">
    //         <img src={logo}  className="brand" alt="logo" />
    //     </a>
    //     <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    //         <span className="navbar-toggler-icon"></span>
    //     </button>
    //     <div className="collapse navbar-collapse navbar-nav" id="navbar-nav">  
    //         <ul className="navbar-nav ml-auto">
    //             <li className="nav-item">
    //                 <a id="homeButton" className="nav-link navLink" href="" target="_top"><FontAwesomeIcon icon={faHome} /> Home</a>
    //             </li>
    //             <li className="nav-item">
    //                 <a id="inboxButton" className="nav-link navLink" href="" target="_top"><FontAwesomeIcon icon={faComment} /> Inbox</a>
    //             </li>
    //             <li className="nav-item">
    //                 <a id="friendsButton" className="nav-link navLink" href="" target="_top"><FontAwesomeIcon icon={faUserFriends} /> O'nect Requests</a>
    //             </li>
    //             <li className="nav-item">
    //                 <a id="notificationButton" className="nav-link navLink" href="" target="_top"><FontAwesomeIcon icon={faCogs} /> Settings</a>
    //             </li>
    //             <li className="nav-item">
    //                 <a id="profileButton" className="nav-link navLink" href="" target="_top"><img src={logo} alt="profile_picture" className="profile_picture" id="navProfilePic"/> Profile</a>
    //             </li>
    //             <li className="nav-item">
    //                 <a id="logoutButton" className="nav-link navLink" href="" target="_top"><FontAwesomeIcon icon={faChevronCircleRight} /> Logout</a>
    //             </li>
    //         </ul>
    //     </div>
    // </nav>
}