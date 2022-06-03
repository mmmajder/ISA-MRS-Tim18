import React from 'react';
import { getMonthlyReport, getMonthlyReportById } from '../../services/api/AssetApi';
import { useEffect } from 'react';
import { Row, Col, Table } from 'react-bootstrap';
import { VictoryBar, VictoryChart, VictoryAxis, VictoryTheme, VictoryZoomContainer, VictoryLabel } from 'victory';
import {useState} from 'react';
import "bootstrap/dist/css/bootstrap.css";
import "react-bootstrap-table-next/dist/react-bootstrap-table2.min.css";
import BootstrapTable from "react-bootstrap-table-next";
import paginationFactory from 'react-bootstrap-table2-paginator';
import '../../assets/styles/style.css';

export default function Report(){

    const [data, setData] = useState([]);

    const columns = [
        {
          dataField: "group",
          text: "Group",
          sort: true
        },
        {
          dataField: "income",
          text: "Income",
          sort: true
        },
        {
          dataField: "numOfRes",
          text: "Number of reservations",
          sort: true
        }
      ];

    useEffect(() => {
        let completed = true;
        let canceled = false;
        let potential = true;
        let fromDate = Date.now();
        let toDate = Date.now();
        let reportFilters = {completed, canceled, potential, fromDate, toDate};

        getMonthlyReport(reportFilters).then((response) => {
            let data = [];

            for (let group of response.data){
                data.push({
                    group: group.group,
                    income: group.income,
                    numOfRes: group.numberOfReservations
                });
            }

            for (let group of response.data){
                data.push({
                    group: group.group + "-2",
                    income: group.income,
                    numOfRes: group.numberOfReservations
                });
            }

            for (let group of response.data){
                data.push({
                    group: group.group + "-4",
                    income: group.income,
                    numOfRes: group.numberOfReservations
                });
            }

            setData(data);
        });

        getMonthlyReportById(1000005).then((response) => {
            console.log(response)
            console.log(response.data)
        });
    }, [])

    // reference: https://react-bootstrap-table.github.io/react-bootstrap-table2/storybook/index.html?selectedKind=Pagination&selectedStory=Custom%20Pagination&full=0&addons=1&stories=1&panelRight=0&addonPanel=storybook%2Factions%2Factions-panel
    const customTotal = (from, to, size) => (
        <span className="react-bootstrap-table-pagination-total">
          Showing { from } to { to } of { size } Results
        </span>
      );
      
    const options = {
        paginationSize: 4,
        pageStartIndex: 0,
        // alwaysShowAllBtns: true, // Always show next and previous button
        // withFirstAndLast: false, // Hide the going to First and Last page button
        // hideSizePerPage: true, // Hide the sizePerPage dropdown always
        // hidePageListOnlyOnePage: true, // Hide the pagination list when only one page
        firstPageText: 'First',
        prePageText: 'Back',
        nextPageText: 'Next',
        lastPageText: 'Last',
        nextPageTitle: 'First page',
        prePageTitle: 'Pre page',
        firstPageTitle: 'Next page',
        lastPageTitle: 'Last page',
        showTotal: true,
        paginationTotalRenderer: customTotal,
        disablePageTitle: true,
        sizePerPageList: [{
            text: '5', value: 5
        }, {
            text: '10', value: 10
        }, {
            text: 'All', value: data.length
        }]
    };

    return <div className='borderedBlock mt-4'>
            <Row>
                <Col sm={6} align="center" className='mt-3'>
                    <p style={{fontSize: "xx-large"}}>Income</p>
                </Col>
                <Col sm={6} align="center" className='mt-3'>
                    <p style={{fontSize: "xx-large"}}>Number of Reserations</p>
                </Col>
            </Row>
            <Row >
                <Col sm={6}>
                    <VictoryChart
                        theme={VictoryTheme.material}
                        domainPadding={20}
                        containerComponent={
                            <VictoryZoomContainer
                            allowZoom={false}
                            zoomDomain={{x:[0,4]}}/>
                        }
                        animate={{
                            duration: 1000,
                        }}
                        
                    >
                        <VictoryAxis />
                        <VictoryAxis
                        dependentAxis
                        tickFormat={(x) => (`${x} €`)}
                        />
                        <VictoryBar
                            data={data}
                            x="group"
                            y="income"
                            barWidth={30}
                            labels={({ datum }) => datum.income}
                            style={
                                { 
                                    labels: { fill: "white" },
                                    data: { fill: "#5da4b4" } 
                                }
                            }
                            labelComponent={<VictoryLabel dy={30}/>}
                            
                        />
                    </VictoryChart>
                </Col>
                <Col sm={6}>
                    <VictoryChart
                        theme={VictoryTheme.material}
                        domainPadding={20}
                        containerComponent={
                            <VictoryZoomContainer
                            allowZoom={false}
                            zoomDomain={{x:[0,4]}}/>
                        }
                        animate={{
                            duration: 1000,
                        }}
                    >
                        <VictoryAxis />
                        <VictoryAxis
                        dependentAxis
                        tickFormat={(x) => (`${x}`)}
                        />
                        <VictoryBar
                            data={data}
                            x="group"
                            y="numOfRes"
                            barWidth={30}
                            labels={({ datum }) => datum.numOfRes}
                            style={
                                { 
                                    labels: { fill: "white" },
                                    data: { fill: "#5da4b4" } 
                                }
                            }
                            labelComponent={<VictoryLabel dy={30}/>}
                        />
                    </VictoryChart>
                </Col>
            </Row>
            <Row>
                <BootstrapTable
                    bootstrap4
                    keyField="group"
                    data={data}
                    columns={columns}
                    pagination={ paginationFactory(options) }
                />
            </Row>
        </div> 
}