import {  Row, Col, Button} from 'react-bootstrap';
import '../../assets/styles/form.css';
import '../../assets/styles/asset.css';
import AssetScrollerPhotos from '../asset/AssetScrollerPhotos';
import {useParams} from 'react-router-dom';
import { getAssetById } from '../../services/api/AssetApi';
import {useState, useEffect, useCallback} from 'react';
import { getReservation } from '../../services/api/ReservationApi';
import { getLogged } from '../../services/api/LoginApi'
import MarkStars from '../MarkStars';
import ReviewContent from './ReviewContent';
import ReviewInput from './ReviewInput';
import {getRenterByAssetId} from '../../services/api/RenterApi'

export default function Reviews(){

    const [user, setUser] = useState();
    const {reservationId} = useParams();
    const [reservation, setReservation] = useState();
    const [aboutAsset, setAboutAsset] = useState();
    const [aboutRenter, setAboutRenter] = useState();
    const [aboutClient, setAboutClient] = useState();
    const [renterId, setRenterId] = useState();

    useEffect(() => {
        async function fetchUser(){
            await getLogged(setUser);
        }
        fetchUser();
    }, [])

    useEffect(() => {
        getReservation(reservationId).then((response) =>{
            let res = response.data;
            setReservation(res);
        });
    }, [reservationId, setReservation]
    )

    useEffect(() => {
        if (!!reservation){
            console.log(reservation);
            getRenterByAssetId(reservation.asset).then((response) =>{
                let data = response.data;
                setRenterId(data);
            });
        }
    }, [reservation]
    )

    const manageAboutAsset = useCallback(
        () => {
            if (!!reservation && !!user) {
                let comment;
                if (!!reservation.assetReviewId){
                    comment = <>
                    <ReviewContent reviewId={reservation.assetReviewId}/>
                    </>
                }else{
                    if (reservation.clientId == user.id){
                        comment = <ReviewInput reservation={reservation} reviewFor='asset' renterId={renterId}/>
                    } else{
                        comment = "User still hasn't left a review.";
                    }
                }
                setAboutAsset(comment);
            }
        },[reservation, user, renterId]
    )

    const manageAboutRenter = useCallback(
        () => {
            if (!!reservation && !!user) {
                let comment;
                if (!!reservation.renterReviewId){
                    comment = <>
                    <ReviewContent reviewId={reservation.renterReviewId}/>
                    </>
                }else{
                    if (reservation.clientId == user.id){
                        comment = <ReviewInput reservation={reservation} reviewFor='renter' renterId={renterId}/>
                    } else{
                        comment = "User still hasn't left a review.";
                    }
                }
                setAboutRenter(comment);
            }
        },[reservation, user, renterId]
    )

    const manageAboutClient = useCallback(
        () => {
            if (!!reservation && !!user && !!renterId) {
                let comment;
                if (!!reservation.clientReviewId){
                    comment = <>
                    <ReviewContent reviewId={reservation.clientReviewId}/>
                    </>
                }else{
                    if (renterId == user.id){
                        comment = <ReviewInput reservation={reservation} reviewFor='client' renterId={renterId}/>
                    } else{
                        comment = "User still hasn't left a review.";
                    }
                }
                setAboutClient(comment);
            }
        },[reservation, user, renterId]
    )

    useEffect(() => {
        manageAboutAsset();
        manageAboutRenter();
        manageAboutClient();
    }, [manageAboutAsset, manageAboutRenter, manageAboutClient]
    )

    return (<>
            <div className="borderedBlock mt-3">  
                <Row>
                    <Col sm='2'>
                        About asset:
                    </Col>
                    <Col sm='10'>
                    </Col>
                </Row>
                <Row>
                    {aboutAsset}
                </Row>
            </div>
            <div className="borderedBlock mt-3">  
                <Row>
                    <Col sm='2'>
                        About renter:
                    </Col>
                    <Col sm='10'>
                    </Col>
                </Row>
                <Row>
                    {aboutRenter}
                </Row>
            </div>
            <div className="borderedBlock mt-3">  
                <Row>
                    <Col sm='2'>
                        About client:
                    </Col>
                    <Col sm='10'>
                    </Col>
                </Row>
                <Row>
                    {aboutClient}
                </Row>
            </div>
        </>
    );
}
