import '../../assets/styles/profile.css';
import MarkStars from '../MarkStars';
import ProfileInfo from './ProfileInfo';
import ProfileBusinessInfo from './ProfileBusinessInfo';
import {useState, useEffect, useCallback} from 'react';
import axios from 'axios';

export default function ProfileInfoBlock({id}){
    const [user, setUser] = useState();


    useEffect(() => {
        const fetchData = async() => {
            
            const data = await fetch(`http://localhost:8000/users/${id}`)
            const json = await data.json()
            setUser(json)
        }
        fetchData();

    })

    const user1 = {
        firstName: "Zema",
        lastName: "Moreplovac",
        address: "Topolska 18",
        city: "Backa Topola",
        state: "Srbija",
        phoneNumber: "064 1231231",
        userType: "FISHINGINSTRUCTOR",
        birthDate:  "23.11.2000.",
        profilePic: "../assets/images/blue_profile_pic.jpg",
        mark: 4.2
      }
   // setUser(user1)

    const profilePic = require('../../assets/images/blue_profile_pic.jpg')
    console.log(id)
    console.log(user)
    return (<div className="borderedBlock" align="center">
                <img src={profilePic} className="profilePicture rounded-circle" ></img>
                <ProfileInfo infoClass="profileNameLastname" text={user.firstName + " " + user.lastName}/>
                <MarkStars mark={user.mark} />
                <ProfileInfo infoClass="profileOtherInfo" text={user.city + ", " + user.state }/>
                <ProfileInfo infoClass="profileOtherInfo" text={user.dateBirth}/>
                <hr className="solidDivider"/>
                <ProfileBusinessInfo assetsName={ getAssetName(user.userType)} assetsNum="5" rentsName="RENTS" rentsNum="7" reviewsNum="3"/>
            </div>)
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