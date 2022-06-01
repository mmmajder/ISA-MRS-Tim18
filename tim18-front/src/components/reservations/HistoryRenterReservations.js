import '../../assets/styles/asset.css';
import '../../assets/styles/reservationsTab.css';
import { React, useState, useEffect } from 'react';
import { Form, Row, Col, Button } from 'react-bootstrap'; 
import AssetTypeOption from '../asset/AssetTypeOption';
import { ListedReservation } from './ListReservations';
import { getPastRenterReservations } from '../../services/api/ReservationApi';
import { sortReservations } from '../../services/utils/SortUtils';
import { Marginer } from '../forms/Login/marginer'

export default function HistoryRenterReservations({renter}){
  const [reservations, setReservations] = useState();
  const [listedReservations, setListedReservations] = useState();

  const [filter, setFilter] = useState();
  let filterOptions = [
    { id: 'Date1', name: 'Date (Least to most recent)'},
    { id: 'Date2', name: 'Date (Most to least recent)'},
    { id: 'Price1', name: 'Price (Least to most)'},
    { id: 'Price2', name: 'Price (Most to least)'},
    { id: 'Duration1', name: 'Duration (Shortest to longest)' },
    { id: 'Duration2', name: 'Duration (Longest to shortest)' },
  ];

  useEffect(() => {
      async function fetchReservations(){
          await getPastRenterReservations(setReservations, renter.id);
      }
      if(renter !== undefined){
        fetchReservations();
      }
      
  }, [renter])

  useEffect(() => {
        if (reservations !== undefined){
            let listedReservations = reservations.map((reservation) => <ListedReservation reservation={reservation}/>)
            setListedReservations(listedReservations);
        }
    }, [reservations])
  

  useEffect(() => {
    sortReservations(filter, reservations, setReservations);
  }, [filter])

  return (
    <>
        <Marginer direction="vertical" margin="1em" />
        <Row>
              <Col sm="3"/>
              <Col>
              <h5 style={{textAlign: "center"}} >Sort by:</h5>
              <Form.Select style={{textAlign: "center", width: "100%", fontSize: "20px"}}name="filterOption" id="filterOption"  onChange={(e)=>{setFilter(e.target.value)}}>
                    { filterOptions.map((filterOption) => <option key={filterOption.id} value={filterOption.id}>{filterOption.name}</option>) }
                </Form.Select>
              </Col>  
              <Col sm="3"/>
        </Row>

        <Marginer direction="vertical" margin="2em" />
        
        {listedReservations}
    </>
  );
}
