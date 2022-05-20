import React from 'react';
import '../../assets/styles/asset.css';
import { Row, Col } from 'react-bootstrap';
import RenterInfo from './RenterInfo';
import RegularButton from '../buttons/RegularButton';
import AssetMainInfo from './AssetMainInfo';
import AssetOtherInfo from './AssetOtherInfo';
import { faPenToSquare, faTrash, faCalendarDays, faImage} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { Link } from "react-router-dom";
import ResortSpecificInfo from './ResortSpecificInfo';
import BoatSpecificInfo from './BoatSpecificInfo';
import FishingSpecificInfo from './FishingSpecificInfo';
import { getAssetById, deleteAsset } from '../../services/api/AssetApi';
import {useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import { getRole } from '../../services/AuthService/AuthService';
import MapContainer from './MapContainer';

import AssetCarousel from './AssetCarousel';

export default function AssetDetailedView(){
    const [asset, setAsset] = useState({});
    const {id} = useParams();
    const userType = getRole()
    localStorage.setItem("assetId", id);

    useEffect(() => {
        async function fetchAsset(){
            const requestData = await getAssetById(id);
            console.log(requestData.data);
            setAsset(!!requestData ? requestData.data : {});
            return requestData;
        }
        fetchAsset();
    }, [id])

    let assetType = asset.assetType;

    const linkToEditPage = "/resorts/update/" + id;
    const linkToCalendar = "/calendarAsset";
    const linkToUpdateAssetPhotos = "/updateAssetPhotos/" + id;
    const linkToMyAssetsPage = "/resorts"
    const assetDeletion = () => {
        deleteAsset(id)
    }

    return <>
            <div className="borderedBlock mt-3" align="">
                <Row>
                    <Col sm="6">
                        <Row>
                            <AssetCarousel asset={asset} />
                        </Row>
                        <Row>
                            <RenterInfo renter={asset.renter}/>
                        </Row>
                        <Row>
                        <div className="borderedBlock mt-3" align="">
                            <MapContainer address={asset.address}/>
                        </div>
                        </Row>
                    </Col>
                    <Col sm="6">
                        <Row>
                            <Col sm="8">
                                <AssetMainInfo name={asset.name} mark={asset.averageRating} address={asset.address} price={asset.price}/>
                            </Col> 
                            <Col sm="4"> 
                                <Link to={linkToUpdateAssetPhotos}><FontAwesomeIcon icon={faImage} className="faButtons" /></Link>
                                <Link to={linkToCalendar}><FontAwesomeIcon icon={faCalendarDays} className="faButtons" /></Link>
                                <Link to={linkToEditPage}><FontAwesomeIcon icon={faPenToSquare} className='faButtons'/></Link>
                                { userType !== "Client" && <Link to={linkToMyAssetsPage} onClick={assetDeletion}><FontAwesomeIcon icon={faTrash} className='faButtons'/></Link>}
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

