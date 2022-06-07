import React, { useCallback } from 'react';
import '../../assets/styles/asset.css';
import ListedAsset from './ListedAsset';
import { getAssetsByUserId, deleteAsset } from '../../services/api/AssetApi';
import {useEffect, useState} from 'react';
import { getLogged } from '../../services/api/LoginApi';

export default function AssetList(){

    const [assets, setAssets] = useState([]);
    const [user, setUser] = useState([]);
    const [listedAssets, setListedAssets] = useState([])

    useEffect(() => {
        getLogged(setUser);
    }, [])

    useEffect(() => {
        getAssetsByUserId(user.id).then(
            (response) => {
                setAssets(!!response ? response.data : []);
            }
        )
    }, [])

    const assetDeletion = useCallback(
        (assetId) => {
            console.log("assetDeletion");
            deleteAsset(assetId).then(
                (response) => {
                    let index = -1;
                    let i;
                    for (i in assets)
                        if (assets[i].id == assetId)
                            index = i;

                    if (index > -1) {
                        let newAssets = JSON.parse(JSON.stringify(assets)); // deep copy array
                        newAssets.splice(index, 1); // 2nd parameter means remove one item only
                        // setAssets(assets.filter((x) => x.id != id ));
                        setAssets(newAssets);
                    }
                }
            )
        }, [assets]
    )

    useEffect(() => {
        if (assets != undefined){
            // console.log("AAAAAAAAAAAAAAAAAAAAAAA")
            // console.log(assetDeletion)
            // let mappedAssets = assets.map((asset) => <ListedAsset asset={asset} key={asset.id} deleteFunc={assetDeletion}/>)
            // setListedAssets(mappedAssets);
        }
    }, [assets])

    return <>
            {listedAssets}
            {/* just gives nice space in the bottom */}
            <p className='mt-3'></p> 
        </>












}

