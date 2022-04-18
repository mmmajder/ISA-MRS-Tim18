import React from 'react';
import ResortForm from './ResortForm';


export default function UpdateResortForm(){
    // const resort = {
    //     name: 'Maldivian hut on water',
    //     address: 'Orchid Magu 7, Maadhad, 57887, Maldives',
    // }

    const resort = {
        name: 'Maldivian hut on water',
        address: 'Orchid Magu 7, Maadhad, 57887, Maldives',
        description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi eget nulla congue sapien interdum pulvinar. Quisque a nisi in ex sollicitudin eleifend. Aliquam rutrum erat mauris, sed pulvinar sem tempor at. Cras nec auctor mi. Nam nibh leo, imperdiet et dictum nec, vulputate eget felis. Integer eleifend maximus ligula nec.',
        rules: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi eget nulla congue sapien interdum pulvinar. Quisque a nisi in ex sollicitudin eleifend. Aliquam rutrum erat mauris, sed pulvinar sem tempor at. Cras nec auctor mi. Nam nibh leo, imperdiet et dictum nec, vulputate eget felis. Integer eleifend maximus ligula nec.',
        numOfPeople: 4,
        cancelationFee: 40,
        id: 2
    }

    return (<>
        <ResortForm resort={resort} buttonText="Update resort" id={resort.id}/>
    </>
    );
}