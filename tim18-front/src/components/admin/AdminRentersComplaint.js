import React, {useEffect, useState} from 'react'
import { getClientByID } from '../../services/api/ClientApi'
import { Col, Row } from 'react-bootstrap'
import { getRenterByID} from './../../services/api/RenterApi'

const AdminRentersComplaint = ({request}) => {
    const [client, setClient] = useState(null)
    const [renter, setRenter] = useState(null)
    useEffect(() => {
        async function fetchClient(){
            const requestData = await getClientByID(request.clientID);
            setClient(!!requestData ? requestData.data : []);
            return requestData;
        }
    
        async function fetchRenter(){
            const requestData = await getRenterByID(request.renterID);
            setRenter(!!requestData ? requestData.data : []);
            return requestData;
        }
        fetchClient();
        fetchRenter();
    }, [])


    if (client!=null && renter!=null) {
        return (
        <Row>
            <Col>
                <Row>
                    {"Client: " + client.firstName + " "  + client.lastName}
                </Row>
                <Row>
                    {"Reviewer: " + client.firstName + " "  + client.lastName}
                </Row>
            </Col>
        </Row>
        )
    }
}

export default AdminRentersComplaint