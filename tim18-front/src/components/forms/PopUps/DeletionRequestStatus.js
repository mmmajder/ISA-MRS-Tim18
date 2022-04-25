import {useState, useEffect} from 'react';
import Toast from 'react-bootstrap/Toast'
import ToastContainer from 'react-bootstrap/ToastContainer'
import { faCircleExclamation, faCircleCheck, faerror, faUserXmark, faSadTear, faXmark, faCircleXmark} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { Form, Row, Col, Button, Container, FormLabel} from 'react-bootstrap';
import '../../../assets/styles/popup.css';
import { isEmpty, deletionReason } from '../../../services/utils/InputValidation';
import LabeledTextarea from '../LabeledTextarea';

export default function FeedbackPopUp({changeToShow, isError, message, resetData}) {
    const [show, setShow] = useState(false);
    
    const colors = isError ? 'red' : 'green';
    const icon = isError ? faCircleExclamation : faCircleCheck ;
    const title = "!";

    useEffect(() => {
        setShow(changeToShow);
    }, [changeToShow])

    function reset(){
        setShow(false); 
        resetData();
    }
    return (
        <Col sm={5}>
            <div aria-live="polite" aria-atomic="true" className="bg-dark position-sticky">
                <ToastContainer position="top-center"  style={{color: 'orange', background: 'yellow'}}>
                    <Toast onClose={() => this.hide()}>
                        <Toast.Header closeButton={false}>
                            <strong className="me-auto">
                                <FontAwesomeIcon icon={faCircleExclamation} style={{color: 'orange', align:'center'}}/>
                                <span> NOTIFICATION </span>
                            </strong>
                        </Toast.Header>
                        <Toast.Body>
                        Your deletion request is being processed!                                
                        </Toast.Body>
                    </Toast>
                </ToastContainer>
            </div>
        </Col>    
    );
}

