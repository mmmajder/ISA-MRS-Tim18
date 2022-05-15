import React from 'react'
import { Card, Button, Form } from 'react-bootstrap';
import DateTimePicker from 'react-datetime-picker'
import moment from 'moment';
import {createAppointment} from "./../../../services/api/CalendarApi.js"
import {useState, useEffect} from 'react';
import { getAllAssetsByUser } from '../../../services/api/AssetApi.js';
import { getLogged } from '../../../services/api/LoginApi.js';

const CreateReservationForm = (props) => {
    const [startDateTime, setStartDateTime] = useState(new Date());
    const [endDateTime, setEndDateTime] = useState(new Date());
    const [assetId, setAssetId] = useState({});
    const [assets, setAssets] = useState([])

    const [user, setUser] = useState([]);
    useEffect(() => {
        async function fetchUser(){
            await getLogged(setUser);
        }
        fetchUser();
    }, [])

    const userId = user.id;

    
    const addAppointment = (e) => {
    }

    useEffect(() => {
        async function fetchAssets(){
            const requestData = await getAllAssetsByUser(userId);
            setAssets(!!requestData ? requestData.data : {});
            return requestData;
        }
        fetchAssets();
    }, [user])


    if (!!assets) {
        return (
            <Card className='mb-5 mt-3' style={{color: "#123", borderRadius: "25px"}}>
                    <Card.Body>
                        <Card.Title >Add period of availablity</Card.Title>
                        <div className='mb-2'>
                            <label className='lbl__create_avbl_period'>From: </label>
                            <DateTimePicker id="from" onChange={setStartDateTime} value={startDateTime}/>
                        </div>
                        <div className='mb-2'> 
                            <label className='lbl__create_avbl_period'>To: </label>
                            <DateTimePicker id="to" onChange={setEndDateTime} value={endDateTime}/>
                        </div>
                        <div className='mb-2'>
                            <label className='lbl__create_avbl_period'>Asset:</label>
                            <select name="assets" id="assets" onChange={(e)=>{setAssetId(e.target.value);}} value={assetId}>
                                { assets.map((asset) => <option value={asset.id}>{asset.name}</option>) }
                            </select>
                        </div>
                        <Button className='mb-2 mt-2' style={{backgroundColor: "#5da4b4", borderColor: "#5da4b4"}} onClick={addAppointment}>Add appointment</Button>
                    </Card.Body>
                </Card>
            )
        }
}

export default CreateReservationForm