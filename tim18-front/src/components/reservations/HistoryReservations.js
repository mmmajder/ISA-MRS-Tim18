import '../../assets/styles/asset.css';
import '../../assets/styles/reservationsTab.css';
import { React, useState, useEffect } from 'react';
import { Form, Row, Col, Button } from 'react-bootstrap'; 
import AssetTypeOption from '../asset/AssetTypeOption';
import { ListedReservation } from './ListReservations';
import { getPastReservationsByType } from '../../services/api/ReservationApi';
import { getLogged } from '../../services/api/LoginApi';

export default function HistoryReservations(){
  const [assetType, setAssetType] = useState("ALL");
  const [price, setPrice] = useState(0);
  const [address, setAddress] = useState("");
  const [numOfPeople, setNumOfPeople] = useState(0);
  const [mark, setMark] = useState(0);


  const [reservations, setReservations] = useState();
  const [client, setClient] = useState();
  useEffect(() => {
      async function fetchUser(){
          await getLogged(setClient);
      }
      fetchUser();
  }, [])

  useEffect(() => {
      async function fetchReservations(){
          await getPastReservationsByType(setReservations, client.id, assetType);
      }
      if(client !== undefined){
        fetchReservations();
      }
      
  }, [client, assetType])

  let listedReservations;
    if (reservations !== undefined){
      listedReservations = reservations.map((reservation) => <ListedReservation reservation={reservation}/>)
  }

  const searchFilterFunc = () => {
      console.log("TODO: IMPLEMENTIRAJ SEARCH I FILTER ISTORIJE REZERVACIJA");
  }

  return (
    <>
      
        <AssetTypeOption setAssetType={setAssetType}/>

        <Row className='mt-2'>
            <Col sm={3} align='right'><Form.Label>Max price</Form.Label></Col>
            <Col sm={2}>
                <Form.Control name="price"  type="number" min="0" required
                    value={price} 
                    onChange={(e) => setPrice(e.target.value)}>
                </Form.Control>
            </Col>
            <Col sm={2} align='right'><Form.Label >Address</Form.Label></Col>
            <Col sm={4}>
                <Form.Control value={address} name="address" placeholder="Type address" 
                    onChange={(e) => setAddress(e.target.value)}>
                </Form.Control>
            </Col>
            <Col sm={1}/>
        </Row>
        <Row className='mt-2'>
            <Col sm={3} align='right'><Form.Label>Min number of people</Form.Label></Col>
            <Col sm={2}>
                <Form.Control name="numOfPeople"  type="number" min="1" required
                    value={numOfPeople} 
                    onChange={(e) => setNumOfPeople(e.target.value)}>
                </Form.Control>
            </Col>
            <Col sm={2} align='right'><Form.Label>Min mark </Form.Label></Col>
            <Col sm={4}>
                <Form.Control name="mark"  type="number" min="0" max="5" required
                    value={mark} 
                    onChange={(e) => setMark(e.target.value)}>
                </Form.Control>
            </Col>
            <Col sm={1}/>
        </Row>
        <Row className='mt-2'>
            <Col sm={4}/>
            <Col sm={4} align='center'>
                <Button variant="custom" type="submit" className='formButton' onClick={searchFilterFunc}>
                    Search
                </Button>
            </Col>
            <Col sm={4}/>
        </Row>
        

        {listedReservations}
    </>
    
  );
}


