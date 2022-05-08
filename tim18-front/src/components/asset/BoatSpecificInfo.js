import React from 'react';
import '../../assets/styles/asset.css';
import { Row, Col } from 'react-bootstrap';

export default function BoatSpecificInfo(boat){
    let boatType = "Frigate";
    let length = 15;
    let maxSpeed = 50;
    let numberOfEngines = 6;
    let engineStrength = 50;
    let navigationGear = "GPS, Sonar, Radio";
    let fishingGear = "Harpoon";

    return <>
            <Row className="mt-2">
                <Col sm='4'>
                    <p>Type: {boatType}</p>
                </Col>  
                <Col sm='4'>
                    <p>Length: {length}</p>
                </Col>  
                <Col sm='4'>
                    <p>Max speed: {maxSpeed}</p>
                </Col>   
            </Row>
            <Row className="mt-2">
                <Col sm='6'>
                    <p>Number of engines: {numberOfEngines}</p>
                </Col>  
                <Col sm='6'>
                    <p>Engine strength: {engineStrength}</p>
                </Col>    
            </Row>
            <Row className="mt-2"><div className="borderedBlockNoShadow" align="">Navigation gear:    {navigationGear}</div></Row>
            <Row className="mt-2"><div className="borderedBlockNoShadow" align="">Fishing gear:    {fishingGear}</div></Row>
        </>
}

