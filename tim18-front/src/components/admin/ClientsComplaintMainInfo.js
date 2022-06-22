import React, {useEffect, useState} from 'react'
import { getRenterByID} from './../../services/api/RenterApi'
import {getAssetById} from './../../services/api/AssetApi'
import { Col, Row } from 'react-bootstrap'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faUser, faHouseChimney, faFishFins, faShip } from '@fortawesome/free-solid-svg-icons'
import { getClientByID } from '../../services/api/ClientApi'


const ClientsComplaintMainInfo = ({request}) => {
    const [renter, setRenter] = useState(null)
    const [asset, setAsset] = useState(null)
    const [client, setClient] = useState(null) 
    let prefix = ""

    useEffect(() => {
        async function fetchAsset(){
            const requestData = await getAssetById(request.assetId);
            setAsset(!!requestData ? requestData.data : []);
            return requestData;
        }
    
        async function fetchRenter(){
            const requestData = await getRenterByID(request.renterID);
            setRenter(!!requestData ? requestData.data : []);
            return requestData;
        }

        async function fetchClient(){
            const requestData = await getClientByID(request.clientID);
            setClient(!!requestData ? requestData.data : []);
            return requestData;
        }

        if (request.assetId!=null) {
            fetchAsset();
        }
        else {
            fetchRenter();
        }

        fetchClient();
    }, [])

    let image;
    let data;
    if (renter || asset){
        //console.log(asset.assetType)
        if (renter!=null) {
            prefix = "Renter: "
            data = renter.firstName + " " + renter.lastName
            switch (renter.userType) {
                case "BoatRenter":
                    image = <FontAwesomeIcon icon={faShip} className="faButtonsRegistration mt-2"/>
                    break;
                case "FishingInstructor":
                    image = <FontAwesomeIcon icon={faFishFins} className="faButtonsRegistration mt-2"/>
                    break;
                case "Client":
                    image = <FontAwesomeIcon icon={faUser} className="faButtonsRegistration mt-2"/>
                    break;
                case "ResortRenter":
                    image = <FontAwesomeIcon icon={faHouseChimney} className="faButtonsRegistration mt-2"/>
                    break;    
                default:
                    break;
            }
        } else {
            data = asset.name
            switch (asset.assetType) {
                case "BOAT":
                    prefix = "Boat: "
                    image = <FontAwesomeIcon icon={faShip} className="faButtonsRegistration mt-2"/>
                    break;
                case "FISHING_ADVENTURE":
                    prefix = "Adventure: "
                    data = asset.name
                    image = <FontAwesomeIcon icon={faFishFins} className="faButtonsRegistration mt-2"/>
                    break;
                case "RESORT":
                    prefix = "Resort: "
                    data = asset.name
                    image = <FontAwesomeIcon icon={faHouseChimney} className="faButtonsRegistration mt-2"/>
                    break;    
                default:
                    break;
            }
        }
    }
    if (client && (renter || asset))
    return (
        <Row>
        <Col sm='2'>
            <div className='centerElem'>
                {image}
            </div>
        </Col>
        <Col>
            <Row>
                {prefix + data}
            </Row>
            <Row>
                {"Reviewer: " + client.firstName + " " + client.lastName}
            </Row>
        </Col>
    </Row>
    )
}

export default ClientsComplaintMainInfo