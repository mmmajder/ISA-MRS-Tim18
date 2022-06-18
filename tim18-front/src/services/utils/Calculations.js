import { toHHMMSS } from "./TimeUtils";

export const calculateTotalPrice = (props, setPrice, setHours) => {
    const numberOfPeople = props.numberOfPeople;
    const startDate = props.startDate;
    const endDate = props.endDate;
    const startTime = props.startTime;
    const endTime = props.endTime;

    const assetPrice = props.price;

    const fromDateTime = startDate + "T" + toHHMMSS(startTime);
    const toDateTime = endDate + "T" + toHHMMSS(endTime);
    var hours = Math.abs(new Date(fromDateTime) - new Date(toDateTime)) / 36e5;
    console.log(hours * assetPrice)
    if(!hours){
        setPrice(0);
        setHours(0);
    }
    else{
        setHours(hours)
        setPrice(hours * assetPrice )
    }   
}