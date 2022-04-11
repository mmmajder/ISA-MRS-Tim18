import MarkStars from '../MarkStars';

export default function RenterInfo(){
    const renterImage = require('../../assets/images/blue_profile_pic.jpg');

    return <div className='mt-4'><span className='borderedBlockNoShadow m-3'>
                <img src={renterImage} className="renterProfilePicture rounded-circle me-4 ms-1"/>
                Name Lastname, Novi Sad
                <MarkStars  mark={4.1} />
            </span></div>
}

