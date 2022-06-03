import React from 'react'
import {useState, useEffect} from 'react';
import { getLogged } from '../../services/api/LoginApi';
import '../../assets/styles/style.css';
import ProfilePreview from './ProfilePreview';

export default function MyProfile(){
    
    const [user, setUser] = useState([]);

    useEffect(() => {
        async function fetchUser(){
            await getLogged(setUser);
        }
        fetchUser();
    }, [])

    if(!!user) {
        return <ProfilePreview user={user} />
    }
    else 
        return null;
}