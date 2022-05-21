import { getDateFromList, getTimeFromList } from '../../services/utils/DateTimeUtils'
import FixedWidthRegButton from '../buttons/FixedWidthRegButton';
import AssetMainInfo from '../asset/AssetMainInfo';
import { Row, Col, Button } from 'react-bootstrap'; 

export const ListedReservation = ({reservation}) => {
    return(
      <>
        <div className="borderedBlock mb-3 mt-3">
          <ReservationHeaderInfo timeRange={reservation.timeRange}/>
          <hr style={{width: "100%"}}></hr>
          <ReservationDetails asset={reservation.asset} isCancelable={reservation.cancelable}/>
        </div>
      </>
    )
  }
  
  const ReservationHeaderInfo = ({timeRange}) => {
    const startDate = getDateFromList(timeRange.fromDateTime);
    const endDate = getDateFromList(timeRange.toDateTime);
    const startTime = getTimeFromList(timeRange.fromDateTime);
    const endTime =  getTimeFromList(timeRange.toDateTime);
    return(
      <>
        <h4 style={{textAlign: "center"}}>{startDate} - {endDate} </h4>
          <Row>
              <Col sm="3"/>
              <Col sm="4">
                <h5 >Arrival time:  {startTime}</h5>
              </Col>
              <Col sm="4">
                <h5>Departure time:  {endTime}</h5>
              </Col>  
          </Row>
      </>
    )
  }
  
  const ReservationDetails = ({asset, isCancelable}) => {
    const detViewUrl = "/resorts/" + asset.id;
    let assetImage; 
    if (asset.assetType === "FISHING_ADVENTURE") {
        assetImage = require('../../assets/images/FishingAdventure3.png')
    } else if (asset.assetType === "RESORT") {
        assetImage = require('../../assets/images/Maldives.jpg')
    } else {
        assetImage = require('../../assets/images/boat.jpg')
    }
  
    const cancelReservation = () => {
  
    }
    return(
      <>
        <Row>
              <Col sm="3">
                  <img src={assetImage} alt="Asset" className="listedAssetImage"/>
              </Col>
              <Col sm="7">
                  <Row>
                      <Col sm="7">
                          <AssetMainInfo name={asset.name} mark={asset.averageRating} address={asset.address} price={asset.price}/>
                      </Col>
                      <Col sm="3" /> 
                      <Col sm="2" >
                          <div className='mt-3'>
                              <FixedWidthRegButton href={detViewUrl} text='Preview' onClickFunction={''}/>
                          </div>
                          {isCancelable && 
                          <div className='mt-3'>
                            <Button href={"#"} variant="custom" className='alertFixedWidthButton formButton pe-5 ps-5 mt-2' onClick={cancelReservation}>
                                Cancel Reservation
                            </Button>
                          </div>
                          }
                      </Col>
                  </Row>
              </Col>
          </Row>
      </>
    );
  }