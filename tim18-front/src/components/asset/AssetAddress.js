import React from 'react';
import { Row } from 'react-bootstrap';

export default function AssetAddress({address}){
    return <>
        <Row className="mt-2">{address}</Row>
    </>
}

