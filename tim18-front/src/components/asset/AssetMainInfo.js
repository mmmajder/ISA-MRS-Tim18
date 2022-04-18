import React from 'react';
import '../../assets/styles/asset.css';
import AssetNameAndMark from './AssetNameAndMark';
import AssetAddress from './AssetAddress';

export default function AssetMainInfo({name, address, mark}){

    return <>
            <AssetNameAndMark name={name} mark={mark} />
            <AssetAddress address={address} />
    </>
}

