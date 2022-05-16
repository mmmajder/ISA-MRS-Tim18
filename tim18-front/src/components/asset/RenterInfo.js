import React from 'react';
import MarkStars from '../MarkStars';

export default function RenterInfo({renter}){
    const renterImage = require('../../assets/images/blue_profile_pic.jpg');

    let renterInfo = "";
    if (renter != undefined)
        renterInfo = "" + renter.firstName + " " + renter.lastName + ", " + renter.city;

    return <div className='mt-4'><span className='borderedBlockNoShadow m-3'>
                <img src={renterImage} className="renterProfilePicture rounded-circle me-4 ms-1"/>
                {renterInfo}
                <MarkStars  mark={4.1} />
            </span></div>
}

