import React from 'react';
import '../../assets/styles/asset.css';
import ListedAsset from './ListedAsset';

export default function AssetList(){

    return <>
            <ListedAsset name={'Maldivian hut on water'} mark={4.7} address={'Orchid Magu 7, Maadhad, 57887, Maldives'}/>
            <ListedAsset name={'All inclusive Brunei'} mark={3.7} address={'Brunei City 7, Yupi, 88887, Brunei'}/>
            <ListedAsset name={'Maldivian hut on water'} mark={4.7} address={'Orchid Magu 7, Maadhad, 57887, Maldives'}/>
            <ListedAsset name={'All inclusive Brunei'} mark={3.7} address={'Brunei City 7, Yupi, 88887, Brunei'}/>
            {/* just gives nice space in the bottom */}
            <p className='mt-3'></p> 
        </>
}

