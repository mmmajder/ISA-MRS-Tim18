import React from 'react';
import ResortForm from './ResortForm';
import BoatForm from './BoatForm';
import AdventureForm from './AdventureForm';


export default function CreateForm({userType}){
    // const resort = {
    //     name: 'Maldivian hut on water',
    //     address: 'Orchid Magu 7, Maadhad, 57887, Maldives',
    // }

    const asset = {
        name: '',
        address: '',
        description: '',
        rules: '',
        numOfPeople: 1,
        cancelationFee: 0
    }

    let form;

    console.log("moj user type je " + userType);

    if (userType === "ResortRenter")
        form = <ResortForm resort={asset} buttonText="Create resort" id={-1} />
    else if (userType === "BoatRenter")
        form = <BoatForm boat={asset} buttonText="Create boat" id={-1} />
    else if (userType === "FishingInstructor")
        form = <AdventureForm adventure={asset} buttonText="Create adventure" id={-1} />
    else
        form = "huga buga";

    return (<>
        {form}
    </>
    );
}