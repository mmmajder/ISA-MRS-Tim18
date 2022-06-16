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
import { displayEventsWhenAdding, removeSpecialOffer } from './CalendarUtils'
import { removeAvailable } from './CalendarUtils'
import ReserveSpecOfferModal from './ReserveSpecOfferModal'

const Calendar = () => {
  const calendarRef = createRef()
  const [resources, setResources] = useState()
  const [events, setEvents] = useState()
  const [user, setUser] = useState()
  const [activeForm, setActiveForm] = useState(null);

  useEffect(() => {
    async function fetchUser(){
        await getLogged(setUser);
    }
    fetchUser();
}, [])

async function fetchCalendarData(){
  const requestData = await getCalendarData(user.id);
  let resourceList = []
  const data = requestData.data.map((element) => {
    const res = {
      id : element.id,
      title : element.name
    }
    resourceList = resourceList.concat(res)
    
    let retData = element.calendar.available.map(function(range) {
      var info = {
        title : "Available",
        resourceId : element.id,
        start : makeDateString(range.fromDateTime),
        end : makeDateString(range.toDateTime)
      }
      return info;
    })
    retData = retData.concat(element.calendar.specialPrice.map(function(range) {
      var info = {
        id : range.id,
        title : "Special offer",
        resourceId : element.id,
        start : makeDateString(range.timeRange.fromDateTime),
        end : makeDateString(range.timeRange.toDateTime),
        backgroundColor : "orange",
        borderColor : "orange"
      }
      return info;
    }))
    retData = retData.concat(element.calendar.reserved.map(function(range) {
      var info = {
        title : "Reserved",
        resourceId : element.id,
        start : makeDateString(range.timeRange.fromDateTime),
        end : makeDateString(range.timeRange.toDateTime),
        backgroundColor : "grey",
        borderColor : "grey"
      }
      return info;
    }))

    setResources(resourceList)
    return retData
  }
  );
  if (requestData) {
    setEvents(makeEventList(data))
  }
  return requestData;
}

useEffect(() => {
  if(user!==undefined){
    fetchCalendarData();
  }
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

const removeAvailableCallback = (fromDateTime, toDateTime) => {
  setEvents(removeAvailable(events, fromDateTime, toDateTime))
}

const eventClicked = (info) => {
  if (info.event.title=="Special offer") {
    console.log(info)
    setActiveForm(<ReserveSpecOfferModal start={Date.parse(info.event.start)} end={Date.parse(info.event.end)} title={info.event.title} scope={"global"} assetId={info.event._def.resourceIds} specialOfferId={info.event.id} newReservation={createSpecReservationCallback}/>)
  }
}
  const createReservationCallback = (value, fromDateTime, toDateTime) => {
    let newEvents = removeAvailable(events, fromDateTime, toDateTime)
    newEvents = displayEventsWhenAdding([...newEvents, value])
    setEvents(newEvents);
  } 
  const createSpecReservationCallback = (value, fromDateTime, toDateTime) => {
    let newEvents = removeSpecialOffer(events, fromDateTime, toDateTime)
    newEvents = displayEventsWhenAdding([...newEvents, value])
    setEvents(newEvents);
  } 

  const [show, setShow] = useState(true);

  return (
    <div>
      <div>
        <CreateCalendarEventForm show={show} setShow={setShow} newReservation={createReservationCallback} 
                                 periodRemoved = {removeAvailableCallback} scope={"global"} 
        onChange={(value)=>{
          if (!!events) {
            let newVal = [...events, value] 
            let retData = displayEventsWhenAdding(newVal)
            setEvents(retData)
          } else { 
            setEvents([value])
          }
          
        }
      }/>       
      </div>
      <div style={{marginTop: "15px"}}>
      <FullCalendar 
          eventClick = {function(info) {
            eventClicked(info)
          }}

          ref={calendarRef}
          schedulerLicenseKey='CC-Attribution-NonCommercial-NoDerivatives'
          plugins={[ dayGridPlugin, timeGridPlugin, resourceTimelinePlugin, interactionPlugin  ]}
          initialView="resourceTimelineMonth"
          editable = {false}
          selectable = {true}
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
        {show && activeForm}
    </div>
  )
}

export default Calendar
