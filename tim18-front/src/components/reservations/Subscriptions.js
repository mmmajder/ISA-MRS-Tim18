import '../../assets/styles/asset.css';
import '../../assets/styles/reservationsTab.css';
import { React, useState, useEffect } from 'react';
import AssetTypeOption from '../asset/AssetTypeOption';
import { getLogged } from '../../services/api/LoginApi'
import { ListedReservation } from './ListReservations'
import { cancelReservation, getCurrentReservationsByType } from '../../services/api/ReservationApi';
import { ConfirmModal } from '../modal/ConfirmModal';
import ListedAsset from '../asset/ListedAsset';
import { unsubscribeFromAsset, getMySubscriptions } from '../../services/api/SubscriptionApi';
import { Marginer } from '../forms/Login/marginer';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBell } from '@fortawesome/free-regular-svg-icons';

export default function Subscriptions(){
  const [assetType, setAssetType] = useState("ALL");
  const [subscriptions, setSubscriptions] = useState();
  const [client, setClient] = useState();
  const [show, setShow] = useState(false);
  const [unsubscribedAsset, setUnsubscribedAsset] = useState();
  const [listedSubscriptions, setListedSubscriptions] = useState();

  useEffect(() => {
      async function fetchUser(){
          await getLogged(setClient);
      }
      fetchUser();
  }, [])

  useEffect(() => {
      async function fetchSubscriptions(){
          await getMySubscriptions(setSubscriptions, client.id, assetType);
      }
      if(client !== undefined){
        fetchSubscriptions();
      }
      
  }, [client, assetType])
  
  useEffect(() => {
    if (subscriptions !== undefined){
        let subscriptionProp = {text: 'Unsubscribe', subscribe: handleChange}
        let listedSubscriptions = subscriptions.map((subscription) => <ListedAsset asset={subscription.asset} isSearch={true} subscriptionProp={subscriptionProp}/>)
        setListedSubscriptions(listedSubscriptions);
    }
  }, [subscriptions])  


  const handleChange = (assetId) => {
    setShow(true);
    setUnsubscribedAsset(assetId);
  }
  

  
  const handleConfirm = (result) => {
    setShow(false);
    if(result !== 'Cancel'){
      unsubscribeFromAsset(unsubscribedAsset, client.id)
      setSubscriptions(subscriptions.filter(item => item.asset.id !== unsubscribedAsset));
    }
  }

  return (
    <>
      <div className="borderedBlock mb-3 mt-3">
            <h3 style={{textAlign: "center"}}><FontAwesomeIcon icon={faBell}/> My subscriptions </h3>
          
        
        <Marginer direction="vertical" margin="2em"> </Marginer>
        <AssetTypeOption setAssetType={setAssetType}/>
      </div>
      {listedSubscriptions}
      {<ConfirmModal message="Are you sure you want to unsubscribe?" show={show} handleClose={handleConfirm}/>}
    </>
  );
}