import React from 'react';
import '../../assets/styles/buttons.css';
import { Button } from 'react-bootstrap';

export default function RegularButton({text, onClickFunction}){

    return <Button variant="custom" className='sameWidthButton formButton pe-5 ps-5 mt-2' onClick={onClickFunction}>
                {text}
            </Button>
}

