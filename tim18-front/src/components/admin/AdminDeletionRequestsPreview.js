import React from 'react'
import {useEffect, useState} from 'react';
import { getPendingDeleteProfileRequests } from '../../services/api/DeleteRequestApi';
import ListedDeleteProfileRequest from './ListedDeleteProfileRequest';

const AdminDeletionRequestsPreview = () => {
  const [requests, setRequests] = useState([]);

  useEffect(() => {
    async function fetchRequests(){
        const requestData = await getPendingDeleteProfileRequests();
        setRequests(!!requestData ? requestData.data : []);
        return requestData;
    }
    fetchRequests();
  }, [])

  const handleCallback = (childData) =>{
    setRequests(requests.filter(function( obj ) {
        return obj.id !== childData.id;
      }))
}

  let listedRequests;
  if (requests != undefined){
      listedRequests = requests.map((request) => <ListedDeleteProfileRequest request={request} key={request.id} onDelete={handleCallback}/>)
  }

  return (
    <>
    {listedRequests}
    <p className='mt-3'></p>
    </>
  )
}

export default AdminDeletionRequestsPreview