import React from 'react'
import { Form, Button, Modal } from 'react-bootstrap';
import {useState} from 'react';
import { sendCommentOnComplaint } from '../../services/api/ReviewApi';

const CommentClientsReviewModal = ({request, onDelete}) => {
    let title;
    let btn;

    const [emailClient, setEmailClient] = useState("");
    const [emailRenter, setEmailRenter] = useState("");
    const handleClose = () => setShow(false);
    const [show, setShow] = useState(true);

    const acceptRequest = () => {
        sendCommentOnComplaint(request.id, emailClient, emailRenter)
        onDelete(request)
    }

    return (<>
        <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
              <Modal.Title>Write mails to client and renter</Modal.Title>
            </Modal.Header>
            <Modal.Body>
            <Form>
                <Form.Group className="mb-2">
                    <Form.Label>Email to client</Form.Label>
                    <textarea class="form-control" value={emailClient} onChange={(e) => setEmailClient(e.target.value)}></textarea>
                </Form.Group>
                <Form.Group className="mb-2">
                    <Form.Label>Email to renter</Form.Label>
                    <textarea class="form-control" value={emailRenter} onChange={(e) => setEmailRenter(e.target.value)}></textarea>
                </Form.Group>
            </Form>
        </Modal.Body>
        <Modal.Footer>
              <Button variant="secondary" onClick={handleClose}>
                Close
              </Button>
              <Button variant="primary" onClick={acceptRequest}>
                Send
              </Button>
            </Modal.Footer>
        </Modal>
        
        </>
      )
}

export default CommentClientsReviewModal