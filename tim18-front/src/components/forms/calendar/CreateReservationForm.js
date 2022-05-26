import React from 'react'
import { Card, Button, Form, Row, Col } from 'react-bootstrap';
import DateTimePicker from 'react-datetime-picker'
import moment from 'moment';
import {createAppointment} from "./../../../services/api/CalendarApi.js"
import {useState, useEffect, useCallback} from 'react';
import { getAllAssetsByUser } from '../../../services/api/AssetApi.js';
import { getLogged } from '../../../services/api/LoginApi.js';
import Time from './Time.js';
import { Marginer } from '../Login/marginer/index.jsx';
import { calculateTotalPrice } from '../../../services/utils/Calculations.js';
import { getAssetById } from '../../../services/api/AssetApi.js';
import { getAssetTodayPrice } from '../../../services/api/AssetApi.js';
import { getAllMappedClients } from '../../../services/api/ClientApi.js';
import Select from 'react-select';

const CreateReservationForm = ({assetParam, setInputs, assetId}) => {
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
    
    const props = {numberOfPeople: numberOfPeople, 
                   startDate: startDate, endDate: endDate, 
                   startTime: startTime, endTime: endTime, 
                   price: assetParam!=undefined ? assetParam.price : assetPrice};


    useEffect(() => {
        setInputs({startDate, endDate, startTime, endTime, numberOfPeople, clientId, totalPrice});
        calculateTotalPrice(props, setPrice, setHours);
    }, [startDate, endDate, startTime, endTime, numberOfPeople, totalPrice])  

    const [user, setUser] = useState([]);
    useEffect(() => {
        async function fetchUser(){
            await getLogged(setUser);
        }
        fetchUser();
    }, []);


    useEffect(() => {
        async function fetchAsset(){
            const requestData = await getAssetById(assetId);
            setAsset(!!requestData ? requestData.data : {});
            return requestData;
        }
        if(assetParam==undefined){
            fetchAsset();
        }
        
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
        if (asset != undefined){
            getAssetPrice();
        }
    }, [asset]);

    useEffect(() => {
        async function fetchUser(){
            await getLogged(setUser);
        }
        fetchUser();
    }, []);
    
    

    const [clients, setClients] = useState();
    async function fetchClients(){
        await getAllMappedClients(setClients);
    }

    if(!!user){
        if(user.userType === 'Client' && clientId!=user.id){
            setClientId(user.id);
        }
        else{
            if(clients===undefined){
                fetchClients();   
            }
             
        }
    return (
        <Form>
            <Form.Group className="mb-2">
                <Row>
                    <Col sm={2}>
                        <Form.Label className="mb-1">Asset:</Form.Label>
                    </Col>
                    <Col>
                        <Form.Label className="mb-1">{assetParam!=undefined ? assetParam?.name : asset?.name}</Form.Label>
                    </Col>
                </Row>
            </Form.Group>

            <Form.Group className="mb-2">
                <Row>
                    <Col sm={2}>
                        <Form.Label className="mb-1">Client:</Form.Label>
                    </Col>
                    { user.userType === 'Client' &&
                    <Col>
                        <Form.Label className="mb-1">{user.firstName} {user.lastName}</Form.Label>
                    </Col>}
                </Row>
                { user.userType !== 'Client' &&
                    <Col>
                        <Select options={clients} onChange={(selected) => setClientId(selected.value)}/>
                    </Col>}
            </Form.Group>

            <Form.Group className="mb-2">
                <Form.Label className="mb-1">From: </Form.Label>
                <Form.Control className="mb-1" type="date" name="dob" placeholder="Start date" value={startDate}
                onChange={(e) => setStartDate(e.target.value)}/>
                <Time setTime={setStartTime} time={startTime}></Time>
            </Form.Group>
            <Form.Group className="mb-2">
                <Form.Label className="mb-1">To: </Form.Label>
                <Form.Control className="mb-1" type="date" name="dob" placeholder="End date" value={endDate}
                onChange={(e) => setEndDate(e.target.value)}/>
                <Time setTime={setEndTime} time={endTime}></Time>
            </Form.Group>
            <Form.Group className="mb-2">
                <Form.Label className="mb-1">Number of people: (max number = {assetParam!=undefined ? assetParam.numOfPeople : asset.numOfPeople})
                </Form.Label>
                <Form.Control name="numberOfPeople"  type="number" min="1" max={assetParam!=undefined ? assetParam.numOfPeople : asset.numOfPeople}
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
                        <Form.Label className="mb-1"> {assetParam!=undefined ? assetParam.price : assetPrice} €</Form.Label>
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