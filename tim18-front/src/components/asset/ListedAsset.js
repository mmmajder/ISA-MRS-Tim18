import React from 'react';
import '../../assets/styles/asset.css';
import { Row, Col } from 'react-bootstrap';
import FixedWidthRegButton from '../buttons/FixedWidthRegButton';
import AssetMainInfo from './AssetMainInfo';

export default function ListedAsset({name, address, mark}){
    const resortImage = require('../../assets/images/Maldives.jpg');
    

    return <div className="borderedBlock mt-3" align="">
                <Row>
                    <Col sm="3">
                        <img src={resortImage} className="listedAssetImage"/>
                    </Col>
                    <Col sm="6">
                        <Row>
                            <Col sm="6">
                                <AssetMainInfo name={name} mark={mark}/>
                            </Col>
                            <Col sm="4">
                                
                            </Col>
                            <Col sm="2" >
                                <div className='mt-4'>
                                    <FixedWidthRegButton href="/resorts/id" text='Preview' onClickFunction={''}/>
                                    <FixedWidthRegButton text='Delete' onClickFunction={''}/>
                                </div>
                            </Col>
                        </Row>
                    </Col>
                </Row>
            </div>
}

