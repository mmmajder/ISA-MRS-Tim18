import '../../assets/styles/profile.css';
import MarkStars from '../MarkStars';
import ProfileInfo from './ProfileInfo';
import ProfileBusinessInfo from './ProfileBusinessInfo';

export default function ProfileInfoBlock({user}){
    const profilePic = require('../../assets/images/blue_profile_pic.jpg')

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