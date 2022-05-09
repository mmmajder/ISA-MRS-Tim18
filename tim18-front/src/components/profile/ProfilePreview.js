import React from 'react'
import { Row, Col  } from 'react-bootstrap';
import ProfileInfoBlock from './ProfileInfoBlock';
import {useState, useEffect, useCallback} from 'react';
import axios from 'axios';
import {getRenter} from '../../services/api/InstructorApi'
import AssetList from '../asset/AssetsList';

export default function ProfilePreview({userId}){
    const [user, setUser] = useState();

    console.log("userId iz profile " + userId)
    useEffect(() => {
        async function fetchUser(){
            const requestData = await getRenter(userId);
            setUser(!!requestData ? requestData.data : {});
            return requestData;
        }
        fetchUser();
    }, [userId])

    console.log(user)

    if(!!user) {
        return <Row className="pt-5">
                    <Col sm='3'>
                    <ProfileInfoBlock user={user}/>
                    </Col>
                    <Col sm='9'>
                        <AssetList/>
                    </Col>
                </Row>
    }
    else 
        return null;
}