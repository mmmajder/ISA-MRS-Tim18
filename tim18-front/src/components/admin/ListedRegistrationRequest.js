import React, { useState } from 'react';
import '../../assets/styles/asset.css';
import { Row, Col } from 'react-bootstrap';
import RegistrationRequestMainInfo from './RegistrationRequestMainInfo';
import RegistrationRequestMoreInfo from './RegistrationRequestMoreInfo';
import RegistationRequestButton from './RegistrationRequestButton';
import './../../assets/styles/admin.css'
import { acceptRegistrationRequest, declineRegistrationRequest } from '../../services/api/RegistrationRequestApi';
import DeclineRegistrationModal from './DeclineRegistrationModal';

const ListedRegistrationRequest = ({request, key, onDelete}) => {
    console.log(request)
    const [activeForm, setActiveForm] = useState(null);


    const showMessage = (returnData) => {
        if(returnData!=false){
            alert('Oops, something went wrong')
        }
        else
        {
            alert('Successfull')
            onDelete(request)
        }
        }

    const accpetRequest = () => {
        acceptRegistrationRequest(request.id, showMessage)
    }

    const handleCallback = (childData) =>{
        onDelete(childData)
    }

    const declineRequest = () => {
        setActiveForm(<DeclineRegistrationModal request={request} onDelete={handleCallback} showMessage={showMessage}/>)
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
    {activeForm}
    </div>
}

export default ListedRegistrationRequest