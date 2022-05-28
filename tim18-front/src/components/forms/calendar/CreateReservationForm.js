import React from 'react'
import { Form, Row, Col } from 'react-bootstrap';
import {useState, useEffect, useCallback} from 'react';
import { getAllAssetsByUser } from '../../../services/api/AssetApi.js';
import { getLogged } from '../../../services/api/LoginApi.js';
import Time from './Time.js';
import { Marginer } from '../Login/marginer/index.jsx';
import { calculateTotalPrice } from '../../../services/utils/Calculations.js';
import { getAssetById, getCallbackAssetById } from '../../../services/api/AssetApi.js';
import { getAssetTodayPrice } from '../../../services/api/AssetApi.js';
import { getAllMappedClients } from '../../../services/api/ClientApi.js';
import { onlyLetters, onlyNumbers, checkLettersInput, checkNumInput, capitalizeString, isEmpty} from '../../../services/utils/InputValidation'
import Select from 'react-select';

const CreateReservationForm = ({setInputs, assetId, setValidations}) => {
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());
    const [startTime, setStartTime] = useState(0);
    const [endTime, setEndTime] = useState(0);
    const [numberOfPeople, setNumOfPeople] = useState(1);
    const [clientId, setClientId] = useState({});
    const [asset, setAsset] = useState({});
    const [assetPrice, setAssetPrice] = useState("");
    const [totalPrice, setPrice] = useState();
    const [hours, setHours] = useState(0);
    const [assets, setAssets] = useState();
    const [user, setUser] = useState();
    const [clients, setClients] = useState();

    const props = {numberOfPeople: numberOfPeople, 
                   startDate: startDate, endDate: endDate, 
                   startTime: startTime, endTime: endTime, 
                   price: assetPrice};

    const handleChange = (event, validationFunc) => {
        const name = event.target.name;
        var value = event.target.value;
        const valid = validationFunc(value)
        setInputs(values => ({...values, [name]: value}))
        console.log("name")
        console.log(name)
        console.log("valid")
        console.log(valid)
        setValidations(values => ({...values, [name]: valid}))
    }
    

   useEffect(() => {
        setInputs({startDate, endDate, startTime, endTime, numberOfPeople, clientId, totalPrice, assetId: asset.id});
        calculateTotalPrice(props, setPrice, setHours);
    }, [startDate, endDate, startTime, endTime, numberOfPeople, totalPrice])

    
    useEffect(() => {
        async function fetchUser(){
            await getLogged(setUser);
        }
        fetchUser();
    }, []);

    useEffect(() =>{
        if(user===undefined){
            return;
        }
        if(user.userType === 'Client' && clientId !== user.id){
            setValidations(values => ({...values, ["clientId"]: true}))
            setClientId(user.id);
        }
        assetId===undefined ? fetchAssets() : setValidations(values => ({...values, ["assetId"]: true}))
        
        if(clients===undefined || clientId === undefined){
            fetchClients();   
        }
    }, [user])


    useEffect(() => {
        async function fetchAsset(){
            const requestData = await getAssetById(assetId);
            setAsset(!!requestData ? requestData.data : {});
            return requestData;
        }
        fetchAsset();
    }, [assetId])

    const getAssetPrice = useCallback(
        () => {
            getAssetTodayPrice(asset.id).then((response) =>{
                let price = response.data.price;
                setAssetPrice(price);
                setPrice(price);
            });
        }, [asset, setAssetPrice]
    )

    useEffect(() => {
        if (asset !== undefined){
            getAssetPrice();
        }
    }, [asset]);
    
    async function fetchAssets(){
        const requestData = await getAllAssetsByUser(user.id);
        setAssets(!!requestData ? requestData.data : {});
        return requestData;
    }

    const assetsElem = () => {
        try {
            return (
                <Form.Select aria-label="Default select example" name="assetId" id="assets" value={assetId} 
                    onChange={(e)=>{handleChange(e, isEmpty); getCallbackAssetById(setAsset, e.target.value)}}>
                    <option></option>
                    { assets.map((asset) => <option value={asset.id}>{asset.name}</option>) }
                </Form.Select>)
            } 
        catch (e) {
            return null;
            }
    }

    async function fetchClients(){
        await getAllMappedClients(setClients);
    }

    if(!!user){
    return (
        <Form>
            <Form.Group className="mb-2">
                <Row>
                    <Col sm={2}>
                        <Form.Label className="mb-1">Asset:</Form.Label>
                    </Col>
                    <Col>
                        {assetId===undefined ? assetsElem() : <Form.Label className="mb-1">{asset?.name}</Form.Label> }
                    </Col>
                </Row>
            </Form.Group>

            <Form.Group className="mb-2">
                <Row>
                    <Col sm={2}>
                        <Form.Label className="mb-1">Client:</Form.Label>
                    </Col>
                    <Col>
                        { user.userType === 'Client' && <Form.Label className="mb-1">{user.firstName} {user.lastName}</Form.Label> }
                        { user.userType !== 'Client' && clients &&
                        <Form.Select name="clientId" onChange={(e)=>{handleChange(e, isEmpty); setClientId(e.target.value)}}>
                        <option></option>
                        { clients.map((client) => <option value={client.value}>{client.name}</option>) }
                        </Form.Select>
                        }
                    </Col>
                </Row>
            </Form.Group>

            <Form.Group className="mb-2">
                <Form.Label className="mb-1">From: </Form.Label>
                <Form.Control className="mb-1" type="date" name="fromDateTime" placeholder="Start date" value={startDate}
                onChange={(e) => {handleChange(e, isEmpty); setStartDate(e.target.value)}}/>
                <Time setTime={setStartTime} time={startTime}></Time>
            </Form.Group>
            <Form.Group className="mb-2">
                <Form.Label className="mb-1">To: </Form.Label>
                <Form.Control className="mb-1" type="date" name="toDateTime" placeholder="End date" value={endDate}
                onChange={(e) => {handleChange(e, isEmpty); setEndDate(e.target.value)}}/>
                <Time setTime={setEndTime} time={endTime}></Time>
            </Form.Group>
            <Form.Group className="mb-2">
                <Form.Label className="mb-1">Number of people: (max number = {asset?.numOfPeople})
                </Form.Label>
                <Form.Control name="numberOfPeople"  type="number" min="1" max={asset?.numOfPeople}
                              required value={numberOfPeople} onChange={(e) => setNumOfPeople(e.target.value)}>
                </Form.Control>
            </Form.Group>
            <Marginer direction="vertical" margin="2em" />
            <Form.Group className="mb-2">
                <Row>
                    <Col sm={4}>
                        <Form.Label className="mb-1">Price per hour:</Form.Label>
                    </Col>
                    <Col>
                        <Form.Label className="mb-1"> {assetPrice} €</Form.Label>
                    </Col>
                </Row>
                <Row>
                    <Col sm={4}>
                        <Form.Label className="mb-1">Number of hours:</Form.Label>
                    </Col>
                    <Col>
                        <Form.Label className="mb-1"> {hours} h</Form.Label>
                    </Col>
                </Row>
                <Row>
                    <Col sm={4}>
                        <Form.Label className="mb-1">Total price:</Form.Label>
                    </Col>
                    <Col>
                        <Form.Label className="mb-1"> {totalPrice} €</Form.Label>
                    </Col>
                </Row>
            </Form.Group>
        </Form>
    )
}
}

export default CreateReservationForm