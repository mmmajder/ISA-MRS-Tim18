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


const Calendar = ({id}) => {
  const calendarRef = createRef()
  const [resources, setResources] = useState()
  const [events, setEvents] = useState()

  useEffect(() => {
    async function fetchCalendarData(){
        const requestData = await getCalendarData(id);
        let resourceList = []
        const data = requestData.data.map((element) => {
          const res = {
            id : element.id,
            title : element.name
          }
          resourceList = [...resourceList, res]
          
          let retData = element.calendar.availableSingle.map(function(range) {
            var info = {
              title : "Available",
              resourceId : element.id,
              start : range.fromDateTime,
              end : range.toDateTime
            }
            return info;
          })
          retData = [...retData, ...element.calendar.specialPriceSingle.map(function(range) {
            var info = {
              title : "Special offer",
              resourceId : element.id,
              start : range.timeRange.fromDateTime,
              end : range.timeRange.toDateTime,
              backgroundColor : "orange",
              borderColor : "orange"
            }
            return info;
          })]
          setResources(resourceList)
          return retData
        }
        );
        if (requestData) {
          setEvents(data[0]);
        }
        return requestData;
    }
    fetchCalendarData();
}, [])


  

  return (
    <div>
      <div>
        <CreateCalendarEventForm scope={"global"} onChange={(value)=>{
          if (!!events) {
            setEvents([...events, value])
          } else { setEvents([value])}
          
        }
      }/>       
      </div>
      <div style={{marginTop: "15px"}}>
      <FullCalendar 
          ref={calendarRef}
          schedulerLicenseKey='CC-Attribution-NonCommercial-NoDerivatives'
          plugins={[ dayGridPlugin, timeGridPlugin, resourceTimelinePlugin, interactionPlugin  ]}
          initialView="dayGridMonth"
          editable = {true}
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