import React, { useEffect, useState } from 'react'
import { getPendingNonComplaintReviews } from '../../services/api/ReviewApi';
import ListedAdminReviews from "./ListedAdminReviews"

const AdminReviews = () => {
    const [requests, setRequests] = useState([]);

    useEffect(() => {
        async function fetchRenterComplaints(){
            const requestData = await getPendingNonComplaintReviews();
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
        listedRequests = requests.map((request) => <ListedAdminReviews request={request} key={request.id} onDelete={handleCallback}/>)
    }

    return (
        <>
            <div className="importantInfo mt-2">
                Reviews
            </div>
            {listedRequests}
            <p className='mt-3'></p> 
        </>
    )
}

export default AdminReviews