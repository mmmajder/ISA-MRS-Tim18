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
import {makeDateString} from './../../../services/utils/TimeUtils'
import { displayEventsWhenAdding, removeAvailable } from '../../calendar/CalendarUtils'

const CalendarAsset = () => {
    const calendarRef = createRef()
    const [events, setEvents] = useState()

    const assetId = localStorage.getItem("assetId");
    

    useEffect(() => {
      async function fetchCalendarData() {
        const requestData = await getAssetCalendarData(assetId);
        let retData = requestData.data.calendar.available.map(function(range) {
          var info = {
            title : "Available",
            start : makeDateString(range.fromDateTime),
            end : makeDateString(range.toDateTime)
          }
          return info;
        })
        let specialOffers = requestData.data.calendar.specialPrice.map(function(range) {
          var info = {
            title : "Special offer",
            start : makeDateString(range.timeRange.fromDateTime),
            end : makeDateString(range.timeRange.toDateTime),
            backgroundColor : "orange",
            borderColor : "orange"
          }
          return info;
        })
        if (specialOffers.length!=0){
          retData = [...retData, ...specialOffers] 
        }
        console.log("retData")
        console.log(retData)
        if (requestData) {
          setEvents(retData);
        }
        return requestData;
      }
      fetchCalendarData()
    }, [])

    const removeAvailableCallback = (fromDateTime, toDateTime) => {
      setEvents(removeAvailable(events, fromDateTime, toDateTime))
    }
    
    const [show, setShow] = useState(true);

    return (
        <div>
          <div>
              <CreateCalendarEventForm show={show} setShow={setShow} assetId={assetId} periodRemoved = {removeAvailableCallback} scope={"asset"} onChange={(value)=>{
              if (!!events) {
                setEvents(displayEventsWhenAdding([...events, value]))
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