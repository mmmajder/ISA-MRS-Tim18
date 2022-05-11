import React from 'react';
import '../../assets/styles/asset.css';
import ListedAsset from './ListedAsset';
import { getAssetsByUserId } from '../../services/api/AssetApi';
import {useEffect, useState} from 'react';
import { getLogged } from '../../services/api/LoginApi';

export default function AssetList(){

    const [assets, setAssets] = useState([]);
    const [user, setUser] = useState([]);
    useEffect(() => {
        async function fetchUser(){
            await getLogged(setUser);
        }
        fetchUser();
    }, [])

    useEffect(() => {
        async function fetchAssets(){
            const requestData = await getAssetsByUserId(user.id);
            console.log(requestData.data);
            setAssets(!!requestData ? requestData.data : []);
            return requestData;
        }
        fetchAssets();
    }, [])

    let listedAssets;
    if (assets != undefined){
        listedAssets = assets.map((asset) => <ListedAsset asset={asset} key={asset.id} />)
    }
        
    return <>
            {listedAssets}
            {/* just gives nice space in the bottom */}
            <p className='mt-3'></p> 
        </>












}

