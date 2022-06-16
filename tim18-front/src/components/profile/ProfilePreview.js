import React from 'react'
import { Row, Col, Button  } from 'react-bootstrap';
import ProfileInfoBlock from './ProfileInfoBlock';
import {useState, useEffect, useCallback} from 'react';
import axios from 'axios';
import {getReviews, getRating} from '../../services/api/ReviewApi'
import AssetList from '../asset/AssetsList';
import { getRole } from '../../services/AuthService/AuthService';
import ClientProfilePreview from './ClientProfilePreview';
import { getLogged } from '../../services/api/LoginApi';
import '../../assets/styles/style.css';
import ListedReview from '../reservations/ListedReview';
import {getRenter} from '../../services/api/RenterApi'
import {useParams} from 'react-router-dom';
import FixedWidthRegButton from '../buttons/FixedWidthRegButton';
import { deleteUser } from '../../services/api/DeleteRequestApi';

export default function ProfilePreview({user}){
    // const [user, setUser] = useState();

    // console.log("userId iz profile " + userId)
    // useEffect(() => {
    //     async function fetchUser(){
    //         const requestData = await getRenter(userId);
    //         setUser(!!requestData ? requestData.data : {});
    //         return requestData;
    //     }
    //     fetchUser();
    // }, [userId])

    //TODO ODRADITI PRIKAZ TUDJEG PROFILA
    const [reviews, setReviews] = useState();
    const [listedReviews, setListedReviews] = useState();

    const [reviewNum, setReviewNum]  = useState(0);       
    const [mark, setMark]  = useState(0);        
    
    const [logged, setLogged] = useState(null)

    const adminDeleteUser = () => {
        deleteUser(user.id)
    }

    useEffect(() => {
        async function fetchUser(){
            await getLogged(setLogged);
        }
        fetchUser();
      }, []);

    useEffect(() => {
        if (!!user){
            getReviews(user.id, true).then((response) => {
                let revs = response.data;
                setReviews(revs);
                setReviewNum(revs.length)
            })
            getRating(user.id).then((response) => {
                let mar = response.data;
                setMark(mar);
            })
        }
    }, [user])

    useEffect(() => {
        if (!!reviews){
            let listedRevs = <p className='profileNameLastname'>User still hasn't got any reviews.</p>
            if (reviews.length != 0)
                listedRevs = reviews.map((r) => <ListedReview reviewId={r.id}/>);
            if (user.userType!='Admin')
                setListedReviews(listedRevs);
        }
    }, [reviews])


    console.log(user)
    const userType =  user.userType;
    const infoBlock =  userType === 'Client' ? <ClientProfilePreview user={user} reviewNum={reviewNum} mark={mark}/> 
                                             : <ProfileInfoBlock user={user} reviewNum={reviewNum} mark={mark}/>
    if(!!user && logged) {
        return <Row className="pt-5">
                    <Col sm='3'>
                        {infoBlock}
                    </Col>
                    <Col sm='9'>
                        <Row>
                        { logged.userType==="Admin" && userType!="Admin" && 
                        <Button href={"#"} variant="custom" className='alertFixedWidthButton onRight formButton pe-5 ps-5 ' onClick={adminDeleteUser}>
                                Delete user
                            </Button>}
                        </Row>
                        <Row>
                        {listedReviews}
                        </Row>
                    </Col>
                </Row>
    }
    else 
        return null;
}