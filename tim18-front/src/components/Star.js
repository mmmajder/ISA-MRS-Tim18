import React from 'react';
import { faStar} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

export default function Star({isColored}){
    return <FontAwesomeIcon icon={faStar} className={'pb-3 ' + isColored} ></FontAwesomeIcon>
}

