import { getJsFormatDate } from './DateTimeUtils'

function sortReservationsByDates(reservations){
    var sorted_reservations = reservations.sort((a,b) => {
        var startDateA = new Date(getJsFormatDate(a.timeRange.fromDateTime));
        var startDateB = new Date(getJsFormatDate(b.timeRange.fromDateTime));
        return startDateA - startDateB;
    })
    return sorted_reservations;
}

function sortReservationsByPrice(reservations){
    var sorted_reservations = reservations.sort((a,b) => {
        var priceA = Number(a.asset.price);
        var priceB = Number(b.asset.price);
        console.log("priceA")
        console.log(priceA)
        console.log("priceB")
        console.log(priceB)
        return priceA - priceB;
    })
    return sorted_reservations;
}

function sortReservationsByDuration(reservations){
    var sorted_reservations = reservations.sort((a,b) => {
        var priceA = Number(a.duration);
        var priceB = Number(b.duration);
        return priceA - priceB;
    })
    return sorted_reservations;
}


export function sortReservations(filter, reservations, setReservations){
    var reservationsArray;
    var sorted_reservations;
    if(filter !== undefined && reservations !== undefined){
        reservationsArray = Object.values(reservations)
    }
    if(filter === 'Date1')        { sorted_reservations = sortReservationsByDates(reservationsArray) }
    else if(filter === 'Date2')   { sorted_reservations = sortReservationsByDates(reservationsArray).reverse() }
    else if(filter === 'Price1')  { sorted_reservations = sortReservationsByPrice(reservationsArray) }
    else if(filter === 'Price2')  { sorted_reservations = sortReservationsByPrice(reservationsArray).reverse() }
    else if(filter === 'Duration1')  { sorted_reservations = sortReservationsByDuration(reservationsArray) }
    else if(filter === 'Duration2')  { sorted_reservations = sortReservationsByDuration(reservationsArray).reverse() }

    if(sorted_reservations !== undefined){
        setReservations(sorted_reservations);
    }
}
