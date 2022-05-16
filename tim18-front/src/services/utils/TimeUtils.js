export const toHHMMSS = (number) => {
    let hours = parseInt(number / (60*60), 10)
    let minutes = (number/60) % 60
    console.log(hours)
    console.log(minutes)
    
    if (hours<10)
        hours = "0" + hours
    if (minutes==0)
        minutes="00"
        console.log(hours)
        console.log("len " + hours.length)
        console.log(minutes)
    return hours + ":" + minutes + ":" + "00"
}

export const makeDateString = (dateList) => {
    dateList = addNullsToDatetime(dateList)
    return dateList[0] + "-" + dateList[1] + "-" + dateList[2] + "T" + dateList[3] + ":" + dateList[4] + ":00" 
  }

  const addNullsToDatetime = (data) => {
    if (data[1] < 10) {
      data[1] = "0" + data[1] 
    }
    if (data[2] < 10) {
      data[2] = "0" + data[2] 
    }
    if (data[3] < 10) {
      data[3] = "0" + data[3] 
    }
    if (data[4] < 10) {
      data[4] = "0" + data[4] 
    }
    return data
  }

export const makeDateOfList = (data) => {
  let year = data[0]
  let month = data[1]
  let day = data[2]
  let hours = data[3]
  let seconds = data[4]
  return hours + ":" + seconds + " " + day + "." + month + "." + year + "." 
}