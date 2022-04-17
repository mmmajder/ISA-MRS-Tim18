import React from 'react'
import FullCalendar from '@fullcalendar/react' // must go before plugins
import dayGridPlugin from '@fullcalendar/daygrid' // a plugin!

const Calendar = () => {


  return (
    <FullCalendar
        plugins={[ dayGridPlugin ]}
        initialView="dayGridMonth"
        events={[
          {
            title  : 'event1',
            start  : '2022-04-01'
          },
          {
            title  : 'event2',
            start  : '2022-04-05',
            end    : '2022-04-07'
          },
          {
            title  : 'event3',
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
            end    : '2022-04-09T15:30:00',
            allDay : false // will make the time show
          }
        ]}
      />
  )
}

export default Calendar