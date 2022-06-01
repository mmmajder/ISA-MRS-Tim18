import React from 'react';
import MarkStars from '../MarkStars';
import {getPhotoFromServer} from '../../services/api/ImageApi';
import {useState, useEffect, useCallback} from 'react';
import {getRating} from '../../services/api/ReviewApi'
import { Link } from "react-router-dom";

export default function RenterInfo({renter}){

    const [profilePhoto, setProfilePhoto] = useState();
    const [mark, setMark] = useState(0);
    const [profileUrl, setProfileUrl] = useState("/profile");

    const getProfilePhoto = useCallback(
        (e) => {
            getPhotoFromServer(renter.profilePhotoId).then((response) =>{
                let photo = `data:image/jpeg;base64,${response.data}`
                setProfilePhoto(photo);
            });
        }, [renter]
    )

    useEffect(() => {
        if (!!renter){
            getProfilePhoto()
            getRating(renter.id).then((response) => {
                let mar = response.data;
                setMark(mar);
            })
            setProfileUrl("/profile/"+renter.id);
        }
    }, [renter])

    let renterInfo = "";
    if (renter != undefined)
        renterInfo = "" + renter.firstName + " " + renter.lastName + ", " + renter.city;

    return <div className='mt-4'><span className='borderedBlockNoShadow m-3'>
                <Link to={profileUrl}><img src={profilePhoto} className="renterProfilePicture rounded-circle me-4 ms-1"/></Link>
                {renterInfo}
                <MarkStars  mark={mark} />
            </span></div>
}

