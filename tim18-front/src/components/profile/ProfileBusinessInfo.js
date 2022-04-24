import React from 'react';
import { Row } from 'react-bootstrap';
import ProfileBusinessInfoColumn from './ProfileBusinessInfoColumn';

export default function ProfileBusinessInfo({assetsName, assetsNum, rentsName, rentsNum, reviewsNum}){

    return  <Row>
                <ProfileBusinessInfoColumn labelName={assetsName} number={assetsNum} />
                <ProfileBusinessInfoColumn labelName={rentsName} number={rentsNum} />
                <ProfileBusinessInfoColumn labelName="REVIEWS" number={reviewsNum} />
            </Row>
}