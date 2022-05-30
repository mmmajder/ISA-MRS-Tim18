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

export default function Reviews(){

    const [user, setUser] = useState();
    const {reservationId} = useParams();
    const [reservation, setReservation] = useState();
    const [aboutAsset, setAboutAsset] = useState();
    const [aboutRenter, setAboutRenter] = useState();
    const [aboutClient, setAboutClient] = useState();

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

    const manageAboutAsset = useCallback(
        () => {
            if (!!reservation && !!user) {
                let comment;
                if (!!reservation.assetReviewId){
                    comment = <>
                    <MarkStars mark={4}/>
                    Very cool! I would come again no worries.
                    </>
                }else{
                    console.log(reservation)
                    console.log(reservation.clientId)
                    if (reservation.clientId == user.id){
    
                    } else{
                        comment = "User still hasn't left a review.";
                    }
                }
                setAboutAsset(comment);
            }
        },[reservation, user]
    )

    useEffect(() => {
        manageAboutAsset()
    }, [manageAboutAsset]
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
                    <Col sm='8'>
                        {aboutAsset}
                    </Col>
                    <Col sm='4'>
                    </Col>
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
            </div>
            <div className="borderedBlock mt-3">  
                <Row>
                    <Col sm='2'>
                        About client:
                    </Col>
                    <Col sm='10'>
                    </Col>
                </Row>
            </div>
        </>
    );
}
