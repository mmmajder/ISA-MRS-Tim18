import React, { useEffect, useState } from 'react'
import { getLogged } from '../../services/api/LoginApi';
import { getRole } from '../../services/AuthService/AuthService';
import AdminSetPasswordModal from '../admin/AdminSetPasswordModal';

const Home = () => {
    const [user, setUser] = useState()
    const [activeForm, setActiveForm] = useState(null);

    useEffect(() => {
        async function fetchUser(){
            await getLogged(setUser);
        }
        fetchUser();
    }, [])

    const FirstTimeAdmin = () => {
        if (getRole()=="Admin") {
            if (!user.alreadyLogged && activeForm==null) {
                setActiveForm(<AdminSetPasswordModal user={user}/>)
            }
        }
        return "Bravo";
    }

    if (user) {
        FirstTimeAdmin()
        return (
            <>
                {activeForm}
            </>
        )
    }
    
}

export default Home