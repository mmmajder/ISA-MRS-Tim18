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

    const assetsData = [
        {name:'Maldivian hut on water', mark:4.7, address: 'Orchid Magu 7, Maadhad, 57887, Maldives', price:"40", id: 1},
        {name:'All inclusive Brunei', mark:3.7, address: 'Brunei City 7, Yupi, 88887, Brunei', price:"30", id: 2},
        {name:'Maldivian hut on water', mark:4.7, address: 'Orchid Magu 7, Maadhad, 57887, Maldives', price:"25", id: 3},
        {name:'All inclusive Brunei', mark:3.7, address: 'Brunei City 7, Yupi, 88887, Brunei', price:"50", id: 4}
    ]

    console.log(assets);

    let listedAssets;
    if (assets != undefined){
        listedAssets = assets.map((asset) => <ListedAsset name={asset.name} mark={asset.averageRating} address={asset.address} price={asset.price} key={asset.id} />)
    }
        

    return <>
            {listedAssets}
            {/* just gives nice space in the bottom */}
            <p className='mt-3'></p> 
        </>












}

