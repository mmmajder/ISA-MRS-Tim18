import React, {useEffect, useState} from 'react'
import { Col, Row } from 'react-bootstrap';
import { getAssetById } from '../../services/api/AssetApi';
import { getClientByID } from '../../services/api/ClientApi';
import { getRenterByID } from '../../services/api/RenterApi';

const AdminReviewMainData = ({request}) => {
    const [client, setClient] = useState(null)
    const [renter, setRenter] = useState(null)
    const [asset, setAsset] = useState(null)
    useEffect(() => {
        async function fetchClient(){
            const requestData = await getClientByID(request.clientID);
            setClient(!!requestData ? requestData.data : []);
            console.log(requestData)
            return requestData;
        }
    
        async function fetchRenter(){
            const requestData = await getRenterByID(request.renterID);
            setRenter(!!requestData ? requestData.data : []);
            console.log(requestData)
            return requestData;
        }
        async function fetchAsset(){
            const requestData = await getAssetById(request.assetId);
            setAsset(!!requestData ? requestData.data : []);
            console.log(requestData)
            return requestData;
        }
        fetchClient();
        fetchRenter();
        fetchAsset();
    }, [])


    if (client!=null && (renter!=null || asset!=null)) {
        let reviewer, reviewee;
        if (request.clientWriting) {
            reviewer = client.firstName + " "  + client.lastName
            if (renter!=null)
                reviewee = "Renter: " + renter.firstName + " "  + renter.lastName
            else 
                reviewee = "Asset: " + asset.name
        } else {
            reviewer = renter.firstName + " "  + renter.lastName
            reviewee = "Client: " + client.firstName + " "  + client.lastName
        }
        return (
        <Row>
            <Col>
                <Row>
                    {"Reviewer: " + reviewer}
                </Row>
                <Row>
                    {reviewee}
                </Row>
            </Col>
        </Row>
        )
    }
}

export default AdminReviewMainData