import React, {useState} from 'react'
import TimePicker from 'react-bootstrap-time-picker';

const Time = (props) => {
  const handleTimeChange = (time) => {
    console.log("time")
    console.log(time);    
    props.setTime(time);
  }
  return <TimePicker onChange={handleTimeChange} value={props.time} />;
}

export default Time

