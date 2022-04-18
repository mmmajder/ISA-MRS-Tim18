import { Row, Col } from 'react-bootstrap';
import {useState, useEffect} from 'react';
import Toast from 'react-bootstrap/Toast'
import ToastContainer from 'react-bootstrap/ToastContainer'
import { faCircleExclamation, faCircleCheck} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

export default function FeedbackPopUp({changeToShow, isError, message, resetShow}) {
    const [show, setShow] = useState(false);
    
    const colors = isError ? 'red' : 'green';
    const icon = isError ? faCircleExclamation : faCircleCheck ;
    const title = isError ? "ERROR!" : "SUCCESS!";

    useEffect(() => {
        setShow(changeToShow);
    }, [changeToShow])

    function reset(){
        setShow(false); 
        resetShow(false);
    }
    return (
        <Row className='mt-3'>
            <Col sm={12}>
                <div aria-live="polite" aria-atomic="true" className="bg-dark position-relative">
                    <ToastContainer position="top-end"  style={{color: colors}}>
                        <Toast onClose={() => reset()} show={show} delay={3500} autohide>
                        <Toast.Header className=''>
                            <strong className="me-auto"><FontAwesomeIcon icon={icon} style={{color: colors}}/>{title}</strong>
                        </Toast.Header>
                        <Toast.Body>{message}</Toast.Body>
                        </Toast>
                    </ToastContainer>
                </div>
            </Col>
        </Row>
        
        );
}

/**
 *  <div aria-live="polite" aria-atomic="true" className="bg-dark position-relative" style={{ minHeight: '240px' }} >
            <ToastContainer position="top-end" className="p-3">
                <Toast onClose={() => setShow(false)} show={show} delay={3000} autohide>
                <Toast.Header>
                    <img src="holder.js/20x20?text=%20" className="rounded me-2" alt="" />
                    <strong className="me-auto">Bootstrap</strong>
                    <small className="text-muted">just now</small>
                </Toast.Header>
                <Toast.Body>See? Just like this.</Toast.Body>
                </Toast>
            </ToastContainer>
        </div>
 */