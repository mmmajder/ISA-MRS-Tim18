import React from 'react';
import '../../assets/styles/buttons.css';
import { Button } from 'react-bootstrap';

export default function FixedWidthRegButton({text, onClickFunction, href}){

    return <Button href={href} variant="custom" className='fixedWidthButton formButton pe-5 ps-5 mt-2' onClick={onClickFunction}>
                {text}
            </Button>
}

