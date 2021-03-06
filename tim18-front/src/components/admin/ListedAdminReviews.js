import React from 'react'
import { Col, Row } from 'react-bootstrap';
import { acceptdeclineReview } from '../../services/api/ReviewApi';
import AdminReviewMainData from './AdminReviewMainData';
import RegistrationRequestButton from './RegistrationRequestButton';



const ListedAdminReviews = ({request, key, onDelete}) => {

    const deleteCallback = (returnData) => {
        if(!returnData){
            alert('Oops, something went wrong')
        }
        else
        {
            alert('Successfully accepted/denied review')
            onDelete(request)
        }
        }

  const acceptRequest = () => {
        acceptdeclineReview(request, true, deleteCallback)
  }

  const handleCallback = (childData) =>{
      onDelete(childData)
  }

  const cancelRequest = () => {
      acceptdeclineReview(request, false, deleteCallback)
  }


  return <div className="borderedBlock mt-3 pt-0 ms-4 me-4" align="">
  <Row className='ms-4'>
      <Col sm="4" className='mt-4'>
          <AdminReviewMainData request={request}/>
      </Col>
      <Col sm="4" className="mt-4">
          {request.text}
      </Col>
      <Col>
          <Row>
          <Col sm="6" className="mt-3">
              <RegistrationRequestButton request={request} text='Accept' onClickFunction={acceptRequest}/>
          </Col>
          <Col sm="6" className="mt-3">
              <RegistrationRequestButton request={request} text='Decline' onClickFunction={cancelRequest}/>
          </Col>
          </Row>
      </Col>
  </Row>
  </div>
}

export default ListedAdminReviews