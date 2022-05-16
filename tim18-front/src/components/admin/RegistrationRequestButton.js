import React from 'react'
import FixedWidthRegButton from '../buttons/FixedWidthRegButton'
import { Button } from 'react-bootstrap';

const RegistrationRequestButton = ({request, text, onClickFunction}) => {
    let style = "formButton"
    if (text=="Decline") {
      style = "declineButton"
    }
    return <Button variant="custom" className={`acceptReqReq ${style} mt-3`} onClick={onClickFunction}>
      {text}
    </Button>
}

export default RegistrationRequestButton