import { Form, Row, Col, Button, Container} from 'react-bootstrap';
import {LabeledInputWithErrMessage} from './LabeledInput';
import '../../assets/styles/form.css';
import {useState, useEffect, useRef} from 'react';
import { faTrashCan} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import FeedbackPopUp from  './FeedbackPopUp'
import { updateClient, getClientByID } from '../../services/api/ClientApi';
import {getInstructorByID, updateInstructor} from '../../services/api/InstructorApi'
import { onlyLetters, onlyNumbers, checkLettersInput, checkNumInput, capitalizeString } from '../../services/utils/InputValidation';


export default function UpdateClientProfile({id}){
    const [instructor, setInstructor] = useState();

    const [feedbackType, setFeedbackType] = useState(true);
    const [feedbackMessage, setFeedbackMessage] = useState('');
    const [feedbackShow, setFeedbackShow] = useState(false);

    const firstUpdate = useRef(true);

    useEffect(() => {
        async function fetchInstructor(){
            const requestData = await getInstructorByID(id);
            setInstructor(!!requestData ? requestData.data : {});
            return requestData;
        }
        fetchInstructor();
    }, [])

    useEffect(() => {
        if (firstUpdate.current) {
            firstUpdate.current = false;
            return;
          }
          if(feedbackMessage!=''){
            setFeedbackShow(true);
          }
        
    }, [feedbackType, feedbackMessage])

    function setFeedbackPopup(isError, message){
        setFeedbackMessage(message);
        setFeedbackType(isError);
    }
    
    const profilePic = require('../../assets/images/blue_profile_pic.jpg')  // TODO: real data
    if(!!instructor){
        return (<>
                <FeedbackPopUp changeToShow={feedbackShow} isError={feedbackType} message={feedbackMessage} resetShow={setFeedbackShow}/> 
                <Container >  
                    <Row className='mt-5' >  
                        <Col sm={20} >
                            {/*Popup try was here*/}
                            <div className="borderedBlock">    
                                <Col sm={true} >
                                    <Form>
                                        {/* Profile pic row*/}
                                        <Col sm={15} align='center'>
                                            <img src={profilePic} className="profilePicture rounded-circle" ></img>
                                        </Col>
                                        <Row className='mt-3'>  </Row>
                                        <InputElems client={instructor} feedbackFun={setFeedbackPopup}/>
                                    </Form>
                                </Col>
                            </div>
                            
                        </Col>
                    </Row>  
                    <Row className='mt-4'>  </Row>
                    
                     <DeleteRow client={instructor}/>         
                </Container >
            </>
        );
    }   
}

function DeleteRow({client}){
    return <Row className='mt-3'>
                <Col sm={15} align='center'>
                <Button variant="custom" type="submit" className='formButton' onClick={() => {createDeletionRequest({client})}} >
                        <FontAwesomeIcon icon={faTrashCan}/>
                        Delete My Profile 
                </Button>
                </Col>
            </Row>
}

function prepareForUpdate(client, clientData, feedbackFunc){
    let clientCopy = {...client};
    
    // capitalizeString ??
    clientCopy.firstName = clientData.firstName || client.firstName;
    clientCopy.lastName = clientData.lastName || client.lastName;
    clientCopy.address = clientData.address || client.address;
    clientCopy.city = clientData.city || client.city;
    clientCopy.state = clientData.state || client.state;
    clientCopy.phoneNum = clientData.phoneNum || client.phoneNum;

    const feedback = updateInstructor(clientCopy);
    
    // TODO: USE STATE NEKI NESTO?
    !!feedback ? feedbackFunc(false, 'Successfuly updated profile!') : 
                 feedbackFunc(true, 'Oops, something went wrong please try again!');
};


function refreshPage() {
    window.location.reload(false);
}

function createDeletionRequest(){

}


function InputElems({client, feedbackFun}){

    // Values
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [address, setAddress] = useState('');
    const [city, setCity] = useState('');
    const [state, setState] = useState('');
    const [phoneNum, setPhoneNum] = useState('');

    let clientDataValues = {firstName, lastName, address, city, state, phoneNum};

    // Validations
    const [firstNameValid, setFirstNameValid] = useState(true);
    const [lastNameValid, setLastNameValid] = useState(true);
    const [addressValid, setAddressValid] = useState(true);
    const [cityValid, setCityValid] = useState(true);
    const [stateValid, setStateValid] = useState(true);
    const [phoneNumValid, setPhoneNumValid] = useState(true);

    const [formValid, setFormValid] = useState(true);
    
    const validateFirstName = (value) => {
        setFirstNameValid(checkLettersInput(value));
        setFirstName(value);
     };
     const validateLastName = (value) => {
        setLastNameValid(checkLettersInput(value));
        setLastName(value);
     };
     const validateAddress = (value) => {
        setAddressValid(checkLettersInput(value));
        setAddress(value);
     };
     const validateCity = (value) => {
        setCityValid(checkLettersInput(value));
        setCity(value);
     };
     const validateState = (value) => {
        setStateValid(checkLettersInput(value));
        setState(value);
     };
     const validatePhoneNum = (value) => {
        setPhoneNumValid(checkNumInput(value));
        setPhoneNum(value);
     };

     useEffect(() => {
        validateForm()
      }, [firstNameValid, lastNameValid, addressValid, cityValid, stateValid, phoneNumValid]);

     const validateForm = () => {
        if(firstNameValid && lastNameValid && addressValid && cityValid && stateValid && phoneNumValid)
            { setFormValid(true); }
        else
            { setFormValid(false); }
     }

     // JSX
    return <div>
                <LabeledInputWithErrMessage isValid={firstNameValid} label="First Name" inputName="firstName" defaultValue={client.firstName} required onChangeFunc={validateFirstName} hoverTitile={onlyLetters}/>
                <LabeledInputWithErrMessage isValid={lastNameValid} label="Last Name" inputName="lastName" defaultValue={client.lastName} required onChangeFunc={validateLastName} hoverTitile={onlyLetters}/>
                <LabeledInputWithErrMessage isValid={addressValid} label="Address" inputName="address" defaultValue={client.address} required onChangeFunc={validateAddress} hoverTitile={onlyLetters}/>
                <LabeledInputWithErrMessage isValid={cityValid} label="City" inputName="city" defaultValue={client.city} required onChangeFunc={validateCity} hoverTitile={onlyLetters}/>
                <LabeledInputWithErrMessage isValid={stateValid} label="State" inputName="state" defaultValue={client.state} required onChangeFunc={validateState} hoverTitile={onlyLetters}/>
                <LabeledInputWithErrMessage isValid={phoneNumValid} label="Phone number" inputName="phoneNum" defaultValue={client.phoneNum} required onChangeFunc={validatePhoneNum} hoverTitile={onlyNumbers}/>
                
                <Row className='mt-5'>  </Row>
                
                <Row className='mt-2'>
                    <Col sm={4}/>
                    <Col sm={2} align='left'>
                        <Button variant="custom" disabled={!formValid} type="submit" className='formButton' onClick={() => prepareForUpdate(client, clientDataValues, feedbackFun)} >
                            Save Changes
                        </Button>
                    </Col>
                    <Col sm={2} align='right'>
                        <Button variant="custom" type="reset" className='formButton' onClick={refreshPage}>
                            Cancel Changes
                        </Button>
                    </Col>
                    <Col sm={4}/>
                </Row>
            </div>
}