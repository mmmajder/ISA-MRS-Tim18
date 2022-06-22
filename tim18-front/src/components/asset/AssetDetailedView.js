import React from 'react';
import '../../assets/styles/asset.css';
import { Row, Col } from 'react-bootstrap';
import RenterInfo from './RenterInfo';
import AssetMainInfo from './AssetMainInfo';
import AssetOtherInfo from './AssetOtherInfo';
import { faPenToSquare, faTrash, faCalendarDays, faImage, faCoins} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { Link } from "react-router-dom";
import ResortSpecificInfo from './ResortSpecificInfo';
import BoatSpecificInfo from './BoatSpecificInfo';
import FishingSpecificInfo from './FishingSpecificInfo';
import { getAssetById, deleteAsset, deleteAssetAdmin } from '../../services/api/AssetApi';
import {useEffect, useState, useCallback} from 'react';
import {useParams} from 'react-router-dom';
import { getRole } from '../../services/AuthService/AuthService';
import { Marginer } from '../forms/Login/marginer';
import CalendarAsset from '../forms/calendar/CalendarAsset';
import MapContainer from './MapContainer';
import {getAssetTodayPrice} from '../../services/api/AssetApi';
import AssetCarousel from './AssetCarousel';
import RegularButton from '../buttons/RegularButton';
import { getLogged } from '../../services/api/LoginApi';
import { hasSubscription, subscribeToAsset, unsubscribeFromAsset } from '../../services/api/SubscriptionApi';
import {getAssetReviews, getAssetRating} from '../../services/api/ReviewApi'
import ListedReview from '../reservations/ListedReview';
import {doesRenterOwn} from '../../services/api/RenterApi'

export default function AssetDetailedView(){
    const [asset, setAsset] = useState();
    const [client, setClient] = useState();
    const [subscribeTxt, setSubscribeTxt] = useState("");
    const {id} = useParams();
    const userType = getRole()
    localStorage.setItem("assetId", id);

    const [assetPrice, setAssetPrice] = useState(0);

    const [reviews, setReviews] = useState();
    const [listedReviews, setListedReviews] = useState();
    const [mark, setMark] = useState(0);

    const [isMyAsset, setIsMyAsset] = useState(false);

    useEffect(() => {
        async function fetchUser(){
            await getLogged(setClient);
        }
        fetchUser();
      }, []);


      const hasSubscriptionCallback = (data) => {
            !!data ? setSubscribeTxt("Unsubscribe") : setSubscribeTxt("Subscribe");
      }

      useEffect(() => {
        async function hasSubscriptionUser(){
            await hasSubscription(hasSubscriptionCallback, asset.id, client.id);
        }

        if(client!==undefined && asset!==undefined){
            hasSubscriptionUser();
        }
      }, [asset, client]);

    useEffect(() => {
        getAssetById(id).then(
            (response) => {
                setAsset(response.data);
            }
        )
    }, [id])

    useEffect(() => {
        if (!!id && !!client && (userType==="BoatRenter" ||userType === 'FishingInstructor' || userType === 'ResortRenter')){
            doesRenterOwn(client.id, id).then(
                (response) => {
                    console.log("renter owns:")
                    console.log(response.data);
                    setIsMyAsset(response.data);
                }
            )
        }
    }, [id, client, userType])

    let assetType = asset?.assetType;

    const linkToEditPage = "/resorts/update/" + id;
    const linkToUpdateAssetPhotos = "/updateAssetPhotos/" + id;
    const linkToUpdateAssetPrice = "/updateAssetPrice/" + id;
    const linkToMyAssetsPage = "/resorts"
    const assetDeletion = () => {
        if (userType==="Admin") {
            deleteAssetAdmin(id)
        } else {
            deleteAsset(id)
        }
    }

    const getAssetPrice = useCallback(
        () => {
            getAssetTodayPrice(asset?.id).then((response) =>{
                let price = response.data.price;
                setAssetPrice(price);
            });
        }, [asset, setAssetPrice]
    )

    useEffect(
        () => {
            if (!!asset){
                getAssetPrice();
                getAssetReviews(asset.id, true).then((response) => {
                    console.log(response)
                    let revs = response.data;
                    setReviews(revs);
                });
                getAssetRating(asset.id).then((response) => {
                    let mar = response.data;
                    setMark(mar);
                })
            }
        }, [asset]
    )
    const subscribe = () => {
        console.log("subscribeFunc")
        if(subscribeTxt === 'Subscribe'){
            console.log(asset.id, client.id, "Subscribe")
            subscribeToAsset(asset.id, client.id);
            setSubscribeTxt("Unsubscribe")
        }
        else if(subscribeTxt === 'Unsubscribe'){
            console.log(asset.id, client.id, "Unsubscribe")
            unsubscribeFromAsset(asset.id, client.id);
            setSubscribeTxt("Subscribe")
        }
        
    }

    useEffect(() => {
        if (!!reviews){
            let listedRevs = reviews.map((r) => <ListedReview reviewId={r.id}/>);
            setListedReviews(listedRevs);
        }
    }, [reviews])

    if(!!asset){
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
                                <AssetMainInfo name={asset.name} mark={mark} address={asset.address} price={assetPrice}/>
                            </Col> 
                            <Col sm="4">
                                { userType !== "Client" && userType !== "Guest"  ? 
                                <>
                                {(userType !== "Admin" && isMyAsset) && <Link to={linkToUpdateAssetPrice}><FontAwesomeIcon icon={faCoins} className="faButtons" /></Link> }
                                {(userType !== "Admin" && isMyAsset) && <Link to={linkToUpdateAssetPhotos}><FontAwesomeIcon icon={faImage} className="faButtons" /></Link>} 
                                {(userType !== "Admin" && isMyAsset) && <Link to={linkToEditPage}><FontAwesomeIcon icon={faPenToSquare} className='faButtons'/></Link> }
                                {(userType === "Admin" || isMyAsset) && <Link to={linkToMyAssetsPage} onClick={assetDeletion}><FontAwesomeIcon icon={faTrash} className='faButtons'/></Link>}
                                </> : 
                                <RegularButton text={subscribeTxt} disabled={userType === "Guest"} onClickFunction={subscribe}/>
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
                    <CalendarAsset assetId={id}></CalendarAsset>
                </Row>
                <Row>
                    {listedReviews}
                </Row>
            </div>
                
        </>
        }
}

