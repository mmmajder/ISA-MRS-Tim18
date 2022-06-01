import React from 'react';
import '../../assets/styles/profile.css';
import MarkStars from '../MarkStars';
import ProfileInfo from './ProfileInfo';
import ProfileBusinessInfo from './ProfileBusinessInfo';
import { getPastRenterReservations } from '../../services/api/ReservationApi';
import {useState, useEffect, useCallback} from 'react';
import { getLogged } from '../../services/api/LoginApi';
import '../../assets/styles/style.css';

import {getPhotoFromServer} from '../../services/api/ImageApi';
import {getAssetsByUserId} from '../../services/api/AssetApi';

export default function ProfileInfoBlock({user, reviewNum, mark}){
    const [profilePhoto, setProfilePhoto] = useState();
    const [numOfAssets, setNumOfAssets] = useState(0);
    const [reservations, setReservations] = useState();
    const [reservationsNum, setReservationsNum] = useState(0);

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
            getProfilePhoto()
            getAssetsByUserId(user.id).then((response) => {
                let assets = response.data;
                setNumOfAssets(assets.length);
            });
            async function fetchReservations(){
                await getPastRenterReservations(setReservations, user.id).then(() => {
                    
                });
            }
            if(user !== undefined){
              fetchReservations();
            }
        }
    }, [user])

    useEffect(() => {
        if (!!reservations){
            setReservationsNum(reservations.length);
        }
    }, [reservations])

    console.log(user)

    if(!!user) {
        return <div className="borderedBlock" align="center">
                <img src={profilePhoto} className="profilePicture rounded-circle" ></img>
                <ProfileInfo infoClass="profileNameLastname" text={user.firstName + " " + user.lastName}/>
                {user.userType!="Admin" ? <MarkStars mark={mark} /> : [] }
                <ProfileInfo infoClass="profileOtherInfo" text={user.city + ", " + user.state }/>
                <ProfileInfo infoClass="profileOtherInfo" text={user.dateBirth}/>
                <hr className="solidDivider"/>
                {user.userType!="Admin" ? <ProfileBusinessInfo assetsName={ getAssetName(user.userType)} assetsNum={numOfAssets} rentsName="RENTS" rentsNum={reservationsNum} reviewsNum={reviewNum}/> : []}
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