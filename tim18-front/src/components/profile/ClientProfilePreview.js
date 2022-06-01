import React from 'react'
import '../../assets/styles/profile.css';
import MarkStars from '../MarkStars';
import PenaltyInfo from './PenaltyInfo';
import ProfileInfo from './ProfileInfo';
import ProfileBusinessInfo from './ProfileBusinessInfo';
import {useEffect, useState, useCallback} from 'react';
import { getClientByID } from '../../services/api/ClientApi';
import { getLogged } from '../../services/api/LoginApi';
import '../../assets/styles/style.css';
import {getPhotoFromServer} from '../../services/api/ImageApi';

export default function ClientProfilePreview({user, reviewNum, mark}){
    const [profilePhoto, setProfilePhoto] = useState();
    const [penaltyCount, setPenaltyCount]  = useState(2);   // TODO: real data
    
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
        }
    }, [user])

    const penalties = <PenaltyInfo penaltyCount={penaltyCount} />

    if(!!user){
        return <div className="borderedBlock" align="center">
                <img src={profilePhoto} className="profilePicture rounded-circle" ></img>
                <ProfileInfo infoClass="profileNameLastname" text={user.firstName + " " + user.lastName}/>
                <MarkStars mark={mark} />
                <ProfileInfo infoClass="profileOtherInfo" text={user.city + ", " + user.state }/>
                <ProfileInfo infoClass="profileOtherInfo" text={user.dateBirth}/>
                <hr className="solidDivider"/>
                <ProfileBusinessInfo assetsName="PENALTIES" assetsNum={penalties} rentsName="" rentsNum="" reviewsNum={reviewNum}/>
            </div>
    }
}
