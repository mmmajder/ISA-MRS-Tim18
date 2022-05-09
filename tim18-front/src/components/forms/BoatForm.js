import React from 'react';
import { Form, Row, Col, Button } from 'react-bootstrap';
import LabeledInput from './LabeledInput';
import LabeledTextarea from './LabeledTextarea';
import '../../assets/styles/buttons.css';
import {useCallback, useState, useEffect} from 'react';
import { updateAsset } from '../../services/api/AssetApi';
import { useNavigate  } from "react-router-dom";

export default function BoatForm({boat, buttonText, id}){

    const navigate = useNavigate ();

    const [name, setName] = useState();
    const [address, setAddress] = useState();
    const [description, setDescription] = useState();
    const [rules, setRules] = useState();
    const [numOfPeople, setNumOfPeople] = useState();
    const [cancelationFee, setCancelationFee] = useState();
    const [boatType, setBoatType] = useState();
    const [length, setLength] = useState();
    const [numOfMotor, setNumOfMotor] = useState();
    const [motorPower, setMotorPower] = useState();
    const [maxSpeed, setMaxSpeed] = useState();
    const [navigationEquipment, setNavigationEquipment] = useState();
    const [fishingEquipment, setFishingEquipment] = useState();

    const assetType = "BOAT";
    
    // sets resort's values if it's updateResortForm
    useEffect(() => {
        if (id !== -1){
            setName(boat.name);
            setAddress(boat.address);
            setDescription(boat.description);
            setRules(boat.rules);
            setNumOfPeople(boat.numOfPeople);
            setCancelationFee(boat.cancelationConditions);

            setBoatType(boat.boatType);
            setLength(boat.length);
            setNumOfMotor(boat.numOfMotor);
            setMotorPower(boat.motorPower);
            setMaxSpeed(boat.maxSpeed);
            setNavigationEquipment(boat.navigationEquipment);
            setFishingEquipment(boat.fishingEquipment);
        }
    }, [boat])

    const postRequest = useCallback(
        (e) => {
            e.preventDefault();
            const boatJson = {name, address, description, rules, numOfPeople, cancelationFee, assetType,
                boatType, length, numOfMotor, motorPower, maxSpeed, navigationEquipment, fishingEquipment}
            const request = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(boatJson)
            };
            fetch('http://localhost:8000/assets', request) 
                .then(response => response.json())
        }, [name, address, description, rules, numOfPeople, cancelationFee, 
            boatType, length, numOfMotor, motorPower, maxSpeed, navigationEquipment, fishingEquipment]
    )

    const putRequest = useCallback(() => {
            let updatedboat = {
                id : id,
                name : name,
                address : address,
                description : description,
                rules : rules,
                numOfPeople : numOfPeople,
                cancelationConditions : cancelationFee,
                assetType : assetType,
                boatType:boatType,
                length:length,
                numOfMotor:numOfMotor,
                motorPower:motorPower,
                maxSpeed:maxSpeed,
                navigationEquipment:navigationEquipment,
                fishingEquipment:fishingEquipment
            };
            console.log(updatedboat);
            const response = updateAsset(updatedboat.id, updatedboat);
            console.log(response.data);
            navigate('/resorts/' + id);
    }, [id, name, address, description, rules, numOfPeople, cancelationFee, boatType, length, numOfMotor, motorPower, maxSpeed, navigationEquipment, fishingEquipment]);

    const onClickFunction = id === -1 ? postRequest : putRequest;

    return (<>
    <Row className='mt-5' >
        <Col sm={2} />
        <div className="borderedBlock">
            <Col sm={true} >
                <Form>
                    <LabeledInput value={name} label="Name" inputName="name" placeholder="Type name of your boat" required onChangeFunc={setName}/>
                    <LabeledInput value={address} label="Address" inputName="address" placeholder="Type address of your boat" required onChangeFunc={setAddress}/>
                    <LabeledTextarea value={description} label="Description" inputName="description" placeholder="Type description of your boat" required onChangeFunc={setDescription}/>
                    <LabeledTextarea value={rules} label="Rules" inputName="rules" placeholder="Type rules of your boat" required onChangeFunc={setRules}/>
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

                    <LabeledInput value={boatType} label="BoatType" inputName="boatType" placeholder="Type type of your boat" required onChangeFunc={setBoatType}/>

                    <Row className='mt-2'>
                        <Col sm={3} align='right'><Form.Label>Length</Form.Label></Col>
                        <Col sm={2}>
                            <Form.Control name="length"  type="number" min="1" required
                                value={length} 
                                onChange={(e) => setLength(e.target.value)}>
                            </Form.Control>
                        </Col>
                        <Col sm={3} align='right'><Form.Label>Max speed </Form.Label></Col>
                        <Col sm={2}>
                            <Form.Control name="maxSpeed"  type="number" min="0" required
                                value={maxSpeed} 
                                onChange={(e) => setMaxSpeed(e.target.value)}>
                            </Form.Control>
                        </Col>
                        <Col sm={2}/>
                    </Row>

                    <Row className='mt-2'>
                        <Col sm={3} align='right'><Form.Label>Number of engines</Form.Label></Col>
                        <Col sm={2}>
                            <Form.Control name="numOfMotor"  type="number" min="0" required
                                value={numOfMotor} 
                                onChange={(e) => setNumOfMotor(e.target.value)}>
                            </Form.Control>
                        </Col>
                        <Col sm={3} align='right'><Form.Label>Engine strength </Form.Label></Col>
                        <Col sm={2}>
                            <Form.Control name="motorPower"  type="number" min="0" required
                                value={motorPower} 
                                onChange={(e) => setMotorPower(e.target.value)}>
                            </Form.Control>
                        </Col>
                        <Col sm={2}/>
                    </Row>

                    <LabeledTextarea value={navigationEquipment} label="Navigation gear" inputName="navigation gear" placeholder="Type navigation gear of your boat" required onChangeFunc={setNavigationEquipment}/>
                    <LabeledTextarea value={fishingEquipment} label="Fishing gear" inputName="fishing gear" placeholder="Type fishing gear of your boat" required onChangeFunc={setFishingEquipment}/>

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