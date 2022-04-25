import { Row, Col } from 'react-bootstrap';
import {useState, useEffect} from 'react';
import Toast from 'react-bootstrap/Toast'
import ToastContainer from 'react-bootstrap/ToastContainer'
import { faCircleExclamation, faCircleCheck} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

export default function FeedbackPopUp({changeToShow, isError, message, resetData}) {
    const [show, setShow] = useState(false);
    
    const colors = isError ? 'red' : 'green';
    const icon = isError ? faCircleExclamation : faCircleCheck ;
    const title = isError ? "ERROR!" : "SUCCESS!";

    useEffect(() => {
        setShow(changeToShow);
    }, [changeToShow])

    function reset(){
        setShow(false); 
        resetData();
    }
    return (
        <Row className='mt-3'>
            <Col sm={12}>
                <div aria-live="polite" aria-atomic="true" className="bg-dark position-relative">
                    <ToastContainer position="top-end"  style={{color: colors, background: colors}}>
                        <Toast onClose={() => reset()} show={show} delay={4500} autohide>
                        <Toast.Header>
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
