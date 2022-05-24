import React from 'react';
import '../../assets/styles/asset.css';
import { Row, Col } from 'react-bootstrap';
import RenterInfo from './RenterInfo';
import RegularButton from '../buttons/RegularButton';
import AssetMainInfo from './AssetMainInfo';
import AssetOtherInfo from './AssetOtherInfo';
import { faPenToSquare, faTrash, faCalendarDays, faImage, faCoins} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { Link } from "react-router-dom";
import ResortSpecificInfo from './ResortSpecificInfo';
import BoatSpecificInfo from './BoatSpecificInfo';
import FishingSpecificInfo from './FishingSpecificInfo';
import { getAssetById, deleteAsset } from '../../services/api/AssetApi';
import {useEffect, useState, useCallback} from 'react';
import {useParams} from 'react-router-dom';
import { getRole } from '../../services/AuthService/AuthService';
import { Marginer } from '../forms/Login/marginer';
import CalendarAsset from '../forms/calendar/CalendarAsset';
import MapContainer from './MapContainer';
import {getAssetTodayPrice} from '../../services/api/AssetApi';
import AssetCarousel from './AssetCarousel';
import CreateReservationFormModal from '../modal/CreateReservationFormModal';

export default function AssetDetailedView(){
    const [asset, setAsset] = useState({});
    const {id} = useParams();
    const userType = getRole()
    localStorage.setItem("assetId", id);

    const [assetPrice, setAssetPrice] = useState(0);

    useEffect(() => {
        async function fetchAsset(){
            const requestData = await getAssetById(id);
            setAsset(!!requestData ? requestData.data : {});
            return requestData;
        }
        fetchAsset();
    }, [id])

    let assetType = asset.assetType;

    const linkToEditPage = "/resorts/update/" + id;
    const linkToCalendar = "/calendarAsset";
    const linkToUpdateAssetPhotos = "/updateAssetPhotos/" + id;
    const linkToUpdateAssetPrice = "/updateAssetPrice/" + id;
    const linkToMyAssetsPage = "/resorts"
    const assetDeletion = () => {
        deleteAsset(id)
    }

    const getAssetPrice = useCallback(
        () => {
            getAssetTodayPrice(asset.id).then((response) =>{
                let price = response.data.price;
                setAssetPrice(price);
            });
        }, [asset, setAssetPrice]
    )

    useEffect(
        () => {
            getAssetPrice();
        }, [asset]
    )
    const [show, setShow] = useState(false);
    const props = {scope: 'global', asset: asset, setShow: setShow, show: show};

    const makeReservation = () =>{
        setShow(true);
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
                            <Col sm="7">
                                <AssetMainInfo name={asset.name} mark={asset.averageRating} address={asset.address} price={assetPrice}/>
                            </Col> 
                            <Col sm="4">
                                { userType !== "Client" && userType !== "Guest" ? 
                                <>
                                <Link to={linkToUpdateAssetPrice}><FontAwesomeIcon icon={faCoins} className="faButtons" /></Link> 
                                <Link to={linkToUpdateAssetPhotos}><FontAwesomeIcon icon={faImage} className="faButtons" /></Link> 
                                <Link to={linkToEditPage}><FontAwesomeIcon icon={faPenToSquare} className='faButtons'/></Link> 
                                <Link to={linkToMyAssetsPage} onClick={assetDeletion}><FontAwesomeIcon icon={faTrash} className='faButtons'/></Link>
                                </> : null
                                }
                            </Col>
                        </Row>
                        <AssetOtherInfo description={asset.description} rules={asset.rules} maxNumOfPeope={asset.numOfPeople} cancelationFee={asset.cancelationConditions}/>
                        {assetType === "RESORT" && <ResortSpecificInfo resort={asset}/>}
                        {assetType === "BOAT" && <BoatSpecificInfo boat={asset}/>}
                        {assetType === "FISHING_ADVENTURE" && <FishingSpecificInfo fishingAdventure={asset}/>}
                    </Col>
                </Row>
                <Marginer direction="vertical" margin="3em" />
                <Row>
                    <CalendarAsset></CalendarAsset>
                </Row>
                <Row>
                    <Col sm={4}/>
                    <Col sm={4} align='center'>
                       {userType=="Client" ? <RegularButton text='Rent' disabled={userType === "Guest"} onClickFunction={makeReservation}/> : []}
                    </Col>
                    <Col sm={4}>
                    </Col>
                </Row>
                {show && <CreateReservationFormModal props={props}/>}
                
                <Row>
                    {/* Reviews will go under */}
                </Row>
            </div>
                
        </>
}

