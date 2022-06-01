import {  Row, Col, Button} from 'react-bootstrap';
import '../../assets/styles/form.css';
import '../../assets/styles/asset.css';
import ReviewContent from './ReviewContent';

export default function ListedReview({reviewId}){

    return (<>
            <div className="borderedBlock mt-3">  
                <Row>
                    <ReviewContent reviewId={reviewId} />
                </Row> 
            </div>
        </>
    );
}
