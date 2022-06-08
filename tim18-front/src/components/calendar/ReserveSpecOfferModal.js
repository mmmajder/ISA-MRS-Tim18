import React from 'react'
import {useEffect, useState} from 'react';
import { Form, Button, Modal } from 'react-bootstrap';
import { reserveSepcialOfferRequest } from '../../services/api/ReservationApi';
import { getRole } from '../../services/AuthService/AuthService';
import { getLogged } from '../../services/api/LoginApi.js';
import { getAssetById } from '../../services/api/AssetApi';
import { getSpecialOffer } from '../../services/api/CalendarApi';
import { getAllMappedClients } from '../../services/api/ClientApi';
import Select from 'react-select';
import { getTimeFromList } from '../../services/utils/DateTimeUtils';
import { makeDateString } from '../../services/utils/TimeUtils';

const ReserveSpecOfferModal = ({title, start, end, scope, assetId, specialOfferId, newReservation}) => {
    const handleClose = () => setShow(false);
    const [show, setShow] = useState(true);
    const [user, setUser] = useState()
    const [asset, setAsset] = useState(null)
    const [specialOffer, setSpecialOffer] = useState(null)
    const [clients, setClients] = useState(null)
    const [clientId, setClientId] = useState();
    useEffect(() => {
      async function fetchUser(){
          await getLogged(setUser);
      }
      if(clients===undefined || clientId === undefined){
          fetchClients();   
      }
      fetchUser();
  }, [])
  useEffect(() => {
    async function fetchAsset(){
        const requestData = await getAssetById(assetId);
        setAsset(!!requestData ? requestData.data : {});
        return requestData;
    }
    fetchAsset();
  }, [assetId])

  async function fetchClients(){
    await getAllMappedClients(setClients);
}

  useEffect(() => {
    async function fetchSpecialOffer(){
        const requestData = await getSpecialOffer(specialOfferId);
        setSpecialOffer(!!requestData ? requestData.data : {});
        return requestData;
    }
    fetchSpecialOffer();
}, [])

    //TODO ZAKUCANA CENA
    //TODO ZAKUCAN USER KADA BIRA RENTER
    //TODO ZAKUCAN ASSET
    /*let senderType = getRole()
    let clientId;
    if (senderType=="Client") {
      clientId = user.id
    } else {
      clientId = 2
    }*/

  //  let assetId = "1000005"

    const reserveOffer = () => {
      let request;
      if (scope==="private") {
        if (clientId===undefined) {
          request = {
            specialOfferId: specialOfferId, 
            assetId: assetId, 
            clientId: user.id, 
          }
        }
        else {
          request = {
            specialOfferId: specialOfferId, 
            assetId: assetId, 
            clientId: clientId, 
          }
        }
      } else {
        request = {
          specialOfferId: specialOfferId, 
          assetId: assetId[0], 
          clientId: clientId, 
        }
      }
        reserveSepcialOfferRequest(makeReservationCallback, request)
    }
  
    const makeReservationCallback = (data) => {
      if(data){
          alert("Successfully created reservation.\nPlease check your email.")
          newReservation({
            title  : 'Reserved',
            start  : makeDateString(specialOffer.timeRange.fromDateTime),
            end    : makeDateString(specialOffer.timeRange.toDateTime),
            resourceId : asset.id,
            backgroundColor : "grey",
            borderColor : "grey"
          }, specialOffer.timeRange.fromDateTime, specialOffer.timeRange.toDateTime)
          setShow(false);
      }
      else{
        alert("Oops, you are not able to create this reservation, please try again.")
      }
    }

  if (!!asset && specialOffer) {
    if (asset.name)
    return <>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>{title}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group className="mb-2">
                  <Form.Label className="mb-1">Asset: {asset.name}</Form.Label>
            </Form.Group>
            <Form.Group className="mb-2">
                  <Form.Label className="mb-1">From: {new Date(start).toUTCString()}</Form.Label>
            </Form.Group>
            <Form.Group className="mb-2">
                  <Form.Label className="mb-1">To: {new Date(end).toUTCString()}</Form.Label>
            </Form.Group>
            <Form.Group className="mb-2">
                  <Form.Label className="mb-1">Price: {specialOffer.discount}</Form.Label>
            </Form.Group>
            { user.userType === 'Client' && <Form.Label className="mb-1">{user.firstName} {user.lastName}</Form.Label> }
                        { user.userType !== 'Client' && clients !==undefined &&
                            <Select options={clients} onChange={(selected) => setClientId(selected.value)}/>
                        }
          </Form>
        </Modal.Body>
        <Modal.Footer>
              <Button variant="secondary" onClick={handleClose}>
                Close
              </Button>
              <Button variant="primary" onClick={reserveOffer}>
                Accept
              </Button>
            </Modal.Footer>
      </Modal>
    </>

  }
}

export default ReserveSpecOfferModal