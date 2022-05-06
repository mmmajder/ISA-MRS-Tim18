import React from 'react'
import {useState, useEffect} from 'react';
import { Card, Button, Form } from 'react-bootstrap';
import DateTimePicker from 'react-datetime-picker'
import { getAllAssetsByUser } from '../../../services/api/AssetApi.js';
import moment from 'moment';
import {createAppointment} from "./../../../services/api/CalendarApi.js"

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
            borderColor : "orange"
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
                        <div className='mb-2'>
                            <label className='lbl__create_avbl_period'>From: </label>
                            <DateTimePicker id="from" onChange={setStartDateTime} value={startDateTime}/>
                        </div>
                        <div className='mb-2'> 
                            <label className='lbl__create_avbl_period'>To: </label>
                            <DateTimePicker id="to" onChange={setEndDateTime} value={endDateTime}/>
                        </div>
                        <div className='mb-2'> 
                            <label className='lbl__create_avbl_period'>Offer expires at: </label>
                            <DateTimePicker id="to" onChange={setOfferUntilTime} value={offerUntilTime}/>
                        </div>
                        {props.scope=="global" ? (<div className='mb-2'>
                            <label className='lbl__create_avbl_period'>Asset:</label>
                            <select name="assets" id="assets" value={assetId} onChange={(e)=>{setAssetId(e.target.value);}} >
                                <option></option>
                                { assets.map((asset) => <option value={asset.id}>{asset.name}</option>) }
                            </select>
                        </div>) : []}
                        <div className='mb-2'> 
                            <label className='lbl__create_avbl_period'>Discount (in %): </label>
                            <input onChange={event => setDiscount(event.target.value)}></input>
                        </div>
                        <Button className='mb-2 mt-2' style={{backgroundColor: "#5da4b4", borderColor: "#5da4b4"}} onClick={addAppointment}>Add appointment</Button>
                    </Card.Body>
                </Card>
            )
        }
}

export default CreateSpecialOfferForm