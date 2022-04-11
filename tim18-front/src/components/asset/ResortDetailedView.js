import '../../assets/styles/asset.css';
import { Row, Col } from 'react-bootstrap';
import MarkStars from '../MarkStars';
import RenterInfo from './RenterInfo';
import { Button } from 'react-bootstrap';
import ResortInfo from './ResortInfo';

export default function ResortDetailedView(){
    const resortImage = require('../../assets/images/Maldives.jpg');
    

    return <>
            <div className="borderedBlock mt-3" align="">
                <Row>
                    <Col sm="6">
                        <img src={resortImage} className="assetImage"/>
                        <RenterInfo/>
                    </Col>
                    <Col sm="6">
                        <ResortInfo />
                    </Col>
                </Row>
                <Row>
                    <Col sm={4}/>
                    <Col sm={4} align='center'>
                        <Button variant="custom" type="submit" className='formButton pe-5 ps-5'>
                            Rent resort
                        </Button>
                    </Col>
                    <Col sm={4}/>
                </Row>
                <Row>
                    {/* Reviews will go under */}
                </Row>
                
            </div>
        </>
}

