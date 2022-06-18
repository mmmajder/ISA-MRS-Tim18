import React from 'react'
import '../../assets/styles/profile.css';
import MarkStars from '../MarkStars';
import PenaltyInfo from './PenaltyInfo';
import ProfileInfo from './ProfileInfo';
import ProfileBusinessInfo from './ProfileBusinessInfo';
import {useEffect, useState, useCallback} from 'react';
import '../../assets/styles/style.css';
import {getPhotoFromServer} from '../../services/api/ImageApi';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMedal } from '@fortawesome/free-solid-svg-icons'
import { getCategory } from '../../services/api/LoyaltyProgramApi';
import { ConfirmModal } from '../modal/ConfirmModal';

export default function ClientProfilePreview({user, reviewNum, mark}){
    const [profilePhoto, setProfilePhoto] = useState();
    const [loyaltyInfo, setLoyaltyInfo] = useState();
    const [loyaltyCategory, setLoyaltyCategory] = useState();
    const [pointsToUpgarde, setPointsToUpgarde] = useState();
    const [show, setShow] = useState();
    const title = "Info";
    const message = <div>{pointsToUpgarde === 0 ? "You are at the top of our loyalty members, no where to go up from here" : `You need ` + pointsToUpgarde + ` more points to get upgraded. Keep going!`}</div>;
    
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
            getCategory(user.loyaltyPoints, 'Client', setLoyaltyInfo)
        }
    }, [user])

    useEffect(() => {
        if(!!loyaltyInfo){
            setLoyaltyCategory(loyaltyInfo.category)
            const points = loyaltyInfo.pointsToUpgrade;
            setPointsToUpgarde(points)
        }
    }, loyaltyInfo)

    const penalties = <PenaltyInfo penaltyCount={user.penaltyPoints} />
    const showInfoModal = () => {
        setShow(true);        
    }
    const handleConfirm = () => {
        setShow(false);
    }

    if(!!user && !!loyaltyCategory){
        return <div className="borderedBlock" align="center">
                <img src={profilePhoto} className="profilePicture rounded-circle" ></img>
                <ProfileInfo infoClass="profileNameLastname" text={user.firstName + " " + user.lastName}/>
                <MarkStars mark={mark} />
                <ProfileInfo infoClass="profileOtherInfo" text={user.city + ", " + user.state }/>
                <ProfileInfo infoClass="profileOtherInfo" text={user.dateBirth}/>
                <hr className="solidDivider"/>
                <ProfileBusinessInfo assetsName="PENALTIES" assetsNum={penalties} rentsName="" rentsNum="" reviewsNum={reviewNum}/>
                <div onClick={() => showInfoModal()} style={{cursor:"pointer"}}>Loyalty points: {user.loyaltyPoints}</div>
                <div style={{cursor:"pointer"}} onClick={() => showInfoModal() }>
                    {loyaltyCategory !== "Regular" && <FontAwesomeIcon icon={faMedal} className={loyaltyCategory}/>}
                    {loyaltyCategory } user
                </div>
                {<ConfirmModal title={title} message={message} show={show} handleClose={handleConfirm}/>}
            </div>
    }
}
