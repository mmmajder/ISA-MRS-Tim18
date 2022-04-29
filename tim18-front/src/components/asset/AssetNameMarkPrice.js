import React from 'react';
import MarkStars from '../MarkStars';
import { Row, Col } from 'react-bootstrap';

export default function AssetNameMarkPrice({name, mark, price}){

    return <>
        <Row className="importantInfo mt-4">{name}</Row>
        <Row className="mt-2">
            <Col sm="6" >
                <MarkStars mark={mark}/>
            </Col>
            <Col sm="6" align="right">
                <span className="importantInfo">{price}€</span>
            </Col>
            
        </Row>
    </>
}

