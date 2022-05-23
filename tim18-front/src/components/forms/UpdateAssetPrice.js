import {  Row, Col, Button, Form} from 'react-bootstrap';
import '../../assets/styles/form.css';
import '../../assets/styles/asset.css';
import {useParams} from 'react-router-dom';
import { getAssetById } from '../../services/api/AssetApi';
import {useState, useEffect, useCallback} from 'react';
import {getAssetTodayPrice, createNewPriceForAsset} from '../../services/api/AssetApi'; 

export default function UpdateAssetPrice(){

    const {id} = useParams();
    const [asset, setAsset] = useState();
    const [newPrice, setNewPrice] = useState();
    const [currentPrice, setCurrentPrice] = useState();
    const [hasChoseNewPrice, setHasChoseNewPrice] = useState(false);

    useEffect(() => {
        getAssetById(id).then((requestData) =>{
            setAsset(!!requestData ? requestData.data : {});
        });
    }, [id])

    useEffect(() => {
        if(!!asset){
            getAssetPrice(asset.id);            
        }
    }, [asset])

    const createNewPrice = useCallback(
        () => {
            if (!hasChoseNewPrice){
                return
            }
            createNewPriceForAsset(asset.id, newPrice).then(
                (response) => {
                    let price = response.data.price;
                    console.log("new price:"+price);
                }
            )
        }, [hasChoseNewPrice, newPrice]
    )

    const getAssetPrice = useCallback(
        () => {
            getAssetTodayPrice(asset.id).then((response) =>{
                let price = response.data.price;
                setCurrentPrice(price);
            });
        }, [asset, setCurrentPrice]
    )

    return (<>
            <div className="borderedBlock mt-3">  
                <Row>
                    <Col sm={6} align='center'>
                        Previous prices
                    </Col>
                    <Col sm={6} >
                        <Row>
                            <Col sm={3} align='right'><Form.Label>Current price:</Form.Label></Col>
                            <Col sm={2}>
                                {currentPrice}€
                            </Col>
                        </Row>
                        
                        <Row>
                            <Col sm={3} align='right'><Form.Label>New price:</Form.Label></Col>
                            <Col sm={2}>
                                <Form.Control name="newPrice"  type="number" min="0" required
                                    value={newPrice} 
                                    onChange={(e) => {
                                        setNewPrice(e.target.value); 
                                        setHasChoseNewPrice(true);
                                        }}>
                                </Form.Control>
                            </Col>
                        </Row>

                        <Row className='mt-5'>
                            !New price will be valid from the next day at 00:00!
                        </Row>

                        <Row className="mt-3">
                            <Col sm={12} align="center" >
                            <Button variant="custom" type="submit" className='formButton' disabled={!hasChoseNewPrice} onClick={createNewPrice}>
                                Set new price
                            </Button>
                            </Col>
                        </Row>
                    </Col>
                </Row>
                
                
            </div>
        </>
    );
}
