import React from 'react'
import { Card, Button, Form, Row, Col } from 'react-bootstrap';
import DateTimePicker from 'react-datetime-picker'
import moment from 'moment';
import {createAppointment} from "./../../../services/api/CalendarApi.js"
import {useState, useEffect} from 'react';
import { getAllAssetsByUser } from '../../../services/api/AssetApi.js';

const CreatePeriodOfAvailabiltyForm = (props) => {
    const [startDateTime, setStartDateTime] = useState(new Date());
    const [endDateTime, setEndDateTime] = useState(new Date());
    const [assetId, setAssetId] = useState({});
    const [assets, setAssets] = useState([])

    const userId = localStorage.getItem("userId")

    const addAppointment = (e) => {
        
        const fromDateTime = moment(startDateTime).format("YYYY-MM-DDTHH:mm:SS")
        const toDateTime = moment(endDateTime).format("YYYY-MM-DDTHH:mm:SS")
        const type = "Available"

        props.props.onChange({
            title  : 'Available',
            start  : fromDateTime,
            end    : toDateTime,
            resourceId : assetId
          })
        
        const appointmentJson = {fromDateTime, toDateTime, type, userId, assetId}
        createAppointment(JSON.stringify(appointmentJson))
    }

    useEffect(() => {
        setAssetId(localStorage.getItem("assetId"))
        async function fetchAssets(){
            const requestData = await getAllAssetsByUser(userId);
            setAssets(!!requestData ? requestData.data : {});
            return requestData;
        }
        fetchAssets();
    }, [])

    if (!!assets) {
        return (
            <Card className='mb-5 mt-3' style={{color: "#123", borderRadius: "25px"}}>
                    <Card.Body>
                        <Card.Title className='mb-2'>Add period of availablity</Card.Title>
                        <div className='mb-2 mt-2'>
                            <Row>
                            <Col sm='1'>
                                <label className='lbl__create_avbl_period'>From: </label>
                            </Col>
                            <Col sm='5'>
                            <DateTimePicker id="from" onChange={setStartDateTime} value={startDateTime}/>
                            </Col>
                            <Col sm='1'>
                                <label className='lbl__create_avbl_period'>Recurring: </label>
                            </Col>
                            <Col sm='5'>
                                <div className='mb-2'>
                                    <Form>
                                    <Form.Check 
                                        type="switch"
                                        id="custom-switch"
                                    />
                                    </Form>
                                </div>
                            </Col>
                            </Row>
                        </div>
                        <div className='mb-2 mt-2'>
                            <Row>
                            <Col sm='1'>
                                <label className='lbl__create_avbl_period'>To: </label>
                            </Col>
                            <Col sm='5'>
                                <DateTimePicker id="to" onChange={setEndDateTime} value={endDateTime}/>
                            </Col>
                            
                            <Col sm='1'>
                                {props.scope=="global" ? (<label className='lbl__create_avbl_period'>Asset:</label>) : []}
                            </Col>
                            <Col sm='5'>
                                {props.scope=="global" ? (
                                    <Form.Select aria-label="Default select example" name="assets" id="assets" value={assetId} onChange={(e)=>{setAssetId(e.target.value);}}>
                                    <option></option>
                                { assets.map((asset) => <option value={asset.id}>{asset.name}</option>) }
                                </Form.Select>) : []}
                            </Col>
                            </Row>
                        </div>
                        
                        <Button className='mb-2 mt-2' style={{backgroundColor: "#5da4b4", borderColor: "#5da4b4"}} onClick={addAppointment}>Add appointment</Button>
                    </Card.Body>
                </Card>
            )
        }
    }
    


export default CreatePeriodOfAvailabiltyForm