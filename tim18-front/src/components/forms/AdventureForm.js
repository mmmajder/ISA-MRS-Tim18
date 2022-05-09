import React from 'react';
import { Form, Row, Col, Button } from 'react-bootstrap';
import LabeledInput from './LabeledInput';
import LabeledTextarea from './LabeledTextarea';
import '../../assets/styles/buttons.css';
import {useCallback, useState, useEffect} from 'react';
import { updateAsset } from '../../services/api/AssetApi';
import { useNavigate  } from "react-router-dom";

export default function AdventureForm({adventure, buttonText, id}){

    const navigate = useNavigate ();

    const [name, setName] = useState();
    const [address, setAddress] = useState();
    const [description, setDescription] = useState();
    const [rules, setRules] = useState();
    const [numOfPeople, setNumOfPeople] = useState();
    const [cancelationFee, setCancelationFee] = useState();
    const [fishingEquipment, setFishingEquipment] = useState();

    const assetType = "FISHING_ADVENTURE";
    
    // sets resort's values if it's updateResortForm
    useEffect(() => {
        if (id !== -1){
            setName(adventure.name);
            setAddress(adventure.address);
            setDescription(adventure.description);
            setRules(adventure.rules);
            setNumOfPeople(adventure.numOfPeople);
            setCancelationFee(adventure.cancelationConditions);

            setFishingEquipment(adventure.fishingEquipment);
        }
    }, [adventure])

    const postRequest = useCallback(
        (e) => {
            e.preventDefault();
            const adventureJson = {name, address, description, rules, numOfPeople, cancelationFee, assetType,
                fishingEquipment}
            const request = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(adventureJson)
            };
            fetch('http://localhost:8000/assets', request) 
                .then(response => response.json())
        }, [name, address, description, rules, numOfPeople, cancelationFee, fishingEquipment]
    )

    const putRequest = useCallback(() => {
            let updatedadventure = {
                id : id,
                name : name,
                address : address,
                description : description,
                rules : rules,
                numOfPeople : numOfPeople,
                cancelationConditions : cancelationFee,
                assetType : assetType,
                fishingEquipment:fishingEquipment
            };
            console.log(updatedadventure);
            const response = updateAsset(updatedadventure.id, updatedadventure);
            console.log(response.data);
            navigate('/resorts/' + id);
    }, [id, name, address, description, rules, numOfPeople, cancelationFee, fishingEquipment]);

    const onClickFunction = id === -1 ? postRequest : putRequest;

    return (<>
    <Row className='mt-5' >
        <Col sm={2} />
        <div className="borderedBlock">
            <Col sm={true} >
                <Form>
                    <LabeledInput value={name} label="Name" inputName="name" placeholder="Type name of your adventure" required onChangeFunc={setName}/>
                    <LabeledInput value={address} label="Address" inputName="address" placeholder="Type address of your adventure" required onChangeFunc={setAddress}/>
                    <LabeledTextarea value={description} label="Description" inputName="description" placeholder="Type description of your adventure" required onChangeFunc={setDescription}/>
                    <LabeledTextarea value={rules} label="Rules" inputName="rules" placeholder="Type rules of your adventure" required onChangeFunc={setRules}/>
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

                    <LabeledTextarea value={fishingEquipment} label="Fishing gear" inputName="fishing gear" placeholder="Type fishing gear of your adventure" required onChangeFunc={setFishingEquipment}/>

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