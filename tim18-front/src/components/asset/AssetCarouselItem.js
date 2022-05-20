import React from 'react';
import '../../assets/styles/asset.css';
import {useEffect, useState, useCallback} from 'react';
import "react-responsive-carousel/lib/styles/carousel.min.css";
import { getPhotoFromServer} from '../../services/api/ImageApi';

export default function AssetCarouselItem({photoId}){

    const [assetPhoto, setAssetPhoto] = useState();

    const getPhoto = useCallback(
        (e) => {
            getPhotoFromServer(photoId).then((response) =>{
                console.log(response);
                let photo = `data:image/jpeg;base64,${response.data}`
                setAssetPhoto(photo);
            });
        }, [photoId]
    )

    useEffect(
        (e) => {
            getPhoto();
        }, [photoId]
    )

    return (<div>
                <img src={assetPhoto} className="assetCarouselImage"/>
            </div>
    );
}

