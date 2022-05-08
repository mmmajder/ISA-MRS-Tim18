import React from 'react';
import '../../assets/styles/asset.css';
import { Row, Col } from 'react-bootstrap';

export default function FishingSpecificInfo(fishingAdventure){
    let biography = "An accomplished fly casting and fly fishing instructor, Ken has taught hundreds of people how to cast and fly fish";
    let fishingGear = "Harpoon";

    return <>
            <Row className="mt-2"><div className="borderedBlockNoShadow" align="">Instructor biography:    {biography}</div></Row>
            <Row className="mt-2"><div className="borderedBlockNoShadow" align="">Fishing gear:    {fishingGear}</div></Row>
        </>
}

