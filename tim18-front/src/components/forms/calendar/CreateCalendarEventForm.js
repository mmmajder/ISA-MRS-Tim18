import { Button } from 'react-bootstrap';
import React, { useState } from 'react';
import './../../../assets/styles/calendar.css'
import CreateSpecialOfferForm from './CreateSpecialOfferForm'
import CreateReservationForm from './CreateReservationForm'
import CreatePeriodOfAvailabiltyForm from './CreatePeriodOfAvailabiltyForm.js'


export default function CreateCalendarEventForm(props){
    const [activeForm, setActiveForm] = useState(null);

    const availableForm = () => {
        setActiveForm(<CreatePeriodOfAvailabiltyForm props={props} scope = {props.scope}/>)
    }
    const specialOfferForm = () => {
        setActiveForm(<CreateSpecialOfferForm props={props} scope = {props.scope}/>)
    }

    const reservationForm = () => {
        setActiveForm(<CreateReservationForm props={props}/>)
    }

    return (
        <div>
            <button className='btnPeriodAdd' style={{borderRadius:"0rem"}} onClick={availableForm}>Add period of availablity</button>
            <button className='btnPeriodAdd' style={{borderRadius:"0rem"}} onClick={specialOfferForm}>Add special offer</button>
            <button className='btnPeriodAdd' style={{borderRadius:"0rem"}} onClick={reservationForm}>Add reservation</button>
            {activeForm}
        </div>
    );
}