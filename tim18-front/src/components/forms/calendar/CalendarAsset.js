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
import { displayEventsWhenAdding, removeAvailable, removeSpecialOffer } from '../../calendar/CalendarUtils'
import RegularButton from '../../buttons/RegularButton'
import { getRole } from '../../../services/AuthService/AuthService'
import { getLogged } from '../../../services/api/LoginApi'
import CreateReservationFormModal from '../../modal/CreateReservationFormModal'
import ReserveSpecOfferModal from '../../calendar/ReserveSpecOfferModal'

const CalendarAsset = ({assetId}) => {
    const guestTxt = "You must be logged in to do this";
    const penaltyTxt = "You have 3 or more penalties, wait for the first of the month for reset";

    const calendarRef = createRef()
    const userType = getRole()
    const [events, setEvents] = useState()
    const [client, setClient] = useState();
    const [show, setShow] = useState(true);
    const [clientShow, setClientShow] = useState(false);
    const [activeForm, setActiveForm] = useState(null);
    useEffect(() => {
      async function fetchUser(){
          await getLogged(setClient);
      }
      fetchUser();
    }, []);
    

    useEffect(() => {
        getAssetCalendarData(assetId).then(
          (response) => {
            let requestData = response;
            let retData = requestData.data.available.map(function(range) {
              var info = {
                title : "Available",
                start : makeDateString(range.fromDateTime),
                end : makeDateString(range.toDateTime)
              }
              return info;
            })
            let specialOffers = requestData.data.specialPrice.map(function(range) {
              var info = {
                id : range.id,
                title : "Special offer",
                start : makeDateString(range.timeRange.fromDateTime),
                end : makeDateString(range.timeRange.toDateTime),
                backgroundColor : "orange",
                borderColor : "orange"
              }
              return info;
            })
            let reserved = requestData.data.reserved.map(function(range) {
              var info = {
                title : "Reserved",
                start : makeDateString(range.timeRange.fromDateTime),
                end : makeDateString(range.timeRange.toDateTime),
                backgroundColor : "grey",
                borderColor : "grey"
              }
              return info;
            })

            if (specialOffers.length!=0){
              retData = [...retData, ...specialOffers] 
            }
            if(reserved.length!=0){
              retData = [...retData, ...reserved] 
            }
            if (requestData) {
              setEvents(retData);
            }
          }
        )
    }, [assetId])

    const removeAvailableCallback = (fromDateTime, toDateTime) => {
      setEvents(removeAvailable(events, fromDateTime, toDateTime))
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

    const clientProps = {assetId: assetId, setShow: setClientShow, show: clientShow, newReservation: createReservationCallback};

    const eventClicked = (info) => {
      if (info.event.title=="Special offer") {
        console.log(info)
        setActiveForm(<ReserveSpecOfferModal start={Date.parse(info.event.start)} end={Date.parse(info.event.end)} title={info.event.title} scope={"private"} assetId={assetId} specialOfferId={info.event.id} newReservation={createSpecReservationCallback}/>)
      }
    }

    return (
        <div>
          <div>
              <CreateCalendarEventForm show={show} setShow={setShow} assetId={assetId} newReservation={createReservationCallback}
                                      periodRemoved = {removeAvailableCallback} scope={"asset"}
              onChange={(value)=>{
              if (!!events) {
                setEvents(displayEventsWhenAdding([...events, value]))
              } else { setEvents([value])}
            }
          }/>       
          </div>
          <FullCalendar 
              //dayCellContent = {injectCellCOntent}
              eventClick = {function(info) {
                eventClicked(info)
              }}
              ref={calendarRef}
              schedulerLicenseKey='CC-Attribution-NonCommercial-NoDerivatives'
              plugins={[ dayGridPlugin, timeGridPlugin, resourceTimelinePlugin, interactionPlugin  ]}
              initialView="dayGridMonth"
              editable = {true}
              selectable = {true}
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
            <Row>
                <Col sm={4}/>
                  <Col sm={4} align='center'>
                      {(userType==="Client" || userType === "Guest" ) && 
                        <RegularButton text='Rent' disabled={userType === "Guest" || client?.penaltyPoints>=3} 
                          disabledReason={userType === "Guest" ? guestTxt: client?.penaltyPoints>=3 ? penaltyTxt : ""} 
                          onClickFunction={() => setClientShow(true)}/>
                      }
                  </Col>
                  <Col sm={4}>
                  </Col>
              </Row>
              {clientShow && <CreateReservationFormModal props={clientProps}/>}
              {activeForm}
        </div>
    )
}

export default CalendarAsset