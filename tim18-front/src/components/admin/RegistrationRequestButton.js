import React from 'react'
import FixedWidthRegButton from '../buttons/FixedWidthRegButton'
import { Button } from 'react-bootstrap';

const RegistrationRequestButton = ({request, text, onClickFunction}) => {
    let style = "formButton"
    if (text.includes("Decline")) {
      style = "declineButtonAdmin"
    }
    return <Button variant="custom" className={`commentBtn ${style} mt-3`} onClick={onClickFunction}>
      {text}
    </Button>
}

export default RegistrationRequestButton