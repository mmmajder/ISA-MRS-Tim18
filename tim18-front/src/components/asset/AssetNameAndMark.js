import React from 'react';
import MarkStars from '../MarkStars';
import { Row, Col } from 'react-bootstrap';

export default function AssetNameAndMark({name, mark}){

    return <>
        <Row className="importantInfo mt-4">{name}</Row>
        <Row className="mt-2"><MarkStars mark={mark}/></Row>
    </>
}

