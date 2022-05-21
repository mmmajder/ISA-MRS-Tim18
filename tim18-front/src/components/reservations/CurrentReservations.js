import '../../assets/styles/asset.css';
import '../../assets/styles/reservationsTab.css';
import { React, useState } from 'react';
import { Row, Col, Button } from 'react-bootstrap'; 
import AssetTypeOption from '../asset/AssetTypeOption';
import FixedWidthRegButton from '../buttons/FixedWidthRegButton';
import RegularButton from '../buttons/RegularButton';
import AssetMainInfo from '../asset/AssetMainInfo';

// RegularButton({text, onClickFunction, disabled=true}
export default function CurrentReservations(){
  const [assetType, setAssetType] = useState("ALL");

  let assetImage; 
  if (assetType === "FISHING_ADVENTURE") {
      assetImage = require('../../assets/images/FishingAdventure3.png')
  } else if (assetType === "RESORT") {
      assetImage = require('../../assets/images/Maldives.jpg')
  } else {
      assetImage = require('../../assets/images/boat.jpg')
  }

  const dateTime = {startDate:"12.12.2022.", endDate: "15.12.2022.", arrivalTime: "12h", departureTime: "15h" };
  const asset = {assetImage: assetImage};
  const reservation = {dateTime: dateTime, asset: asset}
  const reservations = {reservation}
  /*let listedReservations;
    if (reservations != undefined){
      listedReservations = reservations.map((reservation) => <ListedReservation reservation={reservation}/>)
  }*/
  return (
    <>
      <AssetTypeOption setAssetType={setAssetType}/>
      {"listedReservations"}
      <ListedReservation reservation={reservation}/>
    </>
  );
}

const ListedReservation = ({reservation}) => {
  return(
    <>
      <div className="borderedBlock mb-3 mt-3">
        <ReservationHeaderInfo dateTime={reservation.dateTime}/>
        <hr style={{width: "100%"}}></hr>
        <ReservationDetails asset={reservation.asset}/>
      </div>
    </>
  )
}

const ReservationHeaderInfo = ({dateTime}) => {
  return(
    <>
      <h4 style={{"text-align": "center"}}>{dateTime.startDate} - {dateTime.endDate} </h4>
        <Row>
            <Col sm="3"/>
            <Col sm="4">
              <h5 >Arrival time:  {dateTime.arrivalTime}</h5>
            </Col>
            <Col sm="4">
              <h5>Departure time:  {dateTime.departureTime}</h5>
            </Col>  
        </Row>
    </>
  )
}

const ReservationDetails = ({asset}) => {
  const detViewUrl = "/resorts/" + asset.id;
  const cancelReservation = () => {

  }
  return(
    <>
      <Row>
            <Col sm="3">
                <img src={asset.assetImage} className="listedAssetImage"/>
            </Col>
            <Col sm="7">
                <Row>
                    <Col sm="7">
                        <AssetMainInfo name={"asset.name"} mark={"asset.averageRating"} address={"asset.address"} price={"asset.price"}/>
                    </Col>
                    <Col sm="3" /> 
                    <Col sm="2" >
                        <div className='mt-3'>
                            <FixedWidthRegButton href={detViewUrl} text='Preview' onClickFunction={''}/>
                        </div>
                        <div className='mt-3'>
                          <Button href={"#"} variant="custom" className='alertFixedWidthButton formButton pe-5 ps-5 mt-2' onClick={cancelReservation}>
                              Cancel Reservation
                          </Button>
                        </div>
                    </Col>
                </Row>
            </Col>
        </Row>
    </>
  );
}