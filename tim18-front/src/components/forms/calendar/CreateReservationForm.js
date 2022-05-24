import React from 'react'
import { Card, Button, Form, Row, Col } from 'react-bootstrap';
import DateTimePicker from 'react-datetime-picker'
import moment from 'moment';
import {createAppointment} from "./../../../services/api/CalendarApi.js"
import {useState, useEffect} from 'react';
import { getAllAssetsByUser } from '../../../services/api/AssetApi.js';
import { getLogged } from '../../../services/api/LoginApi.js';
import Time from './Time.js';
import { Marginer } from '../Login/marginer/index.jsx';
import { calculateTotalPrice } from '../../../services/utils/Calculations.js';

const CreateReservationForm = ({asset, setInputs}) => {

    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());
    const [startTime, setStartTime] = useState(0);
    const [endTime, setEndTime] = useState(0);
    const [numberOfPeople, setNumOfPeople] = useState([]);
    const [clientId, setClientId] = useState({});

    const props = {numberOfPeople: numberOfPeople, startDate: startDate, endDate: endDate, price: asset.price};

    useEffect(() => {
        setInputs({startDate, endDate, startTime, endTime, numberOfPeople, clientId});
    }, [startDate, endDate, startTime, endTime, numberOfPeople])  

    const [user, setUser] = useState([]);
    useEffect(() => {
        async function fetchUser(){
            await getLogged(setUser);
        }
        fetchUser();
    }, []);
    if(!!user){
        if(user.userType === 'Client' && clientId!=user.id){
            setClientId(user.id);
        }

    return (
        <Form>
            <Form.Group className="mb-2">
                <Row>
                    <Col sm={2}>
                        <Form.Label className="mb-1">Asset:</Form.Label>
                    </Col>
                    <Col>
                        <Form.Label className="mb-1">{asset.name}</Form.Label>
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
                    { user.userType !== 'Client' &&
                    <Col>
                        {/* DOBAVITI KAO COMBOBOX SVE KLIJENTE*/}
                    </Col>}
                </Row>
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
                <Form.Label className="mb-1">Number of people: </Form.Label>
                <Form.Control name="numberOfPeople"  type="number" min="1" required value={numberOfPeople} 
                            onChange={(e) => setNumOfPeople(e.target.value)}>
                    </Form.Control>
            </Form.Group>
            <Marginer direction="vertical" margin="2em" />
            <Form.Group className="mb-2">
                <Form.Label className="mb-1">Total price: {calculateTotalPrice(props)}</Form.Label>
            </Form.Group>
        </Form>
    )
}
}

export default CreateReservationForm