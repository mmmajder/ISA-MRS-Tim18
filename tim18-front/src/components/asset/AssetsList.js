import React from 'react';
import '../../assets/styles/asset.css';
import ListedAsset from './ListedAsset';
import { getAssets } from '../../services/api/AssetApi';
import {useEffect, useState} from 'react';

export default function AssetList(){

    const [assets, setAssets] = useState([]);

    useEffect(() => {
        async function fetchAssets(){
            const requestData = await getAssets();
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

