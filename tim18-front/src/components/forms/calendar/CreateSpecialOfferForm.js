import React from 'react'
import {useState, useEffect} from 'react';
import { Card, Button, Form } from 'react-bootstrap';
import DateTimePicker from 'react-datetime-picker'
import { getAllAssetsByUser } from '../../../services/api/AssetApi.js';
import moment from 'moment';
import {createAppointment} from "./../../../services/api/CalendarApi.js"
import { Row, Col } from 'react-bootstrap';


const CreateSpecialOfferForm = (props) => {
    const [startDateTime, setStartDateTime] = useState(new Date());
    const [endDateTime, setEndDateTime] = useState(new Date());
    const [offerUntilTime, setOfferUntilTime] = useState(new Date());
    const [assetId, setAssetId] = useState({});
    const [assets, setAssets] = useState([])
    const [discount, setDiscount] = useState(0)

    const userId = localStorage.getItem("userId")

    const addAppointment = (e) => {
        const fromDateTime = moment(startDateTime).format("YYYY-MM-DDTHH:mm:SS")
        const toDateTime = moment(endDateTime).format("YYYY-MM-DDTHH:mm:SS")
        const type = "SpecialOffer"
        const offerUntil = moment(offerUntilTime).format("YYYY-MM-DDTHH:mm:SS")
        props.props.onChange({
            title  : 'Special offer',
            start  : fromDateTime,
            end    : toDateTime,
            backgroundColor : "orange",
            borderColor : "orange",
            resourceId : assetId
          })
        const appointmentJson = {fromDateTime, toDateTime, type, userId, assetId, offerUntil, discount}

        createAppointment(JSON.stringify(appointmentJson))
    }

    useEffect(() => {
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
                        <Card.Title>Add special offer</Card.Title>
                        <Row className='mb-2'>
                        <Col sm='1'>
                            <label className='lbl__create_avbl_period'>From: </label>
                        </Col>
                        <Col sm='5'>
                            <DateTimePicker id="from" onChange={setStartDateTime} value={startDateTime}/>
                        </Col>
                        <Col sm='1'>
                            <label className='lbl__create_avbl_period'>Discount (in %): </label>
                        </Col>
                        <Col sm='5'>
                            <input className='col-sm-5' onChange={event => setDiscount(event.target.value)}></input>
                        </Col>
                        
                        </Row>
                        <Row className='mb-2'>
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
                            {props.scope=="global" ? (<Form.Select aria-label="Default select example" name="assets" id="assets" value={assetId} onChange={(e)=>{setAssetId(e.target.value);}}>
                                <option></option>
                                { assets.map((asset) => <option value={asset.id}>{asset.name}</option>) }
                            </Form.Select>) : []}
                        </Col>
                        </Row>
                        <Row className='mb-2'>
                        <Col sm='1'>
                            <label className='lbl__create_avbl_period'>Expiration: </label>
                        </Col>
                        <Col sm='5'>
                            <DateTimePicker id="to" onChange={setOfferUntilTime} value={offerUntilTime}/>
                        </Col>
                        
                        </Row>
                        <Button className='mb-2 mt-2' style={{backgroundColor: "#5da4b4", borderColor: "#5da4b4"}} onClick={addAppointment}>Add appointment</Button>
                    </Card.Body>
                </Card>
            )
        }
}

export default CreateSpecialOfferForm