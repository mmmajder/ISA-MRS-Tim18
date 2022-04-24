import React from 'react';
import '../../assets/styles/profile.css';
import MarkStars from '../MarkStars';
import ProfileInfo from './ProfileInfo';
import ProfileBusinessInfo from './ProfileBusinessInfo';
import {useState, useEffect, useCallback} from 'react';
import axios from 'axios';
import {getInstructorByID} from '../../services/api/InstructorApi'

export default function ProfileInfoBlock({id}){
    const [user, setUser] = useState();

    useEffect(() => {
        async function fetchUser(){
            const requestData = await getInstructorByID(id);
            setUser(!!requestData ? requestData.data : {});
            return requestData;
        }
        fetchUser();
    }, [])

    const profilePic = require('../../assets/images/blue_profile_pic.jpg')
    console.log(id)
    console.log(user)
    if(!!user) {
        return <div className="borderedBlock" align="center">
                <img src={profilePic} className="profilePicture rounded-circle" ></img>
                <ProfileInfo infoClass="profileNameLastname" text={user.firstName + " " + user.lastName}/>
                <MarkStars mark={user.mark} />
                <ProfileInfo infoClass="profileOtherInfo" text={user.city + ", " + user.state }/>
                <ProfileInfo infoClass="profileOtherInfo" text={user.dateBirth}/>
                <hr className="solidDivider"/>
                <ProfileBusinessInfo assetsName={ getAssetName(user.userType)} assetsNum="5" rentsName="RENTS" rentsNum="7" reviewsNum="3"/>
            </div>
    }
    
}

const getAssetName = (userType) => {
    console.log(userType)
    switch (userType) {
        case "BOATOWNER":
            return "BOATS";
        case "FishingInstructor":
            return "TRIPS";
        case "RESORTOWNER":
            return "RESORTS";
        default:
            break;
    }
} 