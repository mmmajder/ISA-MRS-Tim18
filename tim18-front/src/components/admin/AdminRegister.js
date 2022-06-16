import React, { useState } from 'react'
import { Form, Button, Modal, Row, Col, Container } from 'react-bootstrap';
import { registerAdminRequest } from '../../services/api/RegistrationRequestApi';

const AdminRegister = () => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [repeatedPassword, setRepeatedPassword] = useState("");
    const [name, setName] = useState("");
    const [surname, setSurname] = useState("");
    const [validated, setValidated] = useState(false);
    const [repeatedError, setRepeatedError] = useState("");

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
            registerNewUser();
          
        }
        event.preventDefault();
        event.stopPropagation();
        setValidated(true);
      };

    const registerNewUser = () => {
        const requestBody = {email, password, name, surname}
        console.log(requestBody)
        registerAdminRequest(requestBody)
    }
  return (
    <div className='borderedBlockRegisterAdmin mb-3 mt-3' >
    <Container >  
    <Form className='mb-2 --bs-body-font-family ms-4 me-4' noValidate validated={validated} onSubmit={handleSubmit}>
        <h1 className="form-label">Register new admin</h1>
        <Row className="mt-3">
            <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Email address</Form.Label>
                <Form.Control type="email" placeholder="Enter email" required pattern=".+@gmail\.com" value={email} onChange={(e) => setEmail(e.target.value)}/>
                <Form.Control.Feedback type="invalid">Please provide a valid gmail.</Form.Control.Feedback>
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" placeholder="Password" required pattern="^(?=.*?[A-Za-z])(?=.*?[0-9]).{6,}$" value={password} onChange={(e) => setPassword(e.target.value)}/>
                <Form.Control.Feedback type="invalid">Password must have at least 6 characters including letters and numbers.</Form.Control.Feedback>
                </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicPasswordRepeat">
                <Form.Label>Repeat password</Form.Label>
                <Form.Control type="password" placeholder="Password" required pattern="^(?=.*?[A-Za-z])(?=.*?[0-9]).{6,}$"  value={repeatedPassword} onChange={(e) => setRepeatedPassword(e.target.value)}/>
                <div className="invalid-feedback">{repeatedError}</div>
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicName"  value={name} onChange={(e) => setName(e.target.value)}>
            <Form.Label>Name</Form.Label>
            <Form.Control type="text" placeholder="Enter name" required pattern="^([a-zA-Z]+\\s)*[a-zA-Z]+$"/>
            <Form.Control.Feedback type="invalid">Please provide a valid name.</Form.Control.Feedback>
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicSurname" value={surname} onChange={(e) => setSurname(e.target.value)}>
            <Form.Label>Surname</Form.Label>
            <Form.Control type="text" placeholder="Enter surname" required pattern="^([a-zA-Z]+\\s)*[a-zA-Z]+$"/>
            <Form.Control.Feedback type="invalid">Please provide a valid surname.</Form.Control.Feedback>
        </Form.Group>
        </Row>
        <div align='center'>
        <Button variant="custom" type="submit" className='formButtonAdmin'>
            Submit
        </Button>
        </div>
    </Form>
    </Container>
    </div>
  )
}

export default AdminRegister