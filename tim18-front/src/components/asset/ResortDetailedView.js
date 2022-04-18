import React from 'react';
import '../../assets/styles/asset.css';
import { Row, Col } from 'react-bootstrap';
import MarkStars from '../MarkStars';
import RenterInfo from './RenterInfo';
import { Button } from 'react-bootstrap';
import ResortInfo from './ResortInfo';
import RegularButton from '../buttons/RegularButton';

export default function ResortDetailedView(){
    const resortImage = require('../../assets/images/Maldives.jpg');

    return <>
            <div className="borderedBlock mt-3" align="">
                <Row>
                    <Col sm="6">
                        <img src={resortImage} className="assetImage"/>
                        <RenterInfo/>
                    </Col>
                    <Col sm="6">
                        <ResortInfo name={'Maldivian hut on water'} mark={4.7} address={'Orchid Magu 7, Maadhad, 57887, Maldives'}/>
                    </Col>
                </Row>
                <Row>
                    <Col sm={4}/>
                    <Col sm={4} align='center'>
                        <RegularButton text='Rent resort' onClickFunction={''}/>
                    </Col>
                    <Col sm={4}/>
                </Row>
                <Row>
                    {/* Reviews will go under */}
                </Row>
                
            </div>
        </>
}

