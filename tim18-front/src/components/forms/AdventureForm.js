import React from 'react';
import { Form, Row, Col, Button } from 'react-bootstrap';
import LabeledInput from './LabeledInput';
import LabeledTextarea from './LabeledTextarea';
import '../../assets/styles/buttons.css';
import {useCallback, useState, useEffect} from 'react';
import { updateAsset, createNewAsset } from '../../services/api/AssetApi';
import { useNavigate  } from "react-router-dom";
import { getLogged } from '../../services/api/LoginApi';

export default function AdventureForm({adventure, buttonText, id}){

    const navigate = useNavigate ();

    const [name, setName] = useState();
    const [address, setAddress] = useState();
    const [description, setDescription] = useState();
    const [rules, setRules] = useState();
    const [numOfPeople, setNumOfPeople] = useState();
    const [cancelationFee, setCancelationFee] = useState();
    const [fishingEquipment, setFishingEquipment] = useState();
    const[price, setPrice] = useState();

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

    const [user, setUser] = useState([]);
    useEffect(() => {
        async function fetchUser(){
            await getLogged(setUser);
        }
        fetchUser();
    }, [])

    let renterId = user.id;

    const postRequest = useCallback(
        (e) => {
            e.preventDefault();
            console.log("price"+price)
            const adventureJson = {name, address, description, rules, numOfPeople, cancelationFee, assetType,
                fishingEquipment, renterId, price};
            createNewAsset(adventureJson).then(
                (response) => {

                }
            );
            
            // const request = {
            //     method: 'POST',
            //     headers: { 'Content-Type': 'application/json' },
            //     body: JSON.stringify(adventureJson)
            // };
            // console.log(JSON.stringify(adventureJson));
            // fetch('http://localhost:8000/assets', request) 
            //     .then(response => {})
        }, [name, address, description, rules, numOfPeople, cancelationFee, fishingEquipment, price]
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
            const response = updateAsset(updatedadventure.id, updatedadventure);
            navigate('/resorts/' + id);
    }, [id, name, address, description, rules, numOfPeople, cancelationFee, fishingEquipment]);

    const onClickFunction = id === -1 ? postRequest : putRequest;

    const priceInput =  id === -1 ? <>
                        <Col sm={2} align='right'><Form.Label>Price</Form.Label></Col>
                        <Col sm={1}>
                            <Form.Control name="price"  type="number" min="0" required
                                value={price} 
                                onChange={(e) => setPrice(e.target.value)}>
                            </Form.Control>
                        </Col>
                        </> : null

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
                        <Col sm={2} align='right'><Form.Label>Number of people</Form.Label></Col>
                        <Col sm={1}>
                            <Form.Control name="numOfPeople"  type="number" min="1" required
                                value={numOfPeople} 
                                onChange={(e) => setNumOfPeople(e.target.value)}>
                            </Form.Control>
                        </Col>
                        <Col sm={1} />
                        <Col sm={2} align='right'><Form.Label>Cancelation fee in % </Form.Label></Col>
                        <Col sm={1}>
                            <Form.Control name="cancelationFee"  type="number" min="0" max="100" required
                                value={cancelationFee} 
                                onChange={(e) => setCancelationFee(e.target.value)}>
                            </Form.Control>
                        </Col>
                        <Col sm={1}/>
                        {priceInput}
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