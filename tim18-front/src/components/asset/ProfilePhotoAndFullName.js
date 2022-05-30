import React from 'react';
import {getPhotoFromServer} from '../../services/api/ImageApi';
import {useState, useEffect, useCallback} from 'react';

export default function ProfilePhotoAndFullName({user}){

    const [profilePhoto, setProfilePhoto] = useState();

    const getProfilePhoto = useCallback(
        (e) => {
            getPhotoFromServer(user.profilePhotoId).then((response) =>{
                let photo = `data:image/jpeg;base64,${response.data}`
                setProfilePhoto(photo);
            });
        }, [user]
    )

    useEffect(() => {
        if (!!user){
            getProfilePhoto()
        }
    }, [user])

    let userInfo = "";
    if (user != undefined)
        userInfo = "" + user.firstName + " " + user.lastName;

    return <>
                <img src={profilePhoto} className="renterProfilePicture rounded-circle me-4 ms-1"/>
                {userInfo}
            </>
}

