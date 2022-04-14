import '../../assets/styles/profile.css';
import MarkStars from '../MarkStars';
import ProfileInfo from './ProfileInfo';
import ProfileBusinessInfo from './ProfileBusinessInfo';
import {useCallback, useEffect} from 'react';

// const fetchUser = async(id) => {
//     const res = await fetch()
//     const data = await res.json()

//     return data
// }

const fetchUser = async(id) => {
    // GET request using fetch with async/await
    const response = await fetch(`http://localhost:8082/users/${id}`);
    return await response.json();
}


export default async function ProfileInfoBlock({id}){

    const profilePic = require('../../assets/images/blue_profile_pic.jpg')
    console.log(id)
    const user = await fetchUser(id)

    return <div className="borderedBlock" align="center">
                <img src={profilePic} className="profilePicture rounded-circle" ></img>
                <ProfileInfo infoClass="profileNameLastname" text={user.firstName + " " + user.lastName}/>
                <MarkStars mark={user.mark} />
                <ProfileInfo infoClass="profileOtherInfo" text={user.city + ", " + user.state }/>
                <ProfileInfo infoClass="profileOtherInfo" text={user.dateBirth}/>
                <hr className="solidDivider"/>
                <ProfileBusinessInfo assetsName={getAssetName(user.userType)} assetsNum="5" rentsName="RENTS" rentsNum="7" reviewsNum="3"/>
            </div>
}

const getAssetName = (userType) => {
    switch (userType) {
        case "BOATOWNER":
            return "BOATS";
        case "FISHINGINSTRUCTOR":
            return "TRIPS";
        case "RESORTOWNER":
            return "RESORTS";
        default:
            break;
    }
} 