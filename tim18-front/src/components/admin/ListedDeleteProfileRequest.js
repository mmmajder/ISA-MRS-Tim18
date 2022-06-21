import React, { useState } from 'react';
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
import ReasonProfileDeletionModal from './ReasonProfileDeletionModal'

const ListedDeleteProfileRequest = ({request, key, onDelete}) => {
    const [activeForm, setActiveForm] = useState(null);

    const accpetRequest = () => {
        setActiveForm(<ReasonProfileDeletionModal request={request} onDelete={handleCallback} action="accept"/>)
    }

    const declineRequest = () => {
        setActiveForm(<ReasonProfileDeletionModal request={request} onDelete={handleCallback} action="decline"/>)
    }

    const handleCallback = (childData) =>{
        onDelete(childData)
    }


    console.log(request)
    console.log("request")
    
    return <div className="borderedBlock mt-3 pt-0 ms-4 me-4" align="">
    <Row className='ms-4'>
        <Col sm="4" className='mt-4'>
            <RegistrationRequestMainInfo user={request.user} />
        </Col>
        <Col sm="4" className="mt-4">
            <div className='centerElem mt-3'>
                {"Reason: " + request.reason } 
            </div>
        </Col>
        <Col sm="2" className="mt-3">
            <RegistationRequestButton request={request} text='Accept' onClickFunction={accpetRequest}/>
        </Col>
        <Col sm="2" className="mt-3">
            <RegistationRequestButton request={request} text='Decline' onClickFunction={declineRequest}/>
        </Col>
    </Row>
    {activeForm}
    </div>
}

export default ListedDeleteProfileRequest