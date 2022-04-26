import React from 'react';
import { Form, Row, Col, Button } from 'react-bootstrap';
import { LabeledInput } from './LabeledInput';
import LabeledTextarea from './LabeledTextarea';
import '../../assets/styles/form.css';
import {useCallback, useState} from 'react';
import { createAsset } from '../../services/api/AssetApi';


export default function CreateResortForm({userType}){
    const [name, setName] = useState('');
    const [address, setAddress] = useState('');
    const [description, setDescription] = useState('');
    const [rules, setRules] = useState('');
    const [numOfPeople, setNumOfPeople] = useState(1);
    const [cancelationConditions, setCancelationConditions] = useState(0);

    const postRequest = () => {
        const adventureJson = {name, address, description, rules, numOfPeople, cancelationConditions}
        createAsset(JSON.stringify(adventureJson))
    }

    return (<>
    <Row className='mt-5' >
        <Col sm={2} />
        <div className="borderedBlock">
            <Col sm={true} >
                <Form>
                    <LabeledInput label="Name" inputName="name" placeholder={userType=="instructor" ? "Type name of your adventure" : "Type name of your resort"} required onChangeFunc={setName}/>
                    <LabeledInput label="Address" inputName="address" placeholder={userType=="instructor" ? "Type address of your adventure" : "Type address of your resort"} required onChangeFunc={setAddress}/>
                    <LabeledTextarea label="Description" inputName="description" placeholder={userType=="instructor" ? "Type description of your adventure" : "Type description of your resort"} required onChangeFunc={setDescription}/>
                    <LabeledTextarea label="Rules" inputName="rules" placeholder={userType=="instructor" ? "Type rules of your adventure" : "Type rules of your resort"} required onChangeFunc={setRules}/>
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
                            <Form.Control name="cancelationConditions"  type="number" min="0" max="100" required
                                value={cancelationConditions} 
                                onChange={(e) => setCancelationConditions(e.target.value)}>
                            </Form.Control>
                        </Col>
                        <Col sm={2}/>
                    </Row>
                    <Row className='mt-2'>
                        <Col sm={4}/>
                        <Col sm={4} align='center'>
                            <Button variant="custom" type="submit" className='formButton' onSubmit={postRequest}>
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