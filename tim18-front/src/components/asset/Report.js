import React from 'react';
import { getMonthlyReport, getMonthlyReportById } from '../../services/api/AssetApi';
import { useEffect } from 'react';
import { Row } from 'react-bootstrap';

export default function Report(){

    useEffect(() => {
        let completed = true;
        let canceled = true;
        let potential = true;
        let fromDate = Date.now();
        let toDate = Date.now();
        let reportFilters = {completed, canceled, potential, fromDate, toDate};

        getMonthlyReport(reportFilters).then((response) => {
            console.log(response)
            console.log(response.data)
        });
        getMonthlyReportById(1000005).then((response) => {
            console.log(response)
            console.log(response.data)
        });
    }, [])

    return <>
        <Row className='borderedBlock mt-4'>
        
        </Row>
    </>
}

