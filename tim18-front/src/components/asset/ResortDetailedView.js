import React from 'react';
import '../../assets/styles/asset.css';
import { Row, Col } from 'react-bootstrap';
import RenterInfo from './RenterInfo';
import ResortInfo from './ResortInfo';
import RegularButton from '../buttons/RegularButton';
import AssetMainInfo from './AssetMainInfo';
import AssetOtherInfo from './AssetOtherInfo';
import { faPenToSquare, faTrash} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { Link } from "react-router-dom";

export default function ResortDetailedView(){
    const resortImage = require('../../assets/images/Maldives.jpg');
    const assetName='Maldivian hut on water';
    const mark=4.7;
    const address='Orchid Magu 7, Maadhad, 57887, Maldives';

    return <>
            <div className="borderedBlock mt-3" align="">
                <Row>
                    <Col sm="6">
                        <img src={resortImage} className="assetImage"/>
                        <RenterInfo/>
                    </Col>
                    <Col sm="6">
                        <Row>
                            <Col sm="10">
                                <AssetMainInfo name={assetName} mark={mark} address={address}/>
                            </Col> 
                            <Col sm="2">
                                <Link to="/resorts/update/id"><FontAwesomeIcon icon={faPenToSquare} className='faButtons'/></Link>
                                <FontAwesomeIcon icon={faTrash} className='faButtons'/>
                            </Col>
                        </Row>
                        <AssetOtherInfo maxNumOfPeope={4} cancelationFee={40}/>
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

