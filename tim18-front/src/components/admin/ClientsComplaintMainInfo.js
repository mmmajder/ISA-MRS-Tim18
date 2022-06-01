import React, {useEffect, useState} from 'react'
import {getRenterByAssetId, getRenterByID} from './../../services/api/RenterApi'
import {getAssetById} from './../../services/api/AssetApi'
import { Col, Row } from 'react-bootstrap'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faUser, faHouseChimney, faFishFins, faShip } from '@fortawesome/free-solid-svg-icons'


const ClientsComplaintMainInfo = ({request}) => {
    const [renter, setRenter] = useState(null)
    const [asset, setAsset] = useState(null)

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

    useEffect(() => {
        console.log("request")
        console.log(request)
        if (request.assetId!=null) {
            fetchAsset();
        }
        else {
            fetchRenter();
        }
    }, [])

    let image;
    let data;
    if (renter || asset){
        //console.log(asset.assetType)
        if (renter!=null) {
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
                    image = <FontAwesomeIcon icon={faShip} className="faButtonsRegistration mt-2"/>
                    break;
                case "FISHING_ADVENTURE":
                    image = <FontAwesomeIcon icon={faFishFins} className="faButtonsRegistration mt-2"/>
                    break;
                case "RESORT":
                    image = <FontAwesomeIcon icon={faHouseChimney} className="faButtonsRegistration mt-2"/>
                    break;    
                default:
                    break;
            }
        }
    }
    
    

    return (
        <Row>
        <Col sm='2'>
            <div className='centerElem'>
                {image}
            </div>
            
        </Col>
        <Col>
            <Row className="importantInfo">
                {data}
            </Row>
        </Col>
    </Row>
    )
}

export default ClientsComplaintMainInfo