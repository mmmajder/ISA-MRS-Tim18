import React from 'react';
import '../../assets/styles/asset.css';
import ListedAsset from './ListedAsset';
import { getAssetsByUserId, getAssets, getFilteredAssets, getFilteredAssetsForRenter} from '../../services/api/AssetApi';
import {useEffect, useState} from 'react';
import { Form, Row, Col, Button } from 'react-bootstrap';
import { getRole } from '../../services/AuthService/AuthService';
import { getLogged } from '../../services/api/LoginApi';
import '../../assets/styles/buttons.css';
import AssetTypeOption from './AssetTypeOption';
import { Input, Radio, RadioGroup } from '@mui/material';
import { makeDefDatePicker } from '../../services/utils/TimeUtils';

export default function AssetsPreview({isSearch}){

    const [assets, setAssets] = useState([]);
    const [assetType, setAssetType] = useState("ALL");
    const [price, setPrice] = useState(0);
    const [address, setAddress] = useState("");
    const [numOfPeople, setNumOfPeople] = useState(0);
    const [mark, setMark] = useState(0);
    const userType = getRole();
    const [user, setUser] = useState([]);
    const [startDate, setStartDate] = useState(makeDefDatePicker(new Date()))
    const [endDate, setEndDate] = useState(makeDefDatePicker(new Date()))



    useEffect(() => {
        async function fetchClient(){
            await getLogged(setUser);
        }
        fetchClient();
    }, [])

    const onClickSearchFunction = () => {
        if (isSearch || userType==='Client' || userType==='Guest')
            getFilteredAssets(assetType, address, numOfPeople, price, mark, startDate, endDate).then(
                requestData => setAssets(!!requestData ? requestData.data : [])
            );
        else
            getFilteredAssetsForRenter(user.id, address, numOfPeople, price, mark, startDate, endDate).then(
                requestData => setAssets(!!requestData ? requestData.data : [])
            );
    }

    useEffect(() => {
        async function fetchAssets(){
            let requestData;
            if (isSearch || userType==='Client' || userType==='Guest' || userType==='Admin')
                requestData = await getAssets();
            else
                requestData = await getAssetsByUserId(user.id);
            console.log(requestData.data);
            setAssets(!!requestData ? requestData.data : []);
            return requestData;
        }
        if (user != undefined){
            fetchAssets();
        }
    }, [user])

    let listedAssets;
    if (assets != undefined){
        listedAssets = assets.map((asset) => <ListedAsset asset={asset} isSearch={isSearch} />)
    }

    let assetTypeOptions;
    if(isSearch){
        assetTypeOptions = < AssetTypeOption setAssetType={setAssetType}/>
    }

    return <>
            <div className="borderedBlock mb-3 mt-3">
                <Row className="profileNameLastname mb-4">
                    <Col sm={1} />
                    <Col lg={10} align="center" >
                        Find exactly what you are looking for:
                    </Col>
                    <Col sm={1} />
                </Row>
                {assetTypeOptions}
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
                    <Col sm={3} align='right'><Form.Label>Start date:</Form.Label></Col>
                    <Col sm={2}>
                        <Form.Control className="mb-1" type="date" name="dob" placeholder="Start date" value={startDate}
                        onChange={(e) => setStartDate(e.target.value)}/>
                    </Col>
                    <Col sm={2} align='right'><Form.Label>End date: </Form.Label></Col>
                    <Col sm={4}>
                        <Form.Control className="mb-1" type="date" name="dob" placeholder="End date" value={endDate}
                        onChange={(e) => setEndDate(e.target.value)}/>
                    </Col>
                    <Col sm={1}/>
                </Row>
                <Row className='mt-2'>
                    <Col sm={4}/>
                    <Col sm={4} align='center'>
                        <Button variant="custom" type="submit" className='formButton' onClick={onClickSearchFunction}>
                            Search
                        </Button>
                    </Col>
                    <Col sm={4}/>
                </Row>
            </div>
            {listedAssets}
            {/* just gives nice space in the bottom */}
            <p className='mt-3'></p> 
        </>

}

