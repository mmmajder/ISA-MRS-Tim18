import { faBorderAll, faSailboat, faHouseChimney, faFishFins } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Row, Col } from 'react-bootstrap'; 

export default function AssetTypeOption ({setAssetType}) {
    return (<>
    <Row className='mt-2'>
    
        <Col sm={2}/>
        <Col sm={2} align='center'>
            <label onClick={()=>setAssetType("ALL")}>
                <input  type="radio" name="optionsRadio" id="allOption" value="ALL"  title="ALL" />
                <FontAwesomeIcon className='faRadio' icon={faBorderAll} size='xl'/> ALL
            </label>
        </Col>
        <Col sm={2} align='center'>
            <label onClick={()=>setAssetType("BOAT")}>
                <input type="radio" name="optionsRadio" id="boatOption" value="BOAT"title="BOAT" />
                <FontAwesomeIcon className='faRadio' icon={faSailboat}  size='xl'/> Boats
            </label>
        </Col>
        <Col sm={2} align='center'>
            <label onClick={()=>setAssetType("RESORT")}>
                <input type="radio" name="optionsRadio" id="ResortOption" value="RESORT" title="RESORT" />
                <FontAwesomeIcon className='faRadio' icon={faHouseChimney} size='xl'/> Resorts
            </label>
           
        </Col>
        <Col sm={2} align='center'>
            <label onClick={()=>setAssetType("FISHING_ADVENTURE")}>
                <input type="radio" name="optionsRadio" id="FishingOption" value="FISHING_ADVENTURE" title="FISHING_ADVENTURE" />
                <FontAwesomeIcon className='faRadio' icon={faFishFins}  size='xl'/> Fishing
            </label>
        </Col>
        <Col sm={4}/>
    </Row>
    <Row className='mt-4'></Row>
    </>);
}