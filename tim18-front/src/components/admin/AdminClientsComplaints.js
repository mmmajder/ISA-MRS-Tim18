import React, { useEffect, useState } from 'react'
import { getClientsReviews } from '../../services/api/ReviewApi';
import ListedAdminClientsComplaint from './ListedAdminClientsComplaint';

const AdminClientsComplaints = () => {
    const [requests, setRequests] = useState([]);

    useEffect(() => {
        async function fetchClientComplaints(){
            const requestData = await getClientsReviews();
            setRequests(!!requestData ? requestData.data : []);
            return requestData;
        }
        fetchClientComplaints();
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
        listedRequests = requests.map((request) => <ListedAdminClientsComplaint request={request} key={request.id} onDelete={handleCallback}/>)
    }

    return (
        <>
            <div className="importantInfo mt-2">
                Clients complaints
            </div>
            {listedRequests}
            <p className='mt-3'></p> 
        </>
    )
}

export default AdminClientsComplaints