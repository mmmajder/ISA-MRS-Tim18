import React from 'react';
import { Form, Row, Col, Button } from 'react-bootstrap';
import LabeledInput from './LabeledInput';
import LabeledTextarea from './LabeledTextarea';
import '../../assets/styles/buttons.css';
import {useCallback, useState, useEffect} from 'react';
import { updateAsset } from '../../services/api/AssetApi';
import { useNavigate  } from "react-router-dom";

export default function ResortForm({resort, buttonText, id}){

    const navigate = useNavigate ();

    const [name, setName] = useState();
    const [address, setAddress] = useState();
    const [description, setDescription] = useState();
    const [rules, setRules] = useState();
    const [numOfPeople, setNumOfPeople] = useState();
    const [cancelationFee, setCancelationFee] = useState();
    
    // sets resort's values if it's updateResortForm
    useEffect(() => {
        if (id !== -1){
            setName(resort.name);
            setAddress(resort.address);
            setDescription(resort.description);
            setRules(resort.rules);
            setNumOfPeople(resort.numOfPeople);
            setCancelationFee(resort.cancelationConditions);
        }
    }, [resort])

    const postRequest = useCallback(
        (e) => {
            e.preventDefault();
            const resortJson = {name, address, description, rules, numOfPeople, cancelationFee}
            const request = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(resortJson)
            };
            fetch('http://localhost:8000/assets', request) 
                .then(response => response.json())
        }, [name, address, description, rules, numOfPeople, cancelationFee]
    )

    const putRequest = useCallback(() => {
            console.log("evo me tu "+ id + " " + resort.id);
            let updatedResort = {
                id : id,
                name : name,
                address : address,
                description : description,
                rules : rules,
                numOfPeople : numOfPeople,
                cancelationConditions : cancelationFee
            };
            console.log(updatedResort);
            const response = updateAsset(updatedResort.id, updatedResort);
            console.log(response.data);
            navigate('/resorts/' + id);
    }, [id, name, address, description, rules, numOfPeople, cancelationFee]);

    const onClickFunction = id === -1 ? postRequest : putRequest;

    return (<>
    <Row className='mt-5' >
        <Col sm={2} />
        <div className="borderedBlock">
            <Col sm={true} >
                <Form>
                    <LabeledInput value={name} label="Name" inputName="name" placeholder="Type name of your resort" required onChangeFunc={setName}/>
                    <LabeledInput value={address} label="Address" inputName="address" placeholder="Type address of your resort" required onChangeFunc={setAddress}/>
                    <LabeledTextarea value={description} label="Description" inputName="description" placeholder="Type description of your resort" required onChangeFunc={setDescription}/>
                    <LabeledTextarea value={rules} label="Rules" inputName="rules" placeholder="Type rules of your resort" required onChangeFunc={setRules}/>
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
                            <Button variant="custom" type="submit" className='formButton' onClick={onClickFunction}>
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