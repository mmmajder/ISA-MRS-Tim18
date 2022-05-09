import React from 'react';
import ResortForm from './ResortForm';
import BoatForm from './BoatForm';
import {useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import { getAssetById } from '../../services/api/AssetApi';
import AdventureForm from './AdventureForm';


export default function UpdateForm(){
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

    let form;
    let assetType = asset.assetType;

    if (assetType === "RESORT")
        form = <ResortForm resort={asset} buttonText="Update resort" id={asset.id} />
    else if (assetType === "BOAT")
        form = <BoatForm boat={asset} buttonText="Update boat" id={asset.id} />
    else
        // here should go adventure form
        form = <AdventureForm adventure={asset} buttonText="Update adventure" id={asset.id} />

    return (<>
        {form}
    </>
    );
}