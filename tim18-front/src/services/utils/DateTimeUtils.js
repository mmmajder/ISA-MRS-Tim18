
export function getDateFromList(date) {
    let day = date[2].toString();
    if(day.length === 1){ day = "0" + day};
    let month = date[1].toString();
    if(month.length === 1){ month = "0" + month};
    let year = date[0];
    return day + "." + month + "." + year + ".";
}

export function getTimeFromList(dateTime){
    let hours = dateTime[3].toString();
    if(hours.length === 1){ hours = "0" + hours};

    let minutes = dateTime[4].toString();
    if(minutes.length === 1){ minutes = "0" + minutes};
    return hours + ":" + minutes;
}