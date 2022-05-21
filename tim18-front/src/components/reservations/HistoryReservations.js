import '../../assets/styles/asset.css';
import '../../assets/styles/reservationsTab.css';
import { React, useState } from 'react';
import { Form, Row, Col, Button } from 'react-bootstrap'; 
import AssetTypeOption from '../asset/AssetTypeOption';
import FixedWidthRegButton from '../buttons/FixedWidthRegButton';
import RegularButton from '../buttons/RegularButton';
import AssetMainInfo from '../asset/AssetMainInfo';

export default function HistoryReservations({asset, isSearch}){
  const [assets, setAssets] = useState([]);
  const [assetType, setAssetType] = useState("ALL");
  const [price, setPrice] = useState(0);
  const [address, setAddress] = useState("");
  const [numOfPeople, setNumOfPeople] = useState(0);
  const [mark, setMark] = useState(0);
  const [user, setUser] = useState([]);

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
                  <Button variant="custom" type="submit" className='formButton' onClick={"onClickSearchFunction"}>
                      Search
                  </Button>
              </Col>
              <Col sm={4}/>
          </Row>
      <div className="borderedBlock mb-3 mt-3">
        <h2>Content 2</h2>
        <hr />
        <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Sapiente
            voluptatum qui adipisci.
        </p>
    </div>
      <div className="borderedBlock mb-3 mt-3">
        <h2>Content 2</h2>
        <hr />
        <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Sapiente
            voluptatum qui adipisci.
        </p>
    </div>
    <div className="borderedBlock mb-3 mt-3">
        <h2>Content 2</h2>
        <hr />
        <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Sapiente
            voluptatum qui adipisci.
        </p>
    </div>
    <div className="borderedBlock mb-3 mt-3">
        <h2>Content 2</h2>
        <hr />
        <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Sapiente
            voluptatum qui adipisci.
        </p>
    </div>
    <div className="borderedBlock mb-3 mt-3">
        <h2>Content 2</h2>
        <hr />
        <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Sapiente
            voluptatum qui adipisci.
        </p>
    </div>
    </>
    
  );
}


