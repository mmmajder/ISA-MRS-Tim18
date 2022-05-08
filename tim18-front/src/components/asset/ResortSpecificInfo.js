import React from 'react';
import '../../assets/styles/asset.css';
import { Row, Col } from 'react-bootstrap';

export default function ResortSpecificInfo({resort}){
    let numberOfRooms =5;
    let numberOfBedsPerRoom = 3;

    return <>
            <Row className="mt-2">
                <Col sm='6'>
                    <p>Number of rooms: {resort.numberOfRooms}</p>
                </Col>  
                <Col sm='6'>
                    <p>Number of beds per room: {resort.numberOfBeds}</p>
                </Col>    
            </Row>
        </>
}

