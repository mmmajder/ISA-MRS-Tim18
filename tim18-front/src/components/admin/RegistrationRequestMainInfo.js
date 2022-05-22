import React from 'react'
import { Row, Col } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faUser, faHouseChimney, faFishFins, faShip } from '@fortawesome/free-solid-svg-icons'
import './../../assets/styles/admin.css'

const RegistrationRequestMainInfo = ({user}) => {
    let userType;
    switch (user.userType) {
        case "BoatRenter":
            userType = <FontAwesomeIcon icon={faShip} className="faButtonsRegistration mt-2"/>
            break;
        case "FishingInstructor":
            userType = <FontAwesomeIcon icon={faFishFins} className="faButtonsRegistration mt-2"/>
            break;
        case "Client":
            userType = <FontAwesomeIcon icon={faUser} className="faButtonsRegistration mt-2"/>
            break;
        case "ResortRenter":
            userType = <FontAwesomeIcon icon={faHouseChimney} className="faButtonsRegistration mt-2"/>
            break;    
        default:
            break;
    }



    return <>
    <Row>
        <Col sm='2'>
            <div className='centerElem'>
                {userType}
            </div>
            
        </Col>
        <Col>
            <Row className="importantInfo">
                {user.firstName + " " + user.lastName}
            </Row>
            <Row>
                {user.email}
            </Row>
        </Col>
    </Row>
    </>
}

export default RegistrationRequestMainInfo