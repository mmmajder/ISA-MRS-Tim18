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