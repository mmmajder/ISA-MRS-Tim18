import React from 'react'
import { Row, Col  } from 'react-bootstrap';
import ProfileInfoBlock from './ProfileInfoBlock';
import {useState, useEffect, useCallback} from 'react';
import axios from 'axios';
import {getRenter} from '../../services/api/InstructorApi'
import AssetList from '../asset/AssetsList';
import { getRole } from '../../services/AuthService/AuthService';
import ClientProfilePreview from './ClientProfilePreview';
import { getLogged } from '../../services/api/LoginApi';
import '../../assets/styles/style.css';

export default function ProfilePreview(){
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

    const [user, setUser] = useState([]);
    useEffect(() => {
        async function fetchUser(){
            await getLogged(setUser);
        }
        fetchUser();
    }, [])


    console.log(user)
    const userType =  getRole();
    const infoBlock =  userType === 'Client' ? <ClientProfilePreview /> : <ProfileInfoBlock user={user}/>
    const assetList =  userType === 'Client' ? <></> : <AssetList/>
    if(!!user) {
        return <Row className="pt-5">
                    <Col sm='3'>
                        {infoBlock}
                    </Col>
                    <Col sm='9'>
                        {assetList}
                    </Col>
                </Row>
    }
    else 
        return null;
}