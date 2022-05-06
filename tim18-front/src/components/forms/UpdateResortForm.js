import React from 'react';
import ResortForm from './ResortForm';
import {useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import { getAssetById } from '../../services/api/AssetApi';

export default function UpdateResortForm(){

    const [asset, setAsset] = useState({});
    const {id} = useParams();

    useEffect(() => {
        async function fetchAsset(){
            const requestData = await getAssetById(id);
            console.log(requestData.data);
            setAsset(!!requestData ? requestData.data : {});
            return requestData;
        }
        fetchAsset();
    }, [id])

    return (<>
        <ResortForm resort={asset} buttonText="Update resort" id={asset.id}/>
    </>
    );
}