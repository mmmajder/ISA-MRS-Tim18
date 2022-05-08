import React from 'react'
import FullCalendar from '@fullcalendar/react' // must go before plugins
import dayGridPlugin from '@fullcalendar/daygrid' // a plugin!
import timeGridPlugin from '@fullcalendar/timegrid'
import { createRef, useState, useEffect } from 'react'
import resourceTimelinePlugin from '@fullcalendar/resource-timeline';
import interactionPlugin from '@fullcalendar/interaction';
import CreateCalendarEventForm from './CreateCalendarEventForm'
import './../../../assets/styles/calendar.css'
import { getAssetCalendarData } from '../../../services/api/CalendarApi'
import { Row, Col, Container } from 'react-bootstrap';

const CalendarAsset = () => {
    const calendarRef = createRef()
    const [events, setEvents] = useState()

    const userId = localStorage.getItem("userId");
    const assetId = localStorage.getItem("assetId");

    useEffect(() => {
      async function fetchCalendarData() {
        const requestData = await getAssetCalendarData(assetId);
        let retData = requestData.data.calendar.availableSingle.map(function(range) {
          var info = {
            title : "Available",
            start : range.fromDateTime,
            end : range.toDateTime
          }
          return info;
        })
        let specialOffers = requestData.data.calendar.specialPriceSingle.map(function(range) {
          var info = {
            title : "Special offer",
            start : range.timeRange.fromDateTime,
            end : range.timeRange.toDateTime,
            backgroundColor : "orange",
            borderColor : "orange"
          }
          return info;
        })
        if (specialOffers.length!=0){
          retData = [...retData, ...specialOffers] 
        }
        if (requestData) {
          setEvents(retData);
        }
        return requestData;
      }
      fetchCalendarData()
    })

    return (
        <div>
        <Col sm='12'  className="mt-2 importantInfo">Maldivian hut on water</Col>
          <div>
              <CreateCalendarEventForm scope={"asset"} onChange={(value)=>{
              if (!!events) {
                setEvents([...events, value])
              } else { setEvents([value])}
            }
          }/>       
          </div>
          <FullCalendar 
              //dayCellContent = {injectCellCOntent}
              ref={calendarRef}
              schedulerLicenseKey='CC-Attribution-NonCommercial-NoDerivatives'
              plugins={[ dayGridPlugin, timeGridPlugin, resourceTimelinePlugin, interactionPlugin  ]}
              initialView="dayGridMonth"
              editable = {true}
              selectable = {true}
              select = {function(start, end, allDays) {
                console.log(start)
              }}
              events={events}
              
              customButtons={{
                myTimeDayBtn: {
                  text: "Time Day",
                  click() {
                    const calendar = calendarRef.current
                    if (calendar) {
                      const calendarApi = calendar.getApi();
                      calendarApi.changeView("timeGridDay")
                    }
                    // alert("Clicked")
                  }
                },
                myTimeWeekBtn: {
                  text: "Time Week",
                  click() {
                    const calendar = calendarRef.current
                    if (calendar) {
                      const calendarApi = calendar.getApi();
                      calendarApi.changeView("timeGridWeek")
                    }
                    // alert("Clicked")
                  }
                }
              }}
              
              headerToolbar={{
                left: "prev,next",
                center: "title",
                right: "today,myTimeDayBtn,myTimeWeekBtn,dayGridMonth",
                backgroundColor: "#5da4b4",
              }}
            />
        </div>
    )
}

export default CalendarAsset