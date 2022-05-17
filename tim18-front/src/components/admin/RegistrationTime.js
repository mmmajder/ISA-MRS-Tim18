import React from 'react'
import { Row, Col } from 'react-bootstrap';
import { makeDateOfList } from '../../services/utils/TimeUtils';

const RegistrationTime = ({registrationDateTime}) => {
    
  return (
        <Row>
            {"Registation time: " + makeDateOfList(registrationDateTime)}
        </Row>
  )
}

export default RegistrationTime