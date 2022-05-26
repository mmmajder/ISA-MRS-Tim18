import React from 'react'
import { Button, Modal } from 'react-bootstrap';
import {useState, useEffect} from 'react';
import { getLogged } from '../../services/api/LoginApi.js';
import { toHHMMSS } from '../../services/utils/TimeUtils';
import CreateReservationForm from '../forms/calendar/CreateReservationForm';
import { makeReservation } from '../../services/api/ReservationApi'

const CreateReservationFormModal = ({props}) => {
    const [inputs, setInputs] = useState({});

    const fromDateTime = inputs.startDate + "T" + toHHMMSS(inputs.startTime);
    const toDateTime = inputs.endDate + "T" + toHHMMSS(inputs.endTime);

    const appointmentJson = {fromDateTime: fromDateTime, toDateTime: toDateTime, assetId: props.assetId ,clientId: inputs.clientId, 
      numOfPeople: inputs.numberOfPeople, type: "RegularOffer"};

    const resCallback = (data) => {
      if(data){
          alert("Successfully created reservation.\nPlease check your email.")
      }
      else{
        alert("Oops, you are not able to create this reservation, please try again.")
      }
    }
    const createReservation = () => {
      props.setShow(false);
      makeReservation(resCallback, appointmentJson);
    }

    return (
      <>  
        <Modal show={props.show} onHide={() => props.setShow(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Create new reservation</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <CreateReservationForm assetParam={props.asset} assetId={props.assetId} setInputs={setInputs}/>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => props.setShow(false)}>
            Close
          </Button>
          <Button variant="primary" type="submit" onClick={createReservation}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
      </>
    );
}



export default CreateReservationFormModal