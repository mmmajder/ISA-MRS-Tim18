import React from 'react'
import FullCalendar from '@fullcalendar/react' // must go before plugins
import dayGridPlugin from '@fullcalendar/daygrid' // a plugin!
import timeGridPlugin from '@fullcalendar/timegrid'
import { createRef, useState, useEffect } from 'react'
import resourceTimelinePlugin from '@fullcalendar/resource-timeline';
import interactionPlugin from '@fullcalendar/interaction';
import LabeledInput from '../forms/LabeledInput';
import CreateCalendarEventForm from '../forms/calendar/CreateCalendarEventForm'
import './../../assets/styles/calendar.css'
import { getCalendarData } from '../../services/api/CalendarApi'
import { getLogged } from '../../services/api/LoginApi';
import {makeDateString} from './../../services/utils/TimeUtils'

const Calendar = () => {
  const calendarRef = createRef()
  const [resources, setResources] = useState()
  const [events, setEvents] = useState()
  const [user, setUser] = useState()

  useEffect(() => {
    async function fetchUser(){
        await getLogged(setUser);
    }
    fetchUser();
}, [])

  useEffect(() => {
    async function fetchCalendarData(){
        const requestData = await getCalendarData(user.id);
        let resourceList = []
        const data = requestData.data.map((element) => {
          const res = {
            id : element.id,
            title : element.name
          }
          resourceList = resourceList.concat(res)
          
          let retData = element.calendar.availableSingle.map(function(range) {
            var info = {
              title : "Available",
              resourceId : element.id,
              start : makeDateString(range.fromDateTime),
              end : makeDateString(range.toDateTime)
              // start : "2022-05-15T11:30:00",
              // end : "2022-05-16T11:30:00"
            }
            return info;
          })
          retData = retData.concat(element.calendar.specialPriceSingle.map(function(range) {
            var info = {
              title : "Special offer",
              resourceId : element.id,
              start : makeDateString(range.timeRange.fromDateTime),
              end : makeDateString(range.timeRange.toDateTime),
              backgroundColor : "orange",
              borderColor : "orange"
            }
            return info;
          }))
          setResources(resourceList)
          return retData
        }
        );
        if (requestData) {
          console.log(data[0])
          setEvents( makeEventList(data));
        }
        return requestData;
    }
    fetchCalendarData();
}, [user])





const makeEventList = (data) => {
  let retData = []
  for (let i = 0; i < data.length; i++) {
    for (let j = 0; j < data[i].length; j++) {
      retData = [...retData, data[i][j]]
    }
  }
  return retData
}

//TODO
const displayEvents = () => {
  for (let i=0; i<events.length; i++) {

  }
}


  

  return (
    <div>
      <div>
        <CreateCalendarEventForm scope={"global"} onChange={(value)=>{
          if (!!events) {
            let newVal = [...events, value] 
            setEvents(newVal)
          } else { setEvents([value])}
          
        }
      }/>       
      </div>
      <div style={{marginTop: "15px"}}>
      <FullCalendar 
          ref={calendarRef}
          schedulerLicenseKey='CC-Attribution-NonCommercial-NoDerivatives'
          plugins={[ dayGridPlugin, timeGridPlugin, resourceTimelinePlugin, interactionPlugin  ]}
          initialView="resourceTimelineMonth"
          editable = {false}
          selectable = {true}
          select = {function(start, end, allDays) {
            console.log(start)
          }}
          resources={resources}
          events={events}
          
          customButtons={{
            myTimeLineDayBtn: {
              text: "Timeline Day",
              click() {
                const calendar = calendarRef.current
                if (calendar) {
                  const calendarApi = calendar.getApi();
                  calendarApi.changeView("resourceTimelineDay")
                }
              }
            },
            myTimeLineWeekBtn: {
              text: "Timeline Week",
              click() {
                const calendar = calendarRef.current
                if (calendar) {
                  const calendarApi = calendar.getApi();
                  calendarApi.changeView("resourceTimelineWeek")
                }
              }
            },
            myTimeLineMonthBtn: {
              text: "Timeline Month",
              click() {
                const calendar = calendarRef.current
                if (calendar) {
                  const calendarApi = calendar.getApi();
                  calendarApi.changeView("resourceTimelineMonth")
                }
              }
            }
          }}
          
          headerToolbar={{
            left: "prev,next",
            center: "title",
            right: "today,myTimeLineDayBtn,myTimeLineWeekBtn,myTimeLineMonthBtn",
            backgroundColor: "#5da4b4",
          }}
        />
        </div>
    </div>
    
  )
}

export default Calendar