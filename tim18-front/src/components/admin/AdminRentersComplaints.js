import React, { useEffect, useState } from 'react'
import { getRentersReviews } from '../../services/api/ReviewApi';
import ListedAdminRentersComplaint from './ListedAdminRentersComplaint';

const AdminRentersComplaints = () => {
    const [requests, setRequests] = useState([]);

    useEffect(() => {
        async function fetchRenterComplaints(){
            const requestData = await getRentersReviews();
            setRequests(!!requestData ? requestData.data : []);
            return requestData;
        }
        fetchRenterComplaints();
    }, [])

    const handleCallback = (childData) =>{
        setRequests(requests.filter(function( obj ) {
            return obj.id !== childData.id;
          }))
    }

    let listedRequests;
    if (requests != undefined){
        console.log(requests)
        console.log("request")
        listedRequests = requests.map((request) => <ListedAdminRentersComplaint request={request} key={request.id} onDelete={handleCallback}/>)
    }

    return (
        <>
            {listedRequests}
            <p className='mt-3'></p> 
        </>
    )
}

export default AdminRentersComplaints