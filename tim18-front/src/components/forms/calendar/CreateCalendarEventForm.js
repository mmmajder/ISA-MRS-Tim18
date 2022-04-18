import React from 'react';
import { Card, Button } from 'react-bootstrap';
import '../../../assets/styles/calendar.css'
import DateTimePicker from 'react-datetime-picker'
import {useCallback, useState} from 'react';
import moment from 'moment';

export default function CreateCalendarEventForm(props){
    const [startDateTime, setStartDateTime] = useState(new Date());
    const [endDateTime, setEndDateTime] = useState(new Date());
    const [type, setType] = useState("Available");
    const userId = localStorage.getItem("userId")
    const assetId = localStorage.getItem("assetId")
    const addAppointment = useCallback(
        (e) => {
            e.preventDefault();
            console.log(type)
            console.log(startDateTime)
            console.log(endDateTime)
            const resortJson = {startDateTime, endDateTime, type, userId, assetId}
            props.onChange({
                title  : 'available',
                start  : moment(startDateTime).format("YYYY-MM-DD HH:mm:SS"),
                end    : moment(endDateTime).format("YYYY-MM-DD HH:mm:SS")
              })
            /*const request = {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(resortJson)
            };
            fetch('http://localhost:8000/calendar', request) 
                .then(response => response.json())
        }, [startDateTime, endDateTime, type, userId, assetId]*/
    })
    return (
        <div>
        <Card>
            <Card.Body>
                <Card.Title>Add period of availablity</Card.Title>
                <div className="grid-container">
                    <label>From: </label>
                    <DateTimePicker id="from" onChange={setStartDateTime} value={startDateTime}/>
                    <label>To: </label>
                    <DateTimePicker id="to" onChange={setEndDateTime} value={endDateTime}/>
                    <label>Type:</label>
                    <select name="cars" id="cars" onChange={(e)=>{setType(e.target.value);}} value={type}>
                        <option value="available">Available</option>
                        <option value="specialOffer">Special offer</option>
                    </select>
                </div>
                <Button variant="primary" onClick={addAppointment}>Add appointment</Button>
            </Card.Body>
        </Card>
        </div>
    );
}