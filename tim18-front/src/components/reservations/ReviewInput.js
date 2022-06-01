import {  Form, Row, Col, Button} from 'react-bootstrap';
import '../../assets/styles/form.css';
import '../../assets/styles/asset.css';
import AssetScrollerPhotos from '../asset/AssetScrollerPhotos';
import {useParams} from 'react-router-dom';
import { getAssetById } from '../../services/api/AssetApi';
import {useState, useEffect, useCallback} from 'react';
import { createReview } from '../../services/api/ReviewApi'
import LabeledTextarea from '../forms/LabeledTextarea'
import BootstrapSwitchButton from 'bootstrap-switch-button-react'


export default function ReviewInput({reservation, reviewFor, renterId}){
    //TODO COMPLAINT
    //TODO SEND TO ADMIN TO ACCEPT

    const [text, setText] = useState();
    const [rating, setRating] = useState();
    const [buttonActive, setButtonActive] = useState(false);
    const [complaintRow, setComplaintRow] = useState();
    const [complaint, setIsComplaint] = useState(false);
    const [didntShowUp, setDidntShowUp] = useState(false);

    const postReview = useCallback(
        (e) => {
            e.preventDefault();
            let clientWriting = !(reviewFor == "client");
            let clientID = reservation.clientId;
            let assetId = reservation.asset.id;
            let renterID = reviewFor == "asset" ? null : renterId;
            const resortJson = {text, rating, clientWriting, clientID, assetId, renterID, complaint, didntShowUp}
            console.log(resortJson);
            createReview(reservation.id, resortJson).then((response) => {
                console.log("izvrseno")
                window.location.reload(true);
            })
        }, [text, rating, reviewFor, renterId, reservation, createReview, complaint, didntShowUp]
    )

    useEffect(() => {
        if (!!text && !!rating && text != ""){
            setButtonActive(true);
        }else{
            setButtonActive(false);
        }
    }, [text, rating]
    )

    useEffect(() => {
        if (reviewFor == "client"){
            let complRow = <Row className='pt-4'>
                <Col sm={4} align='right'><Form.Label >Make a complaint?</Form.Label></Col>
                <Col sm={2}>
                    <BootstrapSwitchButton
                        checked={complaint}
                        onlabel='Yes'
                        offlabel="No"
                        onChange={(checked) => {
                            setIsComplaint(checked);
                        }}
                    />
                </Col>
                <Col sm={4} align='right'><Form.Label >User didn't show up?</Form.Label></Col>
                <Col sm={2}>
                    <BootstrapSwitchButton
                        size="md"
                        checked={didntShowUp}
                        onlabel="Didn't"
                        offlabel="Did"
                        onChange={(checked) => {
                            setDidntShowUp(checked);
                        }}
                    />
                </Col>
            </Row>
            setComplaintRow(complRow);
        }
    }, [setIsComplaint, setDidntShowUp]
    )

    return (<>
            <Row className='pt-3'>
                <Col sm='7'>
                    <LabeledTextarea value={text} label="Your comment" inputName="text" placeholder="Leave a review about your experience" required onChangeFunc={setText}/>
                </Col>
                <Col sm={3} align='right'><Form.Label>Rate your experience</Form.Label></Col>
                <Col sm={2}>
                    <Form.Control name="rating"  type="number" min="1" max="5" required
                        value={rating} 
                        onChange={(e) => setRating(e.target.value)}>
                    </Form.Control>
                </Col>
               
            </Row>
            {complaintRow}
            <Row className='mt-2'>
            <Col sm={4}/>
            <Col sm={4} align='center'>
                <Button variant="custom" type="submit" className='formButton' onClick={postReview} disabled={!buttonActive}>
                    Make a review
                </Button>
            </Col>
            <Col sm={4}/>
        </Row>
        </>
    );
}
