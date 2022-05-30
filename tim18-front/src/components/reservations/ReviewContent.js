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
import RenterInfo from '../asset/RenterInfo';
import { getReview } from '../../services/api/ReviewApi'
import { getClientByID } from '../../services/api/ClientApi'
import { getRenter } from '../../services/api/InstructorApi'
import ProfilePhotoAndFullName from '../asset/ProfilePhotoAndFullName';

export default function ReviewContent({reviewId}){

    const [ review, setReview] = useState();
    const [reviewer, setReviewer] = useState();
    const [text, setText] = useState();
    const [rating, setRating] = useState();

    useEffect(() => {
        getReview(reviewId).then((response) =>{
            let rev = response.data;
            setReview(rev);
        });
    }, []
    )

    useEffect(() => {
        
        if (!!review){
            let reviewerId;
            if (review.clientWriting)
                reviewerId = review.clientID;
            else 
                reviewerId = review.renterID;
            console.log(reviewerId);
            console.log(review);
            getRenter(reviewerId).then((response) =>{
                let rev = response.data;
                setReviewer(rev);
            })
            setText(review.text);
            setRating(review.rating);
        }
    }, [review]
    )

    return (
            <Row className='pt-3'>
                <Col sm='3'>
                    {/* here this is actually for showing reviewer's info */}
                    <ProfilePhotoAndFullName user={reviewer} />
                </Col>
                <Col sm='7'>
                    {text}
                </Col>
                <Col sm='2' align='right'>
                    <MarkStars mark={rating}/>
                </Col>
               
            </Row>
    );
}
