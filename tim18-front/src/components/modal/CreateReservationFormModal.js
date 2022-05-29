import React from 'react'
import { Button, Modal } from 'react-bootstrap';
import {useState, useEffect} from 'react';
import { toHHMMSS } from '../../services/utils/TimeUtils';
import CreateReservationForm from '../forms/calendar/CreateReservationForm';
import { makeReservation } from '../../services/api/ReservationApi'

const CreateReservationFormModal = ({props}) => {
    const [inputs, setInputs] = useState({});
    const [formValid, setFormValid] = useState(true);
    const [validations, setValidations] = useState({fromDateTime: false, 
                                                    toDateTime: false, 
                                                    assetId: false, 
                                                    clientId: false, 
                                                    numOfPeople: true, 
                                                    totalPrice: true});

    const fromDateTime = inputs.startDate + "T" + toHHMMSS(inputs.startTime);
    const toDateTime = inputs.endDate + "T" + toHHMMSS(inputs.endTime);

    const appointmentJson = {fromDateTime: fromDateTime, toDateTime: toDateTime, assetId: inputs.assetId ,clientId: inputs.clientId, 
      numOfPeople: inputs.numberOfPeople, totalPrice:  inputs.totalPrice};

    useEffect(() => {
      setFormValid(validations.fromDateTime && 
                    validations.toDateTime && 
                    validations.assetId && 
                    validations.clientId && 
                    validations.numOfPeople && 
                    validations.totalPrice);
    }, [validations]);

    const makeReservationCallback = (data) => {
      if(data){
          alert("Successfully created reservation.\nPlease check your email.")
          props.newReservation({
            title  : 'Reserved',
            start  : fromDateTime,
            end    : toDateTime,
            resourceId : inputs.assetId,
            backgroundColor : "grey",
            borderColor : "grey"
          }, fromDateTime, toDateTime)
          props.setShow(false);
      }
      else{
        alert("Oops, you are not able to create this reservation, please try again.")
      }
    }
    return (
      <>  
        <Modal show={props.show} onHide={() => props.setShow(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Create new reservation</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <CreateReservationForm assetId={props.assetId} setInputs={setInputs} setValidations={setValidations}/>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => props.setShow(false)}>
            Close
          </Button>
          <Button variant="primary" type="submit" onClick={() => makeReservation(makeReservationCallback, appointmentJson)} disabled={!formValid}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
      </>
    );
}



export default CreateReservationFormModal