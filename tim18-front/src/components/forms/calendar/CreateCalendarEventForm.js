import React from 'react';
import { Card, Button } from 'react-bootstrap';
import DateTimePicker from 'react-datetime-picker'
import {useCallback, useState} from 'react';
import './../../../assets/styles/calendar.css'
import moment from 'moment';
import {createAppointment} from "./../../../services/api/CalendarApi.js"

export default function CreateCalendarEventForm(props){
    const [startDateTime, setStartDateTime] = useState(new Date());
    const [endDateTime, setEndDateTime] = useState(new Date());
    const [type, setType] = useState("Available");
    const userId = localStorage.getItem("userId")
    const assetId = localStorage.getItem("assetId")

    const addAppointment = (e) => {
        
       // e.preventDefault();
        const fromDateTime = moment(startDateTime).format("YYYY-MM-DDTHH:mm:SS")
        const toDateTime = moment(endDateTime).format("YYYY-MM-DDTHH:mm:SS")
        const title = "Available"
        console.log(type)

        props.onChange({
            title  : 'available',
            start  : fromDateTime,
            end    : toDateTime
          })

        const userId = 2;
        const assetId = 100

        const appointmentJson = {fromDateTime, toDateTime, title, type, userId, assetId}
        console.log(appointmentJson)
        createAppointment(JSON.stringify(appointmentJson))
    }

    return (
        <div>
        <Card className='mb-5 mt-3' style={{color: "#123", borderRadius: "25px"}}>
            <Card.Body>
                <Card.Title>Add period of availablity</Card.Title>
                <div className='mb-2'>
                    <label className='lbl__create_avbl_period'>From: </label>
                    <DateTimePicker id="from" onChange={setStartDateTime} value={startDateTime}/>
                </div>
                <div className='mb-2'> 
                    <label className='lbl__create_avbl_period'>To: </label>
                    <DateTimePicker id="to" onChange={setEndDateTime} value={endDateTime}/>
                </div>
                <div className='mb-2'>
                    <label className='lbl__create_avbl_period'>Type:</label>
                    <select name="cars" id="cars" onChange={(e)=>{setType(e.target.value);}} value={type}>
                        <option value="available">Available</option>
                        <option value="specialOffer">Special offer</option>
                    </select>
                </div>
                <Button className='btn mb-2 mt-2' onClick={addAppointment}>Add appointment</Button>
            </Card.Body>
        </Card>
        </div>
    );
}