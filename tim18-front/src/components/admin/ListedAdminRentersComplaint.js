import { addPointerEvent } from 'framer-motion';
import React, { useState } from 'react';
import { Row, Col } from 'react-bootstrap';
import { addPoint, declineAddPoint } from '../../services/api/ReviewApi';
import AdminRentersComplaint from './AdminRentersComplaint';
import CommentRentersReviewModal from './CommentRentersReviewModal';
import RegistrationRequestButton from './RegistrationRequestButton';

const ListedAdminRentersComplaint = ({request, key, onDelete}) => {
    const acceptRequest = () => {
        // add pop up
        addPoint(request, pointCallback);
    }

    const handleCallback = (childData) =>{
        onDelete(childData)
    }

    const pointCallback = (returnData) => {
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
        declineAddPoint(request, pointCallback)
    }


    return <div className="borderedBlock mt-3 pt-0 ms-4 me-4" align="">
    <Row className='ms-4'>
        <Col sm="4" className='mt-4'>
            <AdminRentersComplaint request={request}/>
        </Col>
        <Col sm="4" className="mt-4">
            {request.text}
        </Col>
        <Col>
            <Row>
            <Col sm="6" className="mt-3">
                <RegistrationRequestButton request={request} text='Accept punishment' onClickFunction={acceptRequest}/>
            </Col>
            <Col sm="6" className="mt-3">
                <RegistrationRequestButton request={request} text='Decline punishment' onClickFunction={cancelRequest}/>
            </Col>
            </Row>
        </Col>
    </Row>
    </div>
}

export default ListedAdminRentersComplaint