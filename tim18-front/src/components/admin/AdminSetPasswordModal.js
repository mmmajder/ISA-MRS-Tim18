import React, { useState } from 'react'
import { Button, Form, Modal } from 'react-bootstrap'
import { saveNewPassword } from '../../services/api/AdminApi';

const AdminSetPasswordModal = ({user}) => {
    const [password, setPassword] = useState("");
    const [repeatedPassword, setRepeatedPassword] = useState("");
    const [repeatedError, setRepeatedError] = useState("");
    const [validated, setValidated] = useState(false);
    const handleClose = () => setShow(false);
    const [show, setShow] = useState(true);
    

    console.log("Stigao")

    const validateRepeated = () => {
        let isValid = true;
        if (repeatedPassword != password) {
            isValid = false;
            setRepeatedError("Please enter your confirm password.");
          }
        else {
            setRepeatedError("");
        }
        return isValid;
    }
    
    const handleSubmit = (event) => {
        const form = event.currentTarget;
        if ((validateRepeated() == true) && form.checkValidity() === true) {
            savePassword(password);
          
        }
        event.preventDefault();
        event.stopPropagation();
        setValidated(true);
      };

    const savePassword = () => {
        saveNewPassword(user, password)
        setShow(false)
    }

  return (
    <Modal show={show} onHide={handleClose}>
    <Modal.Header closeButton>
          <Modal.Title>Set new password</Modal.Title>
        </Modal.Header>
    <Form className='mb-2 --bs-body-font-family ms-4 me-4' noValidate validated={validated} onSubmit={handleSubmit}>
    <Form.Group className="mb-3 mt-2" controlId="formBasicPassword">
        <Form.Label>Password</Form.Label>
        <Form.Control type="password" placeholder="Password" required pattern="^(?=.*?[A-Za-z])(?=.*?[0-9]).{6,}$" value={password} onChange={(e) => setPassword(e.target.value)}/>
        <Form.Control.Feedback type="invalid">Password must have at least 6 characters including letters and numbers.</Form.Control.Feedback>
        </Form.Group>
    <Form.Group className="mb-3" controlId="formBasicPasswordRepeat">
        <Form.Label>Repeat password</Form.Label>
        <Form.Control type="password" placeholder="Password" required pattern="^(?=.*?[A-Za-z])(?=.*?[0-9]).{6,}$"  value={repeatedPassword} onChange={(e) => setRepeatedPassword(e.target.value)}/>
        <div className="invalid-feedback">{repeatedError}</div>
    </Form.Group>
    
    <Modal.Footer>
        <Button variant="custom" type="submit" className='formButtonAdmin'>
                Submit
        </Button>
    </Modal.Footer>
    </Form>
    </Modal>
  )
  /*<Button variant="secondary" onClick={handleClose}>
        Close
        </Button>
        <Button variant="primary" onClick={savePassword}>
        Add level
        </Button>*/
}

export default AdminSetPasswordModal