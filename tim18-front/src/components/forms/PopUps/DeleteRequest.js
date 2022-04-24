import { Form, Row, Col, Button, Container, FormLabel} from 'react-bootstrap';
import { faUserXmark, faSadTear, faXmark, faCircleXmark } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import React, { useState } from 'react';
import '../../../assets/styles/popup.css';
import { LabeledInputWithErrMessage } from '../LabeledInput';
import { isEmpty, deletionReason } from '../../../services/utils/InputValidation';
import LabeledTextarea from '../LabeledTextarea';

const DeleteRequest = ({setPopUp, PropFunc}) => {
    // function that takes boolean as param to conditionally display popup
    
    const [isValid, setIsValid] = useState(false);
    const [value, setValue] = useState('');

    const validateInput = (value) => {
        setIsValid(isEmpty(value));
        setValue(value);
     };
    return (
        <div className="PopUp">
            
            <div className='close'>
                <Row>
                    <Col sm={11}/>
                    <Col sm={1}>
                        <Button variant="custom" onClick={()=> {setPopUp(false)}} ><FontAwesomeIcon icon={faCircleXmark} size="lg"/></Button>
                    </Col>
                </Row>
            </div>
            <Row className='mt-3'>
                <FontAwesomeIcon icon={faSadTear} size="2xl"/>
                <Col sm={3}/>
                <Col sm={9}>
                    <FormLabel style={{'fontSize': 'x-large'}}>
                        Are you sure you want to delete your profile?
                    </FormLabel>
                </Col>
            </Row>
            <Row className='mt-3'>
                <Col sm={1}/>
                <Col sm={9}>
                    <LabeledTextarea label="" inputName="description" placeholder={deletionReason} required onChangeFunc={validateInput}/>
                </Col>
            </Row>
            <Row className='mt-3'>  
                <Col sm={4}/>
                <Col sm={2} align='left'>
                    <Button variant="custom" disabled={!isValid} type="submit" className='requestButton' onClick={()=> {setPopUp(false); PropFunc(value)}} >
                        Delete
                    </Button>
                </Col>
                <Col sm={2} align='right'>
                    <Button variant="custom" type="reset" className='requestButton' onClick={()=> setPopUp(false)}>
                        Cancel
                    </Button>
                </Col>
                <Col sm={4}/>
            </Row>
            <Row className='mt-3'></Row>
        </div>

    );
}

export default DeleteRequest;