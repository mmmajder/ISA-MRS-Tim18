import React from 'react'
import {useEffect, useState} from 'react';
import { Form, Button, Modal } from 'react-bootstrap';
import { declineRegistrationRequest } from '../../services/api/RegistrationRequestApi';

const DeclineRegistrationModal = ({onDelete, request}) => {
    const [reason, setReason] = useState("");
    const handleClose = () => setShow(false);
    const [show, setShow] = useState(true);
    const [isDeleted, setDeleted] = useState(false)

    const declineRequet = () => {
        declineRegistrationRequest(request.id, reason)
        setDeleted(true)
        handleClose()
        onDelete(request)
    }

    return (<>
    <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Reason for not accepting registration request</Modal.Title>
        </Modal.Header>
        <Modal.Body>
        <Form>
            <Form.Group className="mb-2">
                <Form.Control className='mb-1' type='text' name='dob' placeholder='Reason' value={reason} onChange={(e) => setReason(e.target.value)}></Form.Control>
            </Form.Group>
        </Form>
    </Modal.Body>
    <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={declineRequet}>
            Decline request
          </Button>
        </Modal.Footer>
    </Modal>
    
    </>
  )
}

export default DeclineRegistrationModal