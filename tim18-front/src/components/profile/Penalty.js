import React from 'react'
import { faXmarkCircle} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

export default function Penalty({isColored}){
    return <FontAwesomeIcon icon={faXmarkCircle} className={'pb-3 ' + isColored} ></FontAwesomeIcon>
}