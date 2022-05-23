export const displayEventsWhenAdding = (events) => {
    let retData = []
    for (let i=0; i<events.length; i++) {
      events[i].isDeleted = false;
    }
    for (let i=0; i<events.length; i++) {
      if (events[i].isDeleted || events[i].title!="Available") continue
      for (let j=0; j<events.length; j++) {
        if (i==j) continue
        if (events[j].isDeleted || events[j].title!="Available") continue
        if (events[j].start<events[i].start) {
          if (events[j].end>events[i].start) {
            events[j].isDeleted = true
            events[i].start = events[j].start
            if (events[j].end> events[i].end) {
              events[i].end = events[j].end
            }
          }
        } else if(events[j].start < events[i].end) {
          events[j].isDeleted = true;
          if (events[j].end > events[i].end) {
            events[i].end = events[j].end;
          }
        }
      }
    }
    for (let i=0; i<events.length; i++) {
      if (!events[i].isDeleted) {
        retData = [...retData, events[i]]
      }
    }
    return retData;
  }

export const removeAvailable = (events, fromDateTime, toDateTime) => {
let retData = []
for (let i=0; i<events.length; i++) {
  if (events[i].title!="Available") {
    retData = [...retData, events[i]]
    continue
  }
  if (fromDateTime < events[i].start) {
      if (toDateTime < events[i].start) {
          retData = [...retData, events[i]]
          continue;
      }
      else if (toDateTime < events[i].end) {
          events[i].start = toDateTime;
          retData = [...retData, events[i]]
      } //else whole range is deleted
  }
  else if (fromDateTime < events[i].end) {
      if (toDateTime > events[i].end) {
          events[i].end = fromDateTime;
          retData = [...retData, events[i]]
      }
      else if (toDateTime < events[i].end) {
          let first = {...events[i]}
          first.end = fromDateTime
          retData = [...retData, first]
          let second = {...events[i]}
          second.start = toDateTime
          retData = [...retData, second]
      }
  } else {
    retData = [...retData, events[i]]
  }
}
return retData;
}