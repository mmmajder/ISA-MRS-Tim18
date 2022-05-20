import React from 'react';
import '../../assets/styles/profile.css';
import MarkStars from '../MarkStars';
import ProfileInfo from './ProfileInfo';
import ProfileBusinessInfo from './ProfileBusinessInfo';

import {useState, useEffect, useCallback} from 'react';
import { getLogged } from '../../services/api/LoginApi';
import '../../assets/styles/style.css';

import {getPhotoFromServer} from '../../services/api/ImageApi';

export default function ProfileInfoBlock(){
    const [user, setUser] = useState();

    const [profilePhoto, setProfilePhoto] = useState();

    useEffect(() => {
        async function fetchUser(){
            await getLogged(setUser);
        }
        fetchUser();
    }, [])

    const getProfilePhoto = useCallback(
        (e) => {
            getPhotoFromServer(user.profilePhotoId).then((response) =>{
                let photo = `data:image/jpeg;base64,${response.data}`
                setProfilePhoto(photo);
            });
        }, [user]
    )

    useEffect(() => {
        if (!!user && !!user.profilePhotoId){
            console.log(user)
            console.log(user.id)
            console.log(user.profilePhotoId)
            getProfilePhoto()
        } else{
            setProfilePhoto(require('../../assets/images/blue_profile_pic.jpg'))
        }
    }, [user])

    console.log(user)

    if(!!user) {
        return <div className="borderedBlock" align="center">
                <img src={profilePhoto} className="profilePicture rounded-circle" ></img>
                <ProfileInfo infoClass="profileNameLastname" text={user.firstName + " " + user.lastName}/>
                <MarkStars mark={user.mark} />
                <ProfileInfo infoClass="profileOtherInfo" text={user.city + ", " + user.state }/>
                <ProfileInfo infoClass="profileOtherInfo" text={user.dateBirth}/>
                <hr className="solidDivider"/>
                <ProfileBusinessInfo assetsName={ getAssetName(user.userType)} assetsNum="5" rentsName="RENTS" rentsNum="7" reviewsNum="3"/>
            </div>
    }
    else 
        return null;
    
}

const getAssetName = (userType) => {
    console.log(userType)
    userType =  "RESORT_RENTER";
    switch (userType) {
        case "BOAT_RENTER":
            return "BOATS";
        case "FISHERMAN":
            return "TRIPS";
        case "RESORT_RENTER":
            return "RESORTS";
        default:
            break;
    }
} 