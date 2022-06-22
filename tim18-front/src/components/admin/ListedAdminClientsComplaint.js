import React, { useState } from 'react';
import { Row, Col } from 'react-bootstrap';
import { cancelClientsComplaint } from '../../services/api/ReviewApi';
import ClientsComplaintMainInfo from './ClientsComplaintMainInfo';
import CommentClientsReviewModal from './CommentClientsReviewModal';
import RegistrationRequestButton from './RegistrationRequestButton';

const ListedAdminClientsComplaint = ({request, key, onDelete}) => {
    const [activeForm, setActiveForm] = useState(null);

    const commentRequest = () => {
        setActiveForm(<CommentClientsReviewModal  request={request} onDelete={handleCallback}/>)
    }

    const handleCallback = (childData) =>{
        onDelete(childData)
    }

    const complaintCallback = (returnData) => {
        if(!returnData){
            alert('Oops, something went wrong')
        }
        else
        {
            alert('Successfull')
            onDelete(request)
        }
        }

    const cancelRequest = () => {
        cancelClientsComplaint(request.id, complaintCallback)
    }
    /*
    */

    return <div className="borderedBlock mt-3 pt-0 ms-4 me-4" align="">
    <Row className='ms-4'>
        <Col sm="4" className='mt-4'>
            <ClientsComplaintMainInfo request={request} />
        </Col>
        <Col sm="4" className="mt-4">
            {request.text}
        </Col>
        <Col>
            <Row>
            <Col sm="6" className="mt-3">
                <RegistrationRequestButton request={request} text='Comment' onClickFunction={commentRequest}/>
            </Col>
            <Col sm="6" className="mt-3">
                <RegistrationRequestButton request={request} text='Decline' onClickFunction={cancelRequest}/>
            </Col>
            </Row>
        </Col>
        
        
    </Row>
    {activeForm}
    </div>
}

export default ListedAdminClientsComplaint