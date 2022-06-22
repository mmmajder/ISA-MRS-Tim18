import React, { useCallback, useEffect } from 'react';
import { getReport, getAssetReport } from '../../services/api/AssetApi';
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
import {getAssetRating} from '../../services/api/ReviewApi';
import MarkStars from '../MarkStars';

export default function Report(){

    const [data, setData] = useState([]);
    const [completed, setCompleted] = useState(true);
    const [canceled, setCanceled] = useState(true);
    const [potential, setPotential] = useState(true);
    const [renter, setRenter] = useState();
    const [assets, setAssets] = useState([]);
    const [chosenAssetId, setChosenAssetId] = useState("-1");
    const [fromDate, setFromDate] = useState(new Date());
    const [hasChangedFromDate, setHasChangedFromDate] = useState(false);
    const [toDate, setToDate] = useState(new Date());
    const [hasChangedToDate, setHasChangedToDate] = useState(false);
    const [period, setPeriod] = useState("month");
    const [averageMarkRow, setAverageMarkRow] = useState();
    const [markStarsRow, setMarkStarsRow] = useState();

    useEffect(() => {
        async function fetchUser(){
            await getLogged(setRenter);
        }
        fetchUser();
    }, [])

    useEffect(() => {
        if (!!renter){
            getAssetsByUserId(renter.id).then(
                (response) => {
                    console.log(response.data);
                    setAssets(response.data);
                }
            )
        }
    }, [renter])

    useEffect(() => {
        let fromDatee = fromDate;
        if (!hasChangedFromDate)
            fromDatee = "none";

        let toDatee = toDate;
        if (!hasChangedToDate)
            toDatee = "none";

        let assetId = chosenAssetId;
        let reportFilters = {completed, canceled, potential, fromDatee, toDatee, period, assetId};

        console.log(reportFilters);
        
        if (!!renter)
            getReport(renter.id, reportFilters).then((response) => {
                fillInData(response.data);
            })

    }, [completed, canceled, potential, chosenAssetId, fromDate, toDate, period, hasChangedFromDate, hasChangedToDate, renter]
    )

    const fillInData = useCallback(
        (responseData) => {
            let data = [];

            for (let group of responseData){
                data.push({
                    group: group.group,
                    income: group.income,
                    numOfRes: group.numberOfReservations
                });
            }

            setData(data);
        }, [setData]
    )

    useEffect(() => {
        if (chosenAssetId != -1){
            getAssetRating(chosenAssetId).then(
                (response) => {
                    let mark = response.data;
                    setAverageMarkRow("Average mark for chosen Asset is:  " + mark);
                    setMarkStarsRow(<MarkStars mark={mark} />);
                }
            )
        } else {
            setAverageMarkRow(null);
            setMarkStarsRow(null);
        }
    }, [chosenAssetId])

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
                            <option value={"month"}>Month</option>
                            <option value={"year"}>Year</option>
                            <option value={"week"}>Week</option>
                    </Form.Select>
                </Col>
                <Col sm={1} />
                <Col sm={3}>
                    <Form.Control
                        className="mb-1" type="date" name="fromDate" placeholder="Start date" 
                        value={fromDate}
                        onChange={(e) => {
                                setFromDate(e.target.value)
                                setHasChangedFromDate(true);
                            }
                    }/>
                    <Form.Control
                        className="mb-1" type="date" name="toDate" placeholder="End date" 
                        value={toDate}
                        onChange={(e) => {
                                setToDate(e.target.value)
                                setHasChangedToDate(true);
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
            <Row>
                {averageMarkRow}
                {markStarsRow}
            </Row>
            <ReportCharts data={data}/>
            <ReportTable data={data}/>
        </div> 
}