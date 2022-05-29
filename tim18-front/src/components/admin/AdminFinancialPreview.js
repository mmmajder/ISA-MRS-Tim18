import React from 'react'
import BootstrapTable from 'react-bootstrap-table-next';
import cellEditFactory from 'react-bootstrap-table2-editor';
import { getLoyatyProgram, getReservationFinances, saveLoyaltyProgram } from '../../services/api/LoyaltyProgramApi';
import {useEffect, useState} from 'react';
import './../../assets/styles/admin.css'
import { Form, Row, Col, Button } from 'react-bootstrap';
import { getLogged } from '../../services/api/LoginApi';
import AddLoyaltyRowModal from './AddLoyaltyRowModal';


const AdminFinancialPreview = () => {
    const [loyaltyProgram, setLoyaltyProgram] = useState([]);
    const [taxPercent, setTaxPercent] = useState(101);
    const [pointsPerReservation, setPointsPerReservation] = useState(0);
    const [ClientProgram, setClientProgram] = useState([])
    const [RenterProgram, setRenterProgram] = useState([])
    const [activeForm, setActiveForm] = useState(null);
    const [selectedRowClient, setSelectedRowClient] = useState(null)
    const [selectedRowRenter, setSelectedRowRenter] = useState(null)
    const [modalShow, setModalShow] = useState(false);
    const [addTable, setAddTable] = useState(null)
    const [firstRender, setRender] = useState(true)
    const [loyaltyType, setLoyaltyType] = useState(true)
    const [validated, setValidated] = useState(false);
    const [deletedRows, setDeletedRows] = useState([])

    useEffect(() => {
        async function fetchLoyaltyProgram(){
            const requestData = await getLoyatyProgram();
            setLoyaltyProgram(!!requestData ? requestData.data : []);
            return requestData; 
        }
        fetchLoyaltyProgram();

        async function fetchReservationFinances(){
            const requestData = await getReservationFinances();
            setTaxPercent(!!requestData ? requestData.data[0].reservationTax : []);
            setPointsPerReservation(!!requestData ? requestData.data[0].pointsPerReservation : []);
            return requestData;
        }
        fetchReservationFinances();
 

    }, [])

    const addClientLevel = (data) => {
        setClientProgram([...ClientProgram, {...data, userDiscountType:"Client"}])
    }

    const removeClientRow = () => {
        setClientProgram(ClientProgram.filter((element) => {
            if (element.level != selectedRowClient.level) {
                return element;
            }
        }))
        setDeletedRows(...deletedRows, {...selectedRowClient, userDiscountType:"Client"})
    }

    const removeRenterRow = () => {
        setRenterProgram(RenterProgram.filter((element) => {
            if (element.level != selectedRowRenter.level) {
                return element;
            }
        }))
        setDeletedRows(...deletedRows, {...selectedRowRenter, userDiscountType:"Renter"})
    }

    const addRenterLevel = (data) => {
        setRenterProgram([...RenterProgram, {...data, userDiscountType:"Renter"}])
    }

    const saveChanges = (event) => {
        const form = event.currentTarget;
        //if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
       // }
    
        setValidated(true);
        saveLoyaltyProgram(ClientProgram, RenterProgram, taxPercent, pointsPerReservation, deletedRows)
    }

    

    let clientData = []
    let renterData = []
    if (loyaltyProgram.length>0 && taxPercent!=101 && taxPercent!=undefined) {
        if (firstRender) {
            for (let i=0; i<loyaltyProgram.length; i++) {
                if (loyaltyProgram[i].userDiscountType=="Client") {
                clientData = [...clientData, loyaltyProgram[i]]
                }
                else {
                    renterData = [...renterData, loyaltyProgram[i]]
                }
            }
            setClientProgram(clientData)
            setRenterProgram(renterData)
            setRender(false)
        }

    const handleOnSelectClient = (row, isSelect) => {
        if (isSelect) {
            setSelectedRowClient(row)
        } 
    }

    const handleOnSelectRenter = (row, isSelect) => {
        if (isSelect) {
            setSelectedRowRenter(row)
        } 
    }

    const selectRowClient = {
        mode: 'radio',
        clickToSelect: true,
        hideSelectColumn: true,
        bgColor: "#5da4b4",
        clickToEdit: true,
        onSelect: handleOnSelectClient
      };

    const selectRowRenter = {
        mode: 'radio',
        clickToSelect: true,
        hideSelectColumn: true,
        bgColor: "#5da4b4",
        clickToEdit: true,
        onSelect: handleOnSelectRenter
    };
    
    const columns = [{
        dataField: 'level',
        text: 'Level',
        headerStyle: (colum, colIndex) => { return { width: '60%', textAlign: 'center' };}
        }, {
        dataField: 'points',
        text: 'Number of points',
        headerStyle: (colum, colIndex) => { return { width: '20%', textAlign: 'center' };},
        style: { textAlign: 'right' },
        validator: (newValue, row, column) => { 
        if (isNaN(newValue)) {
            return {
              valid: false,
              message: 'Price should be numeric'
            };
          }
          if (newValue < 0) {
            return {
              valid: false,
              message: 'Price should be bigger than 0',
              backgroundColor: "White"
            };
          }
          return true; }
        }, {
        dataField: 'discount',
        text: 'Discount (%)',
        headerStyle: (colum, colIndex) => { return { width: '20%', textAlign: 'center' };},
        style: { textAlign: 'right' },
        validator: (newValue, row, column) => { 
            if (isNaN(newValue)) {
                return {
                  valid: false,
                  message: 'Price should be numeric'
                };
              }
              if (newValue < 0 || newValue>100) {
                return {
                  valid: false,
                  message: 'Price should be in range 0-100',
                  backgroundColor: "White"
                };
              }
              return true; }
        }];
        
    return <>
    <Row className="mt-4">
        <Col sm={10} className="importantInfo">
            Client loyalty program
        </Col>
        <Col sm={2}>
        <Row>
        <Col sm={4}>
            <Button className="loyaltybtn" onClick={() => { setLoyaltyType("client"); setModalShow(true)}}>Add</Button>
        </Col>
        <Col sm={8} >
            <Button className="loyaltybtn"  onClick={removeClientRow}>Remove</Button>
        </Col>
        </Row>
        </Col>
    </Row>
    <BootstrapTable keyField='level' data={ ClientProgram } columns={ columns } selectRow={ selectRowClient } cellEdit={ cellEditFactory({
        mode: 'click'})} />
    <Row className="importantInfo mt-5">
        <Col sm={10} className="importantInfo">
        Renter loyalty program
        </Col>
        <Col sm={2}>
        <Row>
        <Col sm={4} >
            <Button className="loyaltybtn" onClick={() => { setLoyaltyType("renter"); setModalShow(true)}}>Add</Button>
        </Col>
        <Col sm={8} >
            <Button className="loyaltybtn"  onClick={removeRenterRow}>Remove</Button>
        </Col>
        </Row>
        </Col>
    </Row>
    <BootstrapTable keyField='level' data={ RenterProgram } columns={ columns } selectRow={ selectRowRenter } cellEdit={ cellEditFactory({
        mode: 'click'}) } /> 
    
    <Form noValidate validated={validated} onSubmit={saveChanges} >
    <Row className='mt-4 mb-2'>
    <Col sm={2} className="mt-1">
        <Form.Label>Reservation tax in % </Form.Label>
    </Col>
    <Col sm={2}>
        <Form.Control name="taxPercent"  type="number" min="0" max="100" required
            value={taxPercent} 
            onChange={(e) => setTaxPercent(e.target.value)}>
        </Form.Control>
        <Form.Control.Feedback type="invalid">
            Please provide positive number less than 100.
          </Form.Control.Feedback>
    </Col>
    </Row>

    <Row className='mt-2 mb-2'>
    <Col sm={2} className="mt-1">
        <Form.Label>Points per reservation </Form.Label>
    </Col>
    <Col sm={2}>
        <Form.Control name="pointsPerReservation"  type="number" min="0"  required
            value={pointsPerReservation} 
            onChange={(e) => setPointsPerReservation(e.target.value)}>
        </Form.Control>
        <Form.Control.Feedback type="invalid">
            Please provide positive number.
          </Form.Control.Feedback>
    </Col>
    </Row>
    
    <Button type="submit" className="mt-2 rightBtn">Save changes</Button>
    </Form>

    {loyaltyType=="client" ? 
    <AddLoyaltyRowModal loyaltyType="client" addLevel={addClientLevel} currentList={ClientProgram} show={modalShow}
    onHide={() => setModalShow(false)}/> 
    : <AddLoyaltyRowModal loyaltyType="renter" addLevel={addRenterLevel} currentList={RenterProgram} show={modalShow}
    onHide={() => setModalShow(false)}/> 
    }
    </>
    }
}

export default AdminFinancialPreview