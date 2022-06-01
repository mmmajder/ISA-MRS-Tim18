import React from 'react';
import '../../assets/styles/asset.css';
import {useEffect, useState, useCallback} from 'react';
import "react-responsive-carousel/lib/styles/carousel.min.css";
import { Carousel } from 'react-responsive-carousel';
import AssetCarouselItem from './AssetCarouselItem';
import { getAssetPhotoIdsFromServer} from '../../services/api/ImageApi';

export default function AssetCarousel({asset}){

    const [carouselItems, setCarouselItems] = useState();

    const renderCarouselItems = useCallback(
        (e) => {
            getAssetPhotoIdsFromServer(asset.id).then((response) =>{
                let photoIds = response.data;
                let photoItems = photoIds.map((id) => <AssetCarouselItem photoId={id} key={id.id} />)
                setCarouselItems(photoItems);
            });
        }, [asset]
    )

    useEffect(() => {
        if (asset != undefined){
            renderCarouselItems();
        }
    }, [asset]);


    return (<Carousel  showThumbs={false} className="assetCarousel">
            {carouselItems}
        </Carousel>
    );
}

