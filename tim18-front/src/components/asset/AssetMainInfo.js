import React from 'react';
import '../../assets/styles/asset.css';
import AssetNameMarkPrice from './AssetNameMarkPrice';
import AssetAddress from './AssetAddress';

export default function AssetMainInfo({name, address, mark, price}){

    return <>
            <AssetNameMarkPrice name={name} mark={mark} price={price}/>
            <AssetAddress address={address} />
    </>
}

