import React from 'react'
import '../../assets/styles/profile.css';
import MarkStars from '../MarkStars';
import PenaltyInfo from './PenaltyInfo';
import ProfileInfo from './ProfileInfo';
import ProfileBusinessInfo from './ProfileBusinessInfo';
import {useEffect, useState} from 'react';
import { getClientByID } from '../../services/api/ClientApi';


export default function ClientProfilePreview({id}){
    const [client, setClient]  = useState();
    const [mark, setMark]  = useState(4.1);                 // TODO: real data
    const [penaltyCount, setPenaltyCount]  = useState(2);   // TODO: real data
    const [reviewNum, setReviewNum]  = useState(3);         // TODO: real data
    
    const profilePic = require('../../assets/images/blue_profile_pic.jpg')  // TODO: real data

    useEffect(() => {
        async function fetchClient(){
            const requestData = await getClientByID(id);
            setClient(!!requestData ? requestData.data : {});
            return requestData;
        }
        fetchClient();
    }, [])

    const penalties = <PenaltyInfo penaltyCount={penaltyCount} />

    if(!!client){
        return <div className="borderedBlock" align="center">
                <img src={profilePic} className="profilePicture rounded-circle" ></img>
                <ProfileInfo infoClass="profileNameLastname" text={client.firstName + " " + client.lastName}/>
                <MarkStars mark={mark} />
                <ProfileInfo infoClass="profileOtherInfo" text={client.city + ", " + client.state }/>
                <ProfileInfo infoClass="profileOtherInfo" text={client.dateBirth}/>
                <hr className="solidDivider"/>
                <ProfileBusinessInfo assetsName="PENALTIES" assetsNum={penalties} rentsName="" rentsNum="" reviewsNum={reviewNum}/>
            </div>
    }
}
