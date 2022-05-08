import React from 'react';
import { Row, Col } from 'react-bootstrap';

export default function AssetOtherInfo({maxNumOfPeope, cancelationFee, description, rules}){

    return <>
        <Row className="mt-2"><div className="borderedBlockNoShadow" align="">Description:    {description}</div></Row>
        <Row className="mt-2"><div className="borderedBlockNoShadow" align="">Rules:    {rules}</div></Row>
        <Row className="mt-2">
            <Col sm='6'>
                <p>Maximum number of people: {maxNumOfPeope}</p>
            </Col>  
            <Col sm='6'>
                <p>Cancelation fee: {cancelationFee}%</p>
            </Col>    
        </Row>
    </>
}

