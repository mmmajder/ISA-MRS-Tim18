import React from 'react'
import { Row, Col  } from 'react-bootstrap';

export default function ProfilePreview({profileComponent}){
    return <Row className="pt-5">
                <Col sm='1'/>
                <Col sm='3'>
                {profileComponent}
                </Col>
                <Col sm='8'/>
            </Row>
    
}