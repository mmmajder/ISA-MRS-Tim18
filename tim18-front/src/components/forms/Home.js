import React, { useEffect, useState } from 'react'
import { getLogged } from '../../services/api/LoginApi';
import { getRole } from '../../services/AuthService/AuthService';
import AdminSetPasswordModal from '../admin/AdminSetPasswordModal';
import {Container} from 'react-bootstrap'
import AssetsPreview from '../asset/AssetsPreview';
import { getApiCall } from '../../services/Configs';

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

    if (!!user) {
        if(getRole() === "Admin"){
            FirstTimeAdmin()
            return (
                <>
                    <Container><AssetsPreview isSearch={true}/></Container>
                    {activeForm}
                </>
            )
        }
    }
    else if(getRole() === 'Guest'){
        return <Container><AssetsPreview isSearch={true}/></Container>
    }
    
}

export default Home