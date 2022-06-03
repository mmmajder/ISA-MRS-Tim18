import React, { useCallback, useEffect } from 'react';
import { getMonthlyReport, getMonthlyReportById } from '../../services/api/AssetApi';
import { Row, Col, Form } from 'react-bootstrap';
import { VictoryBar, VictoryChart, VictoryAxis, VictoryTheme, VictoryZoomContainer, VictoryLabel } from 'victory';
import {useState} from 'react';
import "bootstrap/dist/css/bootstrap.css";
import "react-bootstrap-table-next/dist/react-bootstrap-table2.min.css";
import BootstrapTable from "react-bootstrap-table-next";
import paginationFactory from 'react-bootstrap-table2-paginator';
import '../../assets/styles/style.css';
import { getLogged } from '../../services/api/LoginApi';
import { getAssetsByUserId } from '../../services/api/AssetApi';
import ReportTable from './ReportTable';
import ReportCharts from './ReportCharts';

export default function Report(){

    const [data, setData] = useState([]);
    const [completed, setCompleted] = useState(true);
    const [canceled, setCanceled] = useState(true);
    const [potential, setPotential] = useState(true);
    const [renter, setRenter] = useState();
    const [assets, setAssets] = useState([]);
    const [chosenAssetId, setChosenAssetId] = useState("-1");
    const [fromDate, setFromDate] = useState(new Date());
    const [toDate, setToDate] = useState(new Date());
    const [period, setPeriod] = useState(1);

    useEffect(() => {
        async function fetchUser(){
            await getLogged(setRenter);
        }
        fetchUser();
    }, [])

    useEffect(() => {
        if (!!renter){
            async function fetchAssets(){
                const requestData = await getAssetsByUserId(renter.id);
                console.log(requestData.data);
                setAssets(!!requestData ? requestData.data : []);
                return requestData;
            }
            fetchAssets();
        }
    }, [renter])

    useEffect(() => {
        let reportFilters = {completed, canceled, potential, fromDate, toDate, chosenAssetId, period};

        console.log(reportFilters);

        getMonthlyReport(reportFilters).then((response) => {
            let data = [];

            for (let group of response.data){
                data.push({
                    group: group.group,
                    income: group.income,
                    numOfRes: group.numberOfReservations
                });
            }

            setData(data);
        })
    }, [completed, canceled, potential, chosenAssetId, fromDate, toDate, period]
    )

    return <div className='borderedBlock mt-4'>
            <Row className='pt-4'>
                <Col sm={4}>
                    <Form.Select 
                        name="assetIdSelect" 
                        onChange={(e)=>{setChosenAssetId(e.target.value)}}>
                            <option value={-1}>All</option>
                        { assets.map((asset) => <option value={asset.id}>{asset.name}</option>) }
                    </Form.Select>
                    <Form.Select className='mt-1' 
                        name="reportPeriodSelect" 
                        onChange={(e)=>{setPeriod(e.target.value)}}>
                            <option value={1}>Monthly</option>
                            <option value={2}>Yearly</option>
                            <option value={0}>Weekly</option>
                    </Form.Select>
                </Col>
                <Col sm={1} />
                <Col sm={3}>
                    <Form.Control
                        className="mb-1" type="date" name="fromDate" placeholder="Start date" 
                        value={fromDate}
                        onChange={(e) => {
                                setFromDate(e.target.value)
                            }
                    }/>
                    <Form.Control
                        className="mb-1" type="date" name="toDate" placeholder="End date" 
                        value={toDate}
                        onChange={(e) => {
                                setToDate(e.target.value)
                            }
                    }/>
                </Col>
                <Col sm={1} />
                <Col sm={3}>
                    <div><input
                        type="checkbox"
                        id="topping"
                        name="topping"
                        value="Paneer"
                        checked={completed}
                        onChange={() => setCompleted(!completed)}
                    /> Completed</div>
                    <div><input
                        type="checkbox"
                        id="topping"
                        name="topping"
                        value="Paneer"
                        checked={canceled}
                        onChange={() => setCanceled(!canceled)}
                    /> Canceled</div>
                    <div><input
                        type="checkbox"
                        id="topping"
                        name="topping"
                        value="Paneer"
                        checked={potential}
                        onChange={() => setPotential(!potential)}
                    /> Potential</div>
                </Col>
            </Row>
            <ReportCharts data={data}/>
            <ReportTable data={data}/>
        </div> 
}