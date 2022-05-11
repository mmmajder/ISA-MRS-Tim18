import React from 'react';
import '../../assets/styles/asset.css';
import { Row, Col } from 'react-bootstrap';
import FixedWidthRegButton from '../buttons/FixedWidthRegButton';
import AssetMainInfo from './AssetMainInfo';
import { getRole }  from '../../services/AuthService/AuthService'

export default function ListedAsset({asset}){
    // let assetType = "RESORT";
    let assetType = asset.assetType;
    const userType = getRole();
    // let assetType = "FISHING";

    let assetImage; 
    if (assetType === "FISHING_ADVENTURE") {
        assetImage = require('../../assets/images/FishingAdventure3.png')
    } else if (assetType === "RESORT") {
        assetImage = require('../../assets/images/Maldives.jpg')
    } else {
        assetImage = require('../../assets/images/boat.jpg')
    }
    
    const detViewUrl = "resorts/" + asset.id;

    return <div className="borderedBlock mt-3" align="">
                <Row>
                    <Col sm="3">
                        <img src={assetImage} className="listedAssetImage"/>
                    </Col>
                    <Col sm="6">
                        <Row>
                            <Col sm="7">
                                <AssetMainInfo name={asset.name} mark={asset.averageRating} address={asset.address} price={asset.price}/>
                            </Col>
                            <Col sm="3">
                                
                            </Col>
                            <Col sm="2" >
                                <div className='mt-4'>
                                    <FixedWidthRegButton href={detViewUrl} text='Preview' onClickFunction={''}/>
                                    { userType !== "Client" && <FixedWidthRegButton text='Delete' onClickFunction={''}/>}
                                </div>
                            </Col>
                        </Row>
                    </Col>
                </Row>
            </div>
}

