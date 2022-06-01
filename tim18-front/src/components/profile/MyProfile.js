import React from 'react'
import { Row, Col  } from 'react-bootstrap';
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
import ProfilePreview from './ProfilePreview';

export default function MyProfile(){
    
    const [user, setUser] = useState([]);

    useEffect(() => {
        async function fetchUser(){
            await getLogged(setUser);
        }
        fetchUser();
    }, [])

    if(!!user) {
        return <ProfilePreview user={user} />
    }
    else 
        return null;
}