import React from 'react';
import { Row, Col  } from 'react-bootstrap';

export default function ProfileInfo({infoClass, text}){

    return <Row className="mt-2 ">
                <Col sm='2'></Col>
                <Col sm='8' align='center'>
                    <p className={infoClass}>{text}</p>
                </Col>
                <Col sm='2'></Col>
            </Row>
}