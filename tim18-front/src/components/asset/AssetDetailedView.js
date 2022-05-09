import React from 'react';
import '../../assets/styles/asset.css';
import { Row, Col } from 'react-bootstrap';
import RenterInfo from './RenterInfo';
import RegularButton from '../buttons/RegularButton';
import AssetMainInfo from './AssetMainInfo';
import AssetOtherInfo from './AssetOtherInfo';
import { faPenToSquare, faTrash, faCalendarDays} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { Link } from "react-router-dom";
import ResortSpecificInfo from './ResortSpecificInfo';
import BoatSpecificInfo from './BoatSpecificInfo';
import FishingSpecificInfo from './FishingSpecificInfo';
import { getAssetById, deleteAsset } from '../../services/api/AssetApi';
import {useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';

export default function AssetDetailedView(){
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

    console.log(asset);

    // let assetType = "RESORT";
    let assetType = asset.assetType;
    // let assetType = "FISHING";

    let assetImage; 
    if (assetType === "FISHING_ADVENTURE") {
        assetImage = require('../../assets/images/FishingAdventure3.png')
    } else if (assetType === "RESORT") {
        assetImage = require('../../assets/images/Maldives.jpg')
    } else {
        assetImage = require('../../assets/images/boat.jpg')
    }

    const linkToEditPage = "/resorts/update/" + id;
    const linkToCalendar = "/calendarAsset";
    const linkToMyAssetsPage = "/resorts"
    const assetDeletion = () => {
        deleteAsset(id)
    }

    return <>
            <div className="borderedBlock mt-3" align="">
                <Row>
                    <Col sm="6">
                        <img src={assetImage} className="assetImage"/>
                        {asset !== {} && <RenterInfo/>}
                    </Col>
                    <Col sm="6">
                        <Row>
                            <Col sm="9">
                                <AssetMainInfo name={asset.name} mark={asset.averageRating} address={asset.address} price={asset.price}/>
                            </Col> 
                            <Col sm="3">
                                <Link to={linkToCalendar}><FontAwesomeIcon icon={faCalendarDays} className="faButtons" /></Link>
                                <Link to={linkToEditPage}><FontAwesomeIcon icon={faPenToSquare} className='faButtons'/></Link>
                                { localStorage.getItem('userType') !== "CLIENT" && <FontAwesomeIcon icon={faTrash} className='faButtons'/>}
                            </Col>
                        </Row>
                        <AssetOtherInfo description={asset.description} rules={asset.rules} maxNumOfPeope={asset.numOfPeople} cancelationFee={asset.cancelationConditions}/>
                        {assetType === "RESORT" && <ResortSpecificInfo resort={asset}/>}
                        {assetType === "BOAT" && <BoatSpecificInfo boat={asset}/>}
                        {assetType === "FISHING_ADVENTURE" && <FishingSpecificInfo fishingAdventure={asset}/>}
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

