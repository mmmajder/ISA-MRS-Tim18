import '../../assets/styles/profile.css';
import MarkStars from '../MarkStars';
import ProfileInfo from './ProfileInfo';
import ProfileBusinessInfo from './ProfileBusinessInfo';

export default function ProfileInfoBlock(){
    const profilePic = require('../../assets/images/blue_profile_pic.jpg')

    return <div className="borderedBlock" align="center">
                <img src={profilePic} className="profilePicture rounded-circle" ></img>
                <ProfileInfo infoClass="profileNameLastname" text="Name Lastname"/>
                <MarkStars mark={4.1} />
                <ProfileInfo infoClass="profileOtherInfo" text="Novi Sad"/>
                <ProfileInfo infoClass="profileOtherInfo" text="23.11.2000."/>
                <hr className="solidDivider"/>
                <ProfileBusinessInfo assetsName="RESORTS" assetsNum="5" rentsName="RENTS" rentsNum="7" reviewsNum="3"/>
            </div>
}