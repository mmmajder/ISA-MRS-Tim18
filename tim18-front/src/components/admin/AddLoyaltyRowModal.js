import React from 'react'
import {useEffect, useState} from 'react';
import { Form, Button, Modal, Row, Col } from 'react-bootstrap';


const AddLoyaltyRowModal = ({loyaltyType, addLevel, currentList, show, onHide}) => {
    const handleClose = () => { onHide() }
    const [level, setLevel] = useState(null)
    const [points, setPoints] = useState(0)
    const [discount, setDiscount] = useState(0)
    

    const sendRequet = () => {
        handleClose()
        if (!levelExist() && points>=0 && discount>=0 && discount<=100) {
            addLevel({level, points, discount})
        } else {
            console.log("error message")
        }
    }

    const levelExist = () => {
        for (let i=0; i<currentList.length; i++) {
            if (currentList[i].level==level) {
                return true;
            }
        }
        return false;
    }


    return (<>
    <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Add {loyaltyType} loyalty level</Modal.Title>
        </Modal.Header>
        <Modal.Body>
        <Form>
            <Form.Group className="mb-2">
                <Row>
                    <Col sm={2} className="mt-1">
                        <Form.Label >Level: </Form.Label>
                    </Col>
                    <Col sm={10}>
                        <Form.Control className='mb-1' type='text' name='dob' placeholder='level' value={level} onChange={(e) => setLevel(e.target.value)}></Form.Control>
                    </Col>
                </Row>
            </Form.Group>
            <Form.Group className="mb-2">
                <Row>
                    <Col sm={2} className="mt-1">
                        <Form.Label >Points: </Form.Label>
                    </Col>
                    <Col sm={10}>
                        <Form.Control className='mb-1' type='double' name='dob'  value={points} onChange={(e) => setPoints(e.target.value)}></Form.Control>
                    </Col>
                </Row>
            </Form.Group>
            <Form.Group className="mb-2">
                <Row>
                    <Col sm={2} className="mt-1">
                        <Form.Label >Discount: </Form.Label>
                    </Col>
                    <Col sm={10}>
                        <Form.Control className='mb-1' type='number' name='dob' value={discount} onChange={(e) => setDiscount(e.target.value)}></Form.Control>
                    </Col>
                </Row>
            </Form.Group>
        </Form>
    </Modal.Body>
    <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={sendRequet}>
            Add level
          </Button>
        </Modal.Footer>
    </Modal>
    
    </>)
}

export default AddLoyaltyRowModal