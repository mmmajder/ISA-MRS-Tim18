import React from 'react'
import { Row, Col } from 'react-bootstrap';
import { makeDateOfList } from '../../services/utils/TimeUtils';

const RegistrationRequestMoreInfo = ({user, registrationDateTime}) => {
    
    return <>
    <Row>
        {"Location: " + user.address + ", " + user.city + ", " + user.state}
    </Row>
    <Row>
        {"Phone number: " + user.phoneNum}
    </Row>
    <Row>
            {"Registation time: " + makeDateOfList(registrationDateTime)}
    </Row>

    </>
}

export default RegistrationRequestMoreInfo