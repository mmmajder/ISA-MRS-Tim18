import React from 'react'
import { Form, Button, Modal } from 'react-bootstrap';
import {useState} from 'react';
import { acceptDeletionRequest } from '../../services/api/DeleteRequestApi';
import { declineDeletionRequest } from '../../services/api/DeleteRequestApi';

const ReasonProfileDeletionModal = ({request, onDelete, action}) => {
    let title;
    let btn;

    const [reason, setReason] = useState("");
    const handleClose = () => setShow(false);
    const [show, setShow] = useState(true);
    

    const deletionCallback = (returnData) => {
        if(!returnData){
            alert('Oops, something went wrong')
        }
        else
        {
            alert('Successfull')
            onDelete(request)
        }
        }

    const acceptRequest = () => {
        acceptDeletionRequest(request.id, reason, deletionCallback)
    }

    const declineRequest = () => {
        declineDeletionRequest(request.id, reason, deletionCallback)
    }

    if (action=="accept") {
        title = "Reason for accepting profile delete request"
        btn = (<Button variant="primary" onClick={acceptRequest}>
        Accept
        </Button>)
    }
    else {
        title = "Reason for not accepting profile delete request"
        btn = (<Button variant="primary" onClick={declineRequest}>
        Accept
        </Button>)
    }

    return (<>
        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
              <Modal.Title>{title}</Modal.Title>
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
              {btn}
            </Modal.Footer>
        </Modal>
        
        </>
      )
}

export default ReasonProfileDeletionModal