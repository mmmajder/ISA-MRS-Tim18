import React from 'react'
import '../../assets/styles/asset.css';
import { Row, Col } from 'react-bootstrap';
import FixedWidthRegButton from '../buttons/FixedWidthRegButton';
import { getRole }  from '../../services/AuthService/AuthService'
import RegistrationRequestMainInfo from './RegistrationRequestMainInfo';
import RegistrationRequestMoreInfo from './RegistrationRequestMoreInfo';
import RegistationRequestButton from './RegistrationRequestButton';
import './../../assets/styles/admin.css'
import { acceptDeletionRequest } from '../../services/api/DeleteRequestApi';
import { declineDeletionRequest } from '../../services/api/DeleteRequestApi';

const ListedDeleteProfileRequest = ({request, key}) => {
    const accpetRequest = () => {
        acceptDeletionRequest(request.id)
    }

    const declineRequest = () => {
        declineDeletionRequest(request.id)
    }
    console.log(request)
    console.log("request")
    
    return <div className="borderedBlock mt-3 pt-0 ms-4 me-4" align="">
    <Row className='ms-4'>
        <Col sm="4" className='mt-4'>
            <RegistrationRequestMainInfo user={request.user} />
        </Col>
        <Col sm="4" className="mt-4">
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

export default ListedDeleteProfileRequest