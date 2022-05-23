import React from 'react';
import MarkStars from '../MarkStars';
import {getPhotoFromServer} from '../../services/api/ImageApi';
import {useState, useEffect, useCallback} from 'react';

export default function RenterInfo({renter}){

    const [profilePhoto, setProfilePhoto] = useState();

    const getProfilePhoto = useCallback(
        (e) => {
            console.log("renter")
            console.log(renter)
            console.log("renter photo id")
            console.log(renter.profilePhotoId)
            getPhotoFromServer(renter.profilePhotoId).then((response) =>{
                let photo = `data:image/jpeg;base64,${response.data}`
                setProfilePhoto(photo);
            });
        }, [renter]
    )

    useEffect(() => {
        if (!!renter){
            getProfilePhoto()
        }
    }, [renter])

    let renterInfo = "";
    if (renter != undefined)
        renterInfo = "" + renter.firstName + " " + renter.lastName + ", " + renter.city;

    return <div className='mt-4'><span className='borderedBlockNoShadow m-3'>
                <img src={profilePhoto} className="renterProfilePicture rounded-circle me-4 ms-1"/>
                {renterInfo}
                <MarkStars  mark={4.1} />
            </span></div>
}

