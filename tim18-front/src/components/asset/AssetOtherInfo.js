import React from 'react';
import { Row, Col } from 'react-bootstrap';

export default function AssetOtherInfo({maxNumOfPeope, cancelationFee}){

    return <>
        <Row className="mt-2"><div className="borderedBlockNoShadow" align="">(Rules:)Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi eget nulla congue sapien interdum pulvinar. Quisque a nisi in ex sollicitudin eleifend. Aliquam rutrum erat mauris, sed pulvinar sem tempor at. Cras nec auctor mi. Nam nibh leo, imperdiet et dictum nec, vulputate eget felis. Integer eleifend maximus ligula nec.</div></Row>
        <Row className="mt-2"><div className="borderedBlockNoShadow" align="">(Rules:)Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi eget nulla congue sapien interdum pulvinar. Quisque a nisi in ex sollicitudin eleifend. Aliquam rutrum erat mauris, sed pulvinar sem tempor at. Cras nec auctor mi. Nam nibh leo, imperdiet et dictum nec, vulputate eget felis. Integer eleifend maximus ligula nec.</div></Row>
        <Row className="mt-2">
            <Col sm='6'>
                <p>Maximum number of people: {maxNumOfPeope}</p>
            </Col>  
            <Col sm='6'>
                <p>Cancelation fee: {cancelationFee}%</p>
            </Col>    
        </Row>
    </>
}

