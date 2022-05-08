import React from 'react';
import MarkStars from '../MarkStars';
import { getRenter } from '../../services/api/InstructorApi';
import {useEffect, useState} from 'react';

export default function RenterInfo(renter){
    const renterImage = require('../../assets/images/blue_profile_pic.jpg');

    console.log("renter "+ renter);

    let renterInfo = "";
    if (renter !== {})
        renterInfo = "" + "Lazar" + " " + "Mocevic" + ", " + "Subotica";

    return <div className='mt-4'><span className='borderedBlockNoShadow m-3'>
                <img src={renterImage} className="renterProfilePicture rounded-circle me-4 ms-1"/>
                {renterInfo}
                <MarkStars  mark={4.1} />
            </span></div>
}

