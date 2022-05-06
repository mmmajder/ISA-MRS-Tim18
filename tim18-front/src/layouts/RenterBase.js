import ResortRenterNavbar from './navbar/RessortRenterNavbar';
import CreateResortForm from '../components/forms/CreateResortForm'
import {Container} from 'react-bootstrap'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import ResortDetailedView from '../components/asset/ResortDetailedView.js';
import ProfilePreview from '../components/profile/ProfilePreview.js';
import ProfileInfoBlock from '../components/profile/ProfileInfoBlock.js';
import Calendar from '../components/calendar/Calendar.js';
import UpdateInstructorProfileForm from '../components/forms/UpdateInstructorProfileForm.js';

export default function RenterBase(){

    const instructor = true;
    const resortForm = <CreateResortForm />
    localStorage.setItem("userId", 2)
    const profile = <ProfilePreview profileComponent={<ProfileInfoBlock id={localStorage.getItem("userId")}/>}/>
    const resortView = <ResortDetailedView />
    const calendar = <Calendar/>
    let updateProfile;
    if (instructor){
    updateProfile = <UpdateInstructorProfileForm id={localStorage.getItem("userId")}/>
    }
    return (
    <>
        <div>
        <body>
            {/* <ResortRenterNavbar/> */}
            <ResortRenterNavbar/>
            <Container>
            <Routes>
                {/* Creating/Registrating Resorts/Boats */}
                <Route path="/createResort" element={resortForm} /> 
                {/* For other's Profile page */}
                <Route path="/profile" element={profile} /> 
                <Route path="/resorts" element={resortView} /> 
                <Route path="/calendar" element={calendar}/>
                <Route path="/settings" element={updateProfile} />
            </Routes>
            </Container>
        </body>
        </div>
    </>
    );
}