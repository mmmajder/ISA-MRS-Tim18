import React from 'react';
import '../../assets/styles/asset.css';
import { Row, Col } from 'react-bootstrap';
import RenterInfo from './RenterInfo';
import RegularButton from '../buttons/RegularButton';
import AssetMainInfo from './AssetMainInfo';
import AssetOtherInfo from './AssetOtherInfo';
import { faPenToSquare, faTrash} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { Link } from "react-router-dom";
import { getAssetById } from '../../services/api/AssetApi';
import {useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';

export default function ResortDetailedView(){
    const resortImage = require('../../assets/images/Maldives.jpg');
    const [asset, setAsset] = useState({});
    const {id} = useParams();

    useEffect(() => {
        async function fetchAsset(){
            const requestData = await getAssetById(id);
            console.log(requestData.data);
            setAsset(!!requestData ? requestData.data : {});
            return requestData;
        }
        fetchAsset();
    }, [id])

    const linkToEditPage = "/resorts/update/" + id;

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
                                <AssetMainInfo name={asset.name} mark={asset.averageRating} address={asset.address}/>
                            </Col> 
                            <Col sm="2">
                                <Link to={linkToEditPage}><FontAwesomeIcon icon={faPenToSquare} className='faButtons'/></Link>
                                <FontAwesomeIcon icon={faTrash} className='faButtons'/>
                            </Col>
                        </Row>
                        <AssetOtherInfo description={asset.description} rules={asset.rules} maxNumOfPeope={asset.maxNumOfPeope} cancelationFee={asset.cancelationConditions}/>
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

