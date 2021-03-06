import React from 'react';
import '../../assets/styles/asset.css';
import { Row, Col } from 'react-bootstrap';
import FixedWidthRegButton from '../buttons/FixedWidthRegButton';
import AssetMainInfo from './AssetMainInfo';
import { getRole }  from '../../services/AuthService/AuthService'
import {useState, useEffect, useCallback} from 'react';
import {getAssetPhotoIdsFromServer, getPhotoFromServer} from '../../services/api/ImageApi';
import {getAssetTodayPrice, deleteAsset} from '../../services/api/AssetApi';
import {deleteAssetAdmin} from '../../services/api/AssetApi';
import {getAssetRating} from '../../services/api/ReviewApi'
import { Button } from 'react-bootstrap';

export default function ListedAsset({asset, isSearch, subscriptionProp, deleteFunc, removeAsset}){
    // let assetType = "RESORT";
    let assetType = asset.assetType;
    const userType = getRole();

    const [assetProfilePhoto, setAssetProfilePhoto] = useState();
    const [assetPrice, setAssetPrice] = useState(0);
    const [mark, setMark] = useState(0);
    
    const detViewUrl = "/resorts/" + asset.id;
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
            getAssetRating(asset.id).then((response) => {
                let mar = response.data;
                setMark(mar);
            })
        }
    }, [asset]);

    const deleteAss = useCallback(
        () => {
            console.log("deleteAss i asset.id " + asset.id);
            console.log(deleteFunc)
            deleteFunc(asset.id);
        }, [deleteFunc, asset]
    )

    const adminDeleteAsset = () => {
        deleteAssetAdmin(asset.id)
        removeAsset(asset.id)
    }

    return <div className="borderedBlock mt-3" align="">
                <Row>
                    <Col sm="3">
                        <img src={assetProfilePhoto} className="listedAssetImage"/>
                    </Col>
                    <Col sm="6">
                        <Row>
                            <Col sm="7">
                                <AssetMainInfo name={asset.name} mark={mark} address={asset.address} price={assetPrice}/>
                            </Col>
                            <Col sm="3">
                                
                            </Col>
                            <Col sm="2" >
                                <div className='mt-4'>
                                    <FixedWidthRegButton href={detViewUrl} text='Preview' onClickFunction={''}/>
                                    { !isSearch && <><Button variant="custom" className='fixedWidthButton formButton pe-5 ps-5 mt-2' onClick={deleteAss}>
                                                        Delete
                                                    </Button></>}
                                    { subscriptionProp!==undefined &&  <FixedWidthRegButton text={subscriptionProp.text} onClickFunction={()=>subscriptionProp.subscribe(asset.id)}/>}
                                    { userType==="Admin" && <FixedWidthRegButton text='Delete' onClickFunction={adminDeleteAsset}/>}
                                </div>
                            </Col>
                        </Row>
                    </Col>
                </Row>
            </div>
}

