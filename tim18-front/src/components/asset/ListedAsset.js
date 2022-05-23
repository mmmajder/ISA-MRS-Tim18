import React from 'react';
import '../../assets/styles/asset.css';
import { Row, Col } from 'react-bootstrap';
import FixedWidthRegButton from '../buttons/FixedWidthRegButton';
import AssetMainInfo from './AssetMainInfo';
import { getRole }  from '../../services/AuthService/AuthService'
import {useState, useEffect, useCallback} from 'react';
import {getAssetPhotoIdsFromServer, getPhotoFromServer} from '../../services/api/ImageApi';
import {getAssetTodayPrice} from '../../services/api/AssetApi';

export default function ListedAsset({asset}){
    const userType = getRole();

    const [assetProfilePhoto, setAssetProfilePhoto] = useState();
    const [assetPrice, setAssetPrice] = useState(0);
    
    // getAssetTodayPrice
    const getAssetProfilePhoto = useCallback(
        (e) => {
            getAssetPhotoIdsFromServer(asset.id).then((response) =>{
                let photoIds = response.data;
                let profilePhotoId = photoIds[0];
                getPhotoFromServer(profilePhotoId).then((response) =>{
                    let profilePhoto = `data:image/jpeg;base64,${response.data}`
                    setAssetProfilePhoto(profilePhoto);
                });
            });
        }, []
    )

    const getAssetPrice = useCallback(
        () => {
            console.log("getAsset rpice usaaa")
            getAssetTodayPrice(asset.id).then((response) =>{
                let price = response.data.price;
                console.log("pricee"+price);
                setAssetPrice(price);
            });
        }, [asset, setAssetPrice]
    )

    useEffect(() => {
        if (asset != undefined){
            getAssetProfilePhoto();
            getAssetPrice();
        }
    }, [asset]);



    const detViewUrl = "resorts/" + asset.id;

    return <div className="borderedBlock mt-3" align="">
                <Row>
                    <Col sm="3">
                        <img src={assetProfilePhoto} className="listedAssetImage"/>
                    </Col>
                    <Col sm="6">
                        <Row>
                            <Col sm="7">
                                <AssetMainInfo name={asset.name} mark={asset.averageRating} address={asset.address} price={assetPrice}/>
                            </Col>
                            <Col sm="3">
                                
                            </Col>
                            <Col sm="2" >
                                <div className='mt-4'>
                                    <FixedWidthRegButton href={detViewUrl} text='Preview' onClickFunction={''}/>
                                    { userType !== "Client" && <FixedWidthRegButton text='Delete' onClickFunction={''}/>}
                                </div>
                            </Col>
                        </Row>
                    </Col>
                </Row>
            </div>
}

