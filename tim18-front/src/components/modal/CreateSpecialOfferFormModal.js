import React from 'react'
import { Form, Row, Col, Button, Modal } from 'react-bootstrap';
import DateTimePicker from 'react-datetime-picker'
import {useState, useEffect} from 'react';
import { getAllAssetsByUser } from '../../services/api/AssetApi';
import { getLogged } from '../../services/api/LoginApi.js';
import TimePicker from 'react-bootstrap-time-picker';
import Time from '../forms/calendar/Time';
import moment from 'moment';
import { createAppointment } from '../../services/api/CalendarApi';
import { toHHMMSS } from '../../services/utils/TimeUtils';

const CreateSpecialOfferFormModal = (props) => {

    const [show, setShow] = useState(true);
  
    const handleClose = () => setShow(false);

    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(new Date());
    const [expirationDate, setExpirationDate] = useState()
    const [startTime, setStartTime] = useState(new Date());
    const [endTime, setEndTime] = useState(new Date());
    const [expirationTime, setExpirationTime] = useState()

    const [assetId, setAssetId] = useState({});
    const [assets, setAssets] = useState([])

    const [user, setUser] = useState([]);
    useEffect(() => {
        async function fetchUser(){
            await getLogged(setUser);
        }
        fetchUser();
    }, [])

    const userId = user.id;

    const addAppointment = (e) => {
        const fromDateTime = startDate + "T" + toHHMMSS(startTime)
        const toDateTime = endDate + "T" + toHHMMSS(endTime)
        const offerUntil = expirationDate + "T" + toHHMMSS(expirationTime)
        const type = 'SpecialOffer'

        props.props.onChange({
            title  : 'Special offer',
            start  : fromDateTime,
            end    : toDateTime,
            resourceId : assetId,
            backgroundColor: "orange",
            borderColor: "orange"
          })
        
        const appointmentJson = {fromDateTime, toDateTime, type, userId, assetId, offerUntil}
        createAppointment(JSON.stringify(appointmentJson))
    }

    useEffect(() => {
        setAssetId(localStorage.getItem("assetId"))
        async function fetchAssets(){
            const requestData = await getAllAssetsByUser(userId);
            setAssets(!!requestData ? requestData.data : {});
            return requestData;
        }
        fetchAssets();
    }, [user])

    const assetsElem = () => {
        if (props.scope=="global") {
          try {
            return (
              <Form.Select aria-label="Default select example" name="assets" id="assets" value={assetId} onChange={(e)=>{setAssetId(e.target.value);}}>
                <option></option>
                { assets.map((asset) => <option value={asset.id}>{asset.name}</option>) }
              </Form.Select>)
          } catch (e) {
            return null;
          }
          }
        return null;
        }

    if (!!assets) {
        return (
            <>
  
        <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Add special offer</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group className="mb-2">
                {props.scope=="global" ? (<Form.Label className="mb-1">Asset:</Form.Label>) : []}
                {assetsElem()}
            </Form.Group>
            <Form.Group className="mb-2">
                <Form.Label className="mb-1">From: </Form.Label>
                <Form.Control className="mb-1" type="date" name="dob" placeholder="Start date" value={startDate}
                onChange={(e) => setStartDate(e.target.value)}/>
                <Time setTime={setStartTime} time={startTime}></Time>
            </Form.Group>
            <Form.Group className="mb-2">
                <Form.Label className="mb-1">To: </Form.Label>
                <Form.Control className="mb-1" type="date" name="dob" placeholder="End date" value={endDate}
                onChange={(e) => setEndDate(e.target.value)}/>
                <Time setTime={setEndTime} time={endTime}></Time>
            </Form.Group>
            <Form.Group className="mb-2">
                <Form.Label className="mb-1">Expiration: </Form.Label>
                <Form.Control className="mb-1" type="date" name="dob" placeholder="Expiration" value={expirationDate}
                onChange={(e) => setExpirationDate(e.target.value)}/>
                <Time setTime={setExpirationTime} time={expirationTime}></Time>
            </Form.Group>
            <Form.Group className="mb-2">
                <Form.Label className="mb-1">Discount: </Form.Label>
                <Form.Control type="number" name="dob" />
            </Form.Group>
            
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={addAppointment}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
      </>
            )
        }
    }

export default CreateSpecialOfferFormModal