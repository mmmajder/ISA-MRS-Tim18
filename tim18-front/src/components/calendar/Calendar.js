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
  const [events, setEvents] = useState()/*[
    {
      title  : 'event1',
      //start  : '2022-04-01',
      daysOfWeek: [ '1', '4' ],
      backgroundColor: '#EC5800',
      borderColor: '#EC5800'
    },
    {
      title  : 'event2',
      resourceId: 'a',
      start  : '2022-04-05',
      end    : '2022-04-07'
    },
    {
      title  : 'event3',
      resourceId: 'a',
      start  : '2022-04-09T12:30:00',
      allDay : false // will make the time show
    },
    {
      title  : 'event3',
      resourceId: 'a',
      start  : '2022-04-09T12:30:00',
      allDay : false // will make the time show
    },
    {
      title  : 'event3',
      resourceId: 'a',
      start  : '2022-04-09T12:30:00',
      allDay : false // will make the time show
    },
    {
      title  : 'event4',
      start  : '2022-04-09T11:30:00',
      allDay : false // will make the time show
    },
    {
      title  : 'event5',
      start  : '2022-04-09T14:30:00',
      allDay : false // will make the time show
    }
  ]) */


  useEffect(() => {
    async function fetchCalendarData(){
        const requestData = await getCalendarData(id);
        const data = requestData.data.map((element) => {
          const res = {
            id : element.id,
            title : element.name
          }
          if (resources==undefined) {
            setResources([res])
          } else { setResources([...resources, res]) }
          
          return element.calendar.availableSingle.map(function(range) {
            var info = {
              title : "Available",
              resourceId : element.id,
              start : range.fromDateTime,
              end : range.toDateTime
            }
            return info;
          })
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
        <CreateCalendarEventForm onChange={(value)=>{
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
          resources={resources/*[
            {
              id: 'a',
              title: 'Room A',
              
            },
            {
              id: 'b',
              title: 'Room B',
              occupancy: 220
            }
          ]*/}
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
            },
            myTimeLineDayBtn: {
              text: "Timeline Day",
              click() {
                const calendar = calendarRef.current
                if (calendar) {
                  const calendarApi = calendar.getApi();
                  calendarApi.changeView("resourceTimelineDay")
                }
                // alert("Clicked")
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
                // alert("Clicked")
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
                // alert("Clicked")
              }
            }
          }}
          
          headerToolbar={{
            left: "prev,next",
            center: "title",
            right: "today,dayGridDay,dayGridWeek,dayGridMonth,myTimeDayBtn,myTimeWeekBtn,myTimeLineDayBtn,myTimeLineWeekBtn,myTimeLineMonthBtn",
            backgroundColor: "#5da4b4"
          }}
        />
    </div>
    
  )
}

export default Calendar