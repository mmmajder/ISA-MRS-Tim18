import React from 'react';
import ResortForm from './ResortForm';


export default function CreateResortForm({userType}){
    // const resort = {
    //     name: 'Maldivian hut on water',
    //     address: 'Orchid Magu 7, Maadhad, 57887, Maldives',
    // }

    const resort = {
        name: '',
        address: '',
        description: '',
        rules: '',
        numOfPeople: 1,
        cancelationFee: 0
    }

    let form;

    if (userType === "RESORT_RENTER")
        form = <ResortForm resort={resort} buttonText="Create resort" id={-1} />
    else if (userType === "BOAT_RENTER")
        form = <ResortForm resort={resort} buttonText="Create resort" id={-1} />
    else
        form = <ResortForm resort={resort} buttonText="Create resort" id={-1} />

    return (<>
        {form}
    </>
    );
}