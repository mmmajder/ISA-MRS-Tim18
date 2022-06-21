import React from 'react';
import '../../assets/styles/profile.css';
import MarkStars from '../MarkStars';
import ProfileInfo from './ProfileInfo';
import ProfileBusinessInfo from './ProfileBusinessInfo';
import { getPastRenterReservations } from '../../services/api/ReservationApi';
import {useState, useEffect, useCallback} from 'react';
import '../../assets/styles/style.css';
import {getPhotoFromServer} from '../../services/api/ImageApi';
import {getAssetsByUserId} from '../../services/api/AssetApi';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faMedal } from '@fortawesome/free-solid-svg-icons'
import { getCategory } from '../../services/api/LoyaltyProgramApi';
import { ConfirmModal } from '../modal/ConfirmModal';
import { Marginer } from '../forms/Login/marginer';

export default function ProfileInfoBlock({user, reviewNum, mark}){
    const [profilePhoto, setProfilePhoto] = useState();
    const [numOfAssets, setNumOfAssets] = useState(0);
    const [reservations, setReservations] = useState();
    const [reservationsNum, setReservationsNum] = useState(0);
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
            getCategory(user.loyaltyPoints, 'Renter', setLoyaltyInfo)
            getAssetsByUserId(user.id).then((response) => {
                let assets = response.data;
                setNumOfAssets(assets.length);
            });
            async function fetchReservations(){
                await getPastRenterReservations(setReservations, user.id).then(() => {
                    
                });
            }
            if(user !== undefined && user.userType !== 'Client' && user.userType !== 'Guest' && user.userType !== 'Admin'){
              fetchReservations();
            }
        }
    }, [user])

    useEffect(() => {
        if(!!loyaltyInfo){
            setLoyaltyCategory(loyaltyInfo.category)
            const points = loyaltyInfo.pointsToUpgrade;
            setPointsToUpgarde(points)
        }
    }, loyaltyInfo)

    useEffect(() => {
        if (!!reservations){
            setReservationsNum(reservations.length);
        }
    }, [reservations])

    const showInfoModal = () => {
        setShow(true);        
    }
    const handleConfirm = () => {
        setShow(false);
    }

    if(!!user) {
        return <div className="borderedBlock" align="center">
                <img src={profilePhoto} className="profilePicture rounded-circle" ></img>
                <ProfileInfo infoClass="profileNameLastname" text={user.firstName + " " + user.lastName}/>
                {user.userType!="Admin" ? <MarkStars mark={mark} /> : [] }
                <ProfileInfo infoClass="profileOtherInfo" text={user.city + ", " + user.state }/>
                <ProfileInfo infoClass="profileOtherInfo" text={user.dateBirth}/>
                <hr className="solidDivider"/>
                {user.userType!="Admin" ? <ProfileBusinessInfo assetsName={ getAssetName(user.userType)} assetsNum={numOfAssets} rentsName="RENTS" rentsNum={reservationsNum} reviewsNum={reviewNum}/> : []}
                {user.userType!="Admin" && <>
                <Marginer direction="vertical" margin="1em"></Marginer>
                <hr className="solidDivider"/>
                <div onClick={() => showInfoModal()} style={{cursor:"pointer"}}>
                    Loyalty points: {user.loyaltyPoints}
                </div>
                <div style={{cursor:"pointer"}} onClick={() => showInfoModal() }>
                    {loyaltyCategory !== "Regular" && <FontAwesomeIcon icon={faMedal} className={loyaltyCategory}/>}
                    {loyaltyCategory } user
                </div></>}
                {<ConfirmModal title={title} message={message} show={show} handleClose={handleConfirm}/>}
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