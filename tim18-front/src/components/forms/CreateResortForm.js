import React from 'react';
import { Form, Row, Col, Button } from 'react-bootstrap';
import { LabeledInput } from './LabeledInput';
import LabeledTextarea from './LabeledTextarea';
import '../../assets/styles/form.css';
import {useCallback, useState} from 'react';


export default function CreateResortForm(){
    const [name, setName] = useState('');
    const [address, setAddress] = useState('');
    const [description, setDescription] = useState('');
    const [rules, setRules] = useState('');
    const [numOfPeople, setNumOfPeople] = useState(1);
    const [cancelationFee, setCancelationFee] = useState(0);

    const postRequest = useCallback(
        (e) => {
            e.preventDefault();
            const resortJson = {name, address, description, rules, numOfPeople, cancelationFee}
            const request = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(resortJson)
            };
            fetch('http://localhost:8082/resorts', request) 
                .then(response => response.json())
        }, [name, address, description, rules, numOfPeople, cancelationFee]
    )

    return (<>
    <Row className='mt-5' >
        <Col sm={2} />
        <div className="borderedBlock">
            <Col sm={true} >
                <Form>
                    <LabeledInput label="Name" inputName="name" placeholder="Type name of your resort" required onChangeFunc={setName}/>
                    <LabeledInput label="Address" inputName="address" placeholder="Type address of your resort" required onChangeFunc={setAddress}/>
                    <LabeledTextarea label="Description" inputName="description" placeholder="Type description of your resort" required onChangeFunc={setDescription}/>
                    <LabeledTextarea label="Rules" inputName="rules" placeholder="Type rules of your resort" required onChangeFunc={setRules}/>
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
                                Create resort
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