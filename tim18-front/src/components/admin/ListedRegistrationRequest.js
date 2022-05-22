import React from 'react'
import '../../assets/styles/asset.css';
import { Row, Col } from 'react-bootstrap';
import FixedWidthRegButton from '../buttons/FixedWidthRegButton';
import { getRole }  from '../../services/AuthService/AuthService'
import RegistrationRequestMainInfo from './RegistrationRequestMainInfo';
import RegistrationRequestMoreInfo from './RegistrationRequestMoreInfo';
import RegistationRequestButton from './RegistrationRequestButton';
import './../../assets/styles/admin.css'
import { acceptRegistrationRequest, declineRegistrationRequest } from '../../services/api/RegistrationRequestApi';

const ListedRegistrationRequest = ({request, key}) => {
    const accpetRequest = () => {
        acceptRegistrationRequest(request.id)
    }

    const declineRequest = () => {
        declineRegistrationRequest(request.id)
    }


    return <div className="borderedBlock mt-3 pt-0 ms-4 me-4" align="">
    <Row className='ms-4'>
        <Col sm="4" className='mt-4'>
            <RegistrationRequestMainInfo user={request.user} />
        </Col>
        <Col sm="4" className="mt-4">
            <RegistrationRequestMoreInfo user = {request.user} registrationDateTime={request.registrationDateTime}  />
        </Col>
        <Col sm="2" className="mt-3">
            <RegistationRequestButton request={request} text='Accept' onClickFunction={accpetRequest}/>
        </Col>
        <Col sm="2" className="mt-3">
            <RegistationRequestButton request={request} text='Decline' onClickFunction={declineRequest}/>
        </Col>
    </Row>
    </div>
}

export default ListedRegistrationRequest