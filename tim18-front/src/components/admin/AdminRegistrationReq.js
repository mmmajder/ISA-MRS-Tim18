import React from 'react'
import {useEffect, useState} from 'react';
import { getRegRequests } from '../../services/api/RegistrationRequestApi';
import '../../assets/styles/asset.css';
import ListedRegistrationRequest from './ListedRegistrationRequest';

const AdminRegistrationReq = () => {
    const [requests, setRequests] = useState([]);

    const handleCallback = (childData) =>{
        setRequests(requests.filter(function( obj ) {
            return obj.id !== childData.id;
          }))
    }

    useEffect(() => {
        async function fetchRegistrationRequests(){
            const requestData = await getRegRequests();
            setRequests(!!requestData ? requestData.data : []);
            return requestData;
        }
        fetchRegistrationRequests();
    }, [])
    let listedRequests;
    if (requests != undefined){
        console.log(requests)
        console.log("request")
        listedRequests = requests.map((request) => <ListedRegistrationRequest request={request} key={request.id} onDelete={handleCallback}/>)
    }

    return (
        <>
            {listedRequests}
            <p className='mt-3'></p> 
        </>
    )
    }

export default AdminRegistrationReq