import React from 'react';
import MarkStars from '../MarkStars';
import { Row, Col, Container } from 'react-bootstrap';
import {faCalendarDays} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';


export default function ResortInfo(){
    const assetCalendar = <div></div>//<AssetCalendar />

    return <>
        <Row className="mt-4">
            <Col sm='11' className="importantInfo">Maldivian hut on water</Col>
            <Col sm='1'><a href="/calendarAsset" style={{color:"grey"}}><FontAwesomeIcon icon={faCalendarDays} className="fa-lg" /></a></Col>
        </Row>
        <Row className="mt-2"><MarkStars mark={4.7}/></Row>
        <Row className="mt-2">Orchid Magu 7, Maadhad, 57887, Maldives</Row>
        <Row className="mt-2"><div className="borderedBlockNoShadow" align="">(Rules:)Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi eget nulla congue sapien interdum pulvinar. Quisque a nisi in ex sollicitudin eleifend. Aliquam rutrum erat mauris, sed pulvinar sem tempor at. Cras nec auctor mi. Nam nibh leo, imperdiet et dictum nec, vulputate eget felis. Integer eleifend maximus ligula nec.</div></Row>
        <Row className="mt-2"><div className="borderedBlockNoShadow" align="">(Rules:)Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi eget nulla congue sapien interdum pulvinar. Quisque a nisi in ex sollicitudin eleifend. Aliquam rutrum erat mauris, sed pulvinar sem tempor at. Cras nec auctor mi. Nam nibh leo, imperdiet et dictum nec, vulputate eget felis. Integer eleifend maximus ligula nec.</div></Row>
        <Row className="mt-2">
            <Col sm='6'>
                <p>Maximum number of people: 4</p>
            </Col>  
            <Col sm='6'>
                <p>Cancelation fee: 40%</p>
            </Col>    
        </Row>
        <Container>
            <Routes>
                <Route path="/calendarAsset" element={assetCalendar}/>
            </Routes>
        </Container>
    </>
}

