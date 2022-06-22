import React, { useEffect, useState } from 'react'
import { getLogged } from '../../services/api/LoginApi';
import { getRole } from '../../services/AuthService/AuthService';
import AdminSetPasswordModal from '../admin/AdminSetPasswordModal';
import {Container} from 'react-bootstrap'
import AssetsPreview from '../asset/AssetsPreview';
import { getApiCall } from '../../services/Configs';
import { findById } from '../../services/api/AdminApi';

const Home = () => {
    const [user, setUser] = useState()
    const [activeForm, setActiveForm] = useState(null);
    const [admin, setAdmin] = useState();

    useEffect(() => {
        async function fetchUser(){
            await getLogged(setUser);
        }
        fetchUser();
    }, [])

    useEffect(() => {
        if (!!user && getRole()=="Admin") {
            findById(user.id).then((response) => {
                console.log(response)
                setAdmin(response.data);
            })
        }
    }, [user])

    useEffect(() => {
        if (!!admin) {
            if (!admin.alreadyLogged && activeForm==null) {
                setActiveForm(<AdminSetPasswordModal user={admin}/>)
            }
        }
    }, [admin])

    if (!!user) {
        if(getRole() === "Admin"){
            return (
                <>
                    <Container><AssetsPreview isSearch={true}/></Container>
                    {activeForm}
                </>
            )
        }
        else{
            return  <Container><AssetsPreview isSearch={true}/></Container>
        }
    }
    else if(getRole() === 'Guest'){
        return <Container><AssetsPreview isSearch={true}/></Container>
    }
    
}

export default Home