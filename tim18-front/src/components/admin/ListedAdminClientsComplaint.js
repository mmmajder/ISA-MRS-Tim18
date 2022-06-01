import React, { useState } from 'react';
import { Row, Col } from 'react-bootstrap';

const ListedAdminClientsComplaint = ({request, key, onDelete}) => {
   /* const [activeForm, setActiveForm] = useState(null);

    const accpetRequest = () => {
        onDelete(request)
    }

    const handleCallback = (childData) =>{
        onDelete(childData)
    }

    const declineRequest = () => {
        //setActiveForm(<DeclineRegistrationModal request={request} onDelete={handleCallback}/>)
    }

    return <div className="borderedBlock mt-3 pt-0 ms-4 me-4" align="">
    <Row className='ms-4'>
        <Col sm="4" className='mt-4'>
            <ClientsComplaintMainInfo request={request} />
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
    </div>*/
}

export default ListedAdminClientsComplaint