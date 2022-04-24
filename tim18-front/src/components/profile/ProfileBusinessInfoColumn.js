import React from 'react';
import { Row, Col  } from 'react-bootstrap';

export default function ProfileBusinessInfoColumn({labelName, number}){

    return <Col sm='4' align='center'>
                <Row><Col sm='12'>{labelName}</Col></Row>
                <Row><Col sm='12'>{number}</Col></Row>
            </Col>
}