import React from 'react'
import {useEffect, useState} from 'react';
import { Form, Button, Modal } from 'react-bootstrap';
import { reserveSepcialOfferRequest } from '../../services/api/ReservationApi';
import { getRole } from '../../services/AuthService/AuthService';
import { getLogged } from '../../services/api/LoginApi.js';
const ReserveSpecOfferModal = ({title, start, end, price}) => {
    const [reason, setReason] = useState("");
    const handleClose = () => setShow(false);
    const [show, setShow] = useState(true);
    const [user, setUser] = useState()

    useEffect(() => {
      async function fetchUser(){
          await getLogged(setUser);
      }
      fetchUser();
  }, [])

    //TODO ZAKUCANA CENA
    //TODO ZAKUCAN USER KADA BIRA RENTER
    //TODO ZAKUCAN ASSET
    let senderType = getRole()
    let clientId;
    if (senderType=="Client") {
      clientId = user.id
    } else {
      clientId = 2
    }

    let assetId = "1000005"

    const reserveOffer = () => {
        const request = {
          startDateTime: start, 
          endDateTime: end, 
          price: 40, 
          clientId: clientId,
          assetId: assetId
        }
        reserveSepcialOfferRequest(request)
    }
    
    return <>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>{title}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          
        </Modal.Body>
        <Modal.Footer>
              <Button variant="secondary" onClick={handleClose}>
                Close
              </Button>
              <Button variant="primary" onClick={reserveOffer}>
                Accept
              </Button>
            </Modal.Footer>
      </Modal>
    </>

    /*return (<>
        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
              <Modal.Title>{title}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
            <Form>
                <Form.Group className="mb-2">
                    <Form.Label className="mb-1">From: {start}</Form.Label>
                    <Form.Control className='mb-1' type='text' name='dob' placeholder='Reason' value={reason} onChange={(e) => setReason(e.target.value)}></Form.Control>
                </Form.Group>
                <Form.Group className="mb-2">
                    <Form.Label className="mb-1">To: </Form.Label>
                    <Form.Label className="mb-1">{end}</Form.Label>
                </Form.Group>
                <Form.Group className="mb-2">
                    <Form.Label className="mb-1">Price: </Form.Label>
                    <Form.Label className="mb-1">{price}</Form.Label>
                </Form.Group>
            </Form>
        </Modal.Body>
        
        </Modal>
        
        </>
      )*/
}

export default ReserveSpecOfferModal