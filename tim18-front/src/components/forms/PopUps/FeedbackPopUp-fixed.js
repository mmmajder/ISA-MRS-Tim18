import { Row, Col } from 'react-bootstrap';
import {useState, useEffect} from 'react';
import Toast from 'react-bootstrap/Toast'
import ToastContainer from 'react-bootstrap/ToastContainer'
import { faCircleExclamation, faCircleCheck} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import styled from "styled-components";
const ImageConatiner = styled.div`
    position: absolute;
    width: 150%;
    height: 200px;
    display: flex;
    flex-basis:50%;
    padding: 3em 3em;  

    aria-live: polite;
    aria-atomic: true;

`;

export default function FeedbackPopUpFixed({changeToShow, isError, message, resetData}) {
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
                <ImageConatiner >
                    <ToastContainer position="top-end"  style={{color: colors, background: colors}}>
                        <Toast onClose={() => reset()} show={show} delay={4500} autohide>
                        <Toast.Header>
                            <strong className="me-auto"><FontAwesomeIcon icon={icon} style={{color: colors}}/>{title}</strong>
                        </Toast.Header>
                        <Toast.Body>{message}</Toast.Body>
                        </Toast>
                    </ToastContainer>
                </ImageConatiner>
            </Col>
        </Row>
        
        );
}
