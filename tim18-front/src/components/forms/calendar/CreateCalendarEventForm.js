import { Button } from 'react-bootstrap';
import React, { useEffect, useState } from 'react';
import './../../../assets/styles/calendar.css'
import CreateReservationForm from './CreateReservationForm'
import CreatePeriodOfAvailabilityModal from './../../modal/CreatePeriodOfAvailabilityModal'
import CreateSpecialOfferFormModal from '../../modal/CreateSpecialOfferFormModal';
import CreateReservationFormModal from '../../modal/CreateReservationFormModal';
import RemovePeriodOfAvailabilityModal from '../../modal/RemovePeriodOfAvailabilityModal';
import { getRole } from '../../../services/AuthService/AuthService'
import { getLogged } from '../../../services/api/LoginApi';
import { doesRenterOwn } from '../../../services/api/RenterApi';


export default function CreateCalendarEventForm(props){
    const [activeForm, setActiveForm] = useState(null);
    const userType = getRole()
    const [user, setUser] = useState()
    const [isMyAsset, setIsMyAsset] = useState(false);

    const availableForm = () => {
        setActiveForm(<CreatePeriodOfAvailabilityModal props={props} scope = {props.scope}/>)
    }
    const specialOfferForm = () => {
        setActiveForm(<CreateSpecialOfferFormModal props={props} scope = {props.scope}/>)
    }

    const reservationForm = () => {
        setActiveForm(<CreateReservationFormModal props={props}/>)
    }

    const periodRemovedCallback = (fromDateTime, toDateTime) => {
        props.periodRemoved(fromDateTime, toDateTime)
    }

    const removePeriodOfAvailability = () => {
        setActiveForm(<RemovePeriodOfAvailabilityModal props={props} scope = {props.scope} periodRemoved = {periodRemovedCallback}/>)
    }

    useEffect(() => {
        props.setShow(true)
    }, [activeForm])

    useEffect(() => {
        async function fetchUser(){
            await getLogged(setUser);
        }
        fetchUser();
    }, [])

    useEffect(() => {
        if (!!localStorage.getItem("assetId")  && !!user && (userType==="BoatRenter" ||userType === 'FishingInstructor' || userType === 'ResortRenter')){
            doesRenterOwn(user.id, localStorage.getItem("assetId")).then(
                (response) => {
                    setIsMyAsset(response.data);
                }
            )
        }
    }, [user, userType])


    if (!!user)
    return (
        <div>
            {userType!=="Client" && userType!=="Guest" && userType!=="Admin" && isMyAsset ? <>
            <button className='btnPeriodAdd' style={{borderRadius:"0rem"}} onClick={availableForm}>Add period of availablity</button>
            <button className='btnPeriodAdd' style={{borderRadius:"0rem"}} onClick={removePeriodOfAvailability}>Remove period of availablity</button>
            <button className='btnPeriodAdd' style={{borderRadius:"0rem"}} onClick={specialOfferForm}>Add special offer</button>
            <button className='btnPeriodAdd' style={{borderRadius:"0rem"}} onClick={reservationForm}>Add reservation</button>
            </> : []}
            {props.show ? activeForm : <></>}
        </div>
    );
}