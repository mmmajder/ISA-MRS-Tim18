import { getDateFromList, getTimeFromList } from '../../services/utils/DateTimeUtils'
import FixedWidthRegButton from '../buttons/FixedWidthRegButton';
import AssetMainInfo from '../asset/AssetMainInfo';
import { Row, Col, Button } from 'react-bootstrap'; 
import {useState, useEffect, useCallback} from 'react';
import {getAssetPhotoIdsFromServer, getPhotoFromServer} from '../../services/api/ImageApi';
import { getCallbackAssetById}  from '../../services/api/AssetApi';
 
export const ListedReservation = ({reservation, handleChange}) => {
    return(
      <>
        <div className="borderedBlock mb-3 mt-3">
          <ReservationHeaderInfo timeRange={reservation.timeRange}/>
          <hr style={{width: "100%"}}></hr>
          <ReservationDetails reservation={reservation} handleChange={handleChange}/>
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
  
  const ReservationDetails = ({reservation, handleChange}) => {

    const assetId = reservation.asset;
    const isCancelable = reservation.cancelable;
    const isReviewable = reservation.reviewable;
    const status = reservation.reservationStatus; // Da li treba prikazati i cancled reservations

    const detViewUrl = "/resorts/" + assetId;
    const reviewsUrl = "/reviews/" + reservation.id;

    const [assetProfilePhoto, setAssetProfilePhoto] = useState();
    const [asset, setAsset] = useState();
    
    const getAssetProfilePhoto = useCallback(
        (e) => {
            getAssetPhotoIdsFromServer(assetId).then((response) =>{
                let photoIds = response.data;
                let profilePhotoId = photoIds[0];
                getPhotoFromServer(profilePhotoId).then((response) =>{
                    let profilePhoto = `data:image/jpeg;base64,${response.data}`
                    setAssetProfilePhoto(profilePhoto);
                });
            });
        }, []
    )

    useEffect(() => {
      getCallbackAssetById(setAsset, assetId)
  }, []);

    
    useEffect(() => {
      if (asset != undefined){
          getAssetProfilePhoto();
      }
  }, [asset]);


  if(!!asset){
    let assetImage; 
    if (asset.assetType === "FISHING_ADVENTURE") {
        assetImage = require('../../assets/images/FishingAdventure3.png')
    } else if (asset.assetType === "RESORT") {
        assetImage = require('../../assets/images/Maldives.jpg')
    } else {
        assetImage = require('../../assets/images/boat.jpg')
    }
    
    return(
      <>
        <Row>
              <Col sm="3">
                  <img src={assetProfilePhoto} alt="Asset" className="listedAssetImage"/>
              </Col>
              <Col sm="7">
                  <Row>
                      <Col sm="7">
                          <AssetMainInfo name={asset.name} mark={asset.averageRating} address={asset.address} price={reservation.totalPrice}/>
                      </Col>
                      <Col sm="3" /> 
                      <Col sm="2" >
                          <div className='mt-3'>
                              <FixedWidthRegButton href={detViewUrl} text='Preview'/>
                          </div>
                          {isCancelable && 
                          <div className='mt-3'>
                            <Button href={"#"} variant="custom" className='alertFixedWidthButton formButton pe-5 ps-5 mt-2' onClick={() => handleChange(reservation)}>
                                Cancel Reservation
                            </Button>
                          </div>
                          }
                          {isReviewable &&
                          <div className='mt-3'>
                              <FixedWidthRegButton href={reviewsUrl} text='Reviews' onClickFunction={''}/>
                          </div>
                          }
                      </Col>
                  </Row>
              </Col>
          </Row>
      </>
    );
  }
    
  }