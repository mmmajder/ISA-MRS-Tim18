import React from 'react';
import '../../assets/styles/asset.css';
import { Row, Col } from 'react-bootstrap';
import FixedWidthRegButton from '../buttons/FixedWidthRegButton';
import AssetMainInfo from './AssetMainInfo';

export default function ListedAsset({asset}){
    const resortImage = require('../../assets/images/Maldives.jpg');
    
    const detViewUrl = "resorts/" + asset.id;

    return <div className="borderedBlock mt-3" align="">
                <Row>
                    <Col sm="3">
                        <img src={resortImage} className="listedAssetImage"/>
                    </Col>
                    <Col sm="6">
                        <Row>
                            <Col sm="6">
                                <AssetMainInfo name={asset.name} mark={asset.averageRating} address={asset.address} price={asset.price}/>
                            </Col>
                            <Col sm="4">
                                
                            </Col>
                            <Col sm="2" >
                                <div className='mt-4'>
                                    <FixedWidthRegButton href={detViewUrl} text='Preview' onClickFunction={''}/>
                                    <FixedWidthRegButton text='Delete' onClickFunction={''}/>
                                </div>
                            </Col>
                        </Row>
                    </Col>
                </Row>
            </div>
}

