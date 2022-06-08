import React, { useCallback, useEffect, useState } from 'react';
import { getLogged } from '../../services/api/LoginApi';
import { Row, Col, Form } from 'react-bootstrap';
import ReportCharts from '../asset/ReportCharts';
import ReportTable from '../asset/ReportTable';
import ReportChartsAdmin from './ReportChartsAdmin';
import { getReportAdmin } from '../../services/api/AdminApi';

const AdminFinancialReports = () => {
    const [data, setData] = useState([]);
    const [completed, setCompleted] = useState(true);
    const [canceled, setCanceled] = useState(true);
    const [potential, setPotential] = useState(true);
    const [renter, setRenter] = useState();
    const [assets, setAssets] = useState([]);
    const [chosenAssetId, setChosenAssetId] = useState("all");
    const [fromDate, setFromDate] = useState(new Date());
    const [hasChangedFromDate, setHasChangedFromDate] = useState(false);
    const [toDate, setToDate] = useState(new Date());
    const [hasChangedToDate, setHasChangedToDate] = useState(false);
    const [period, setPeriod] = useState("month");

    useEffect(() => {
        async function fetchUser(){
            await getLogged(setRenter);
        }
        fetchUser();
    }, [])

    useEffect(() => {
        let fromDatee = fromDate;
        if (!hasChangedFromDate)
            fromDatee = "none";

        let toDatee = toDate;
        if (!hasChangedToDate)
            toDatee = "none";

        let assetType = chosenAssetId;
        let reportFilters = {completed, canceled, potential, fromDatee, toDatee, period, assetType};

        console.log(reportFilters);
        
        if (!!renter)
            getReportAdmin(renter.id, reportFilters).then((response) => {
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

    return (
        <div className='borderedBlock mt-4'>
            <Row className='pt-4'>
            <Col sm={2} />
            <Col sm={3}>
                <Form.Select 
                    name="assetIdSelect" 
                    onChange={(e)=>{setChosenAssetId(e.target.value)}}>
                    <option value={"all"}>All</option>
                    <option value={"boats"}>Boats</option>
                    <option value={"resorts"}>Resorts</option>
                    <option value={"adventures"}>Adventures</option>
                        
                </Form.Select>
                <Form.Select className='mt-1' 
                    name="reportPeriodSelect" 
                    onChange={(e)=>{setPeriod(e.target.value)}}>
                        <option value={"year"}>Year</option>
                        <option value={"month"}>Month</option>
                        <option value={"week"}>Week</option>
                </Form.Select>
            </Col>
            <Col sm={2} />
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
        </Row>
        <ReportCharts data={data}/>
    </div>
    )
}
//ReportCharts
export default AdminFinancialReports