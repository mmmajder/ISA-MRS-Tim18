import React from 'react';
import { Form, Row, Col, Button } from 'react-bootstrap';
import LabeledInput from './LabeledInput';
import LabeledTextarea from './LabeledTextarea';
import '../../assets/styles/buttons.css';
import {useCallback, useState} from 'react';


export default function ResortForm({resort, buttonText, id}){

    const [name, setName] = useState(resort.name);
    const [address, setAddress] = useState(resort.address);
    const [description, setDescription] = useState(resort.description);
    const [rules, setRules] = useState(resort.rules);
    const [numOfPeople, setNumOfPeople] = useState(resort.numOfPeople);
    const [cancelationFee, setCancelationFee] = useState(resort.cancelationFee);

    const postRequest = useCallback(
        (e) => {
            e.preventDefault();
            const resortJson = {name, address, description, rules, numOfPeople, cancelationFee, id}
            const request = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(resortJson)
            };
            fetch('http://localhost:8000/resorts', request) 
                .then(response => response.json())
        }, [name, address, description, rules, numOfPeople, cancelationFee, id]
    )

    return (<>
    <Row className='mt-5' >
        <Col sm={2} />
        <div className="borderedBlock">
            <Col sm={true} >
                <Form>
                    <LabeledInput value={resort.name} label="Name" inputName="name" placeholder="Type name of your resort" required onChangeFunc={setName}/>
                    <LabeledInput value={resort.address} label="Address" inputName="address" placeholder="Type address of your resort" required onChangeFunc={setAddress}/>
                    <LabeledTextarea value={resort.description} label="Description" inputName="description" placeholder="Type description of your resort" required onChangeFunc={setDescription}/>
                    <LabeledTextarea value={resort.rules} label="Rules" inputName="rules" placeholder="Type rules of your resort" required onChangeFunc={setRules}/>
                    <Row className='mt-2'>
                        <Col sm={3} align='right'><Form.Label>Number of people</Form.Label></Col>
                        <Col sm={2}>
                            <Form.Control name="numOfPeople"  type="number" min="1" required
                                value={numOfPeople} 
                                onChange={(e) => setNumOfPeople(e.target.value)}>
                            </Form.Control>
                        </Col>
                        <Col sm={3} align='right'><Form.Label>Cancelation fee in % </Form.Label></Col>
                        <Col sm={2}>
                            <Form.Control name="cancelationFee"  type="number" min="0" max="100" required
                                value={cancelationFee} 
                                onChange={(e) => setCancelationFee(e.target.value)}>
                            </Form.Control>
                        </Col>
                        <Col sm={2}/>
                    </Row>
                    <Row className='mt-2'>
                        <Col sm={4}/>
                        <Col sm={4} align='center'>
                            <Button variant="custom" type="submit" className='formButton' onClick={postRequest}>
                                {buttonText}
                            </Button>
                        </Col>
                        <Col sm={4}/>
                    </Row>
                </Form>
            </Col>
        </div>
        <Col sm={2} />
    </Row>
    </>
    );
}