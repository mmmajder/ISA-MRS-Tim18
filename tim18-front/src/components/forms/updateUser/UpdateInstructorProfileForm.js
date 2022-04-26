import { Form, Row, Col, Button, Container} from 'react-bootstrap';
import {LabeledInputWithErrMessage} from '../LabeledInput';
import '../../../assets/styles/form.css'
import {useState, useEffect, useRef} from 'react';
import { faTrashCan} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import FeedbackPopUp from  '../PopUps/FeedbackPopUp'
import { updateInstructor, getInstructorByID, createDeleteRequest} from '../../../services/api/InstructorApi';
import { onlyLetters, onlyNumbers, checkLettersInput, checkNumInput, repairInput } from '../../../services/utils/InputValidation';
import DeleteRequest from '../PopUps/DeleteRequest';
import {getDeleteRequestByID} from '../../../services/api/DeleteRequestApi';
import DeletionRequestStatus from '../PopUps/DeletionRequestStatus'

export default function UpdateInstructorProfile({id}){
    const [instructor, setInstructor] = useState();
    const [deletionRequestExists, setDeletionRequest] = useState(false);

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

        async function fetchDeleteRequest(){
            const requestData = await getDeleteRequestByID(id);
            setDeletionRequest(!!requestData ? true : false);
        }
        fetchDeleteRequest();
    }, [])

    useEffect(() => {
        if (firstUpdate.current) {
            firstUpdate.current = false;
            return;
          }
          if(feedbackMessage!==''){
            setFeedbackShow(true);
          }
        
    }, [feedbackType, feedbackMessage])

    function setFeedbackPopup(isError, message){
        setFeedbackMessage(message);
        setFeedbackType(isError);
    }

    function reset(){
        setFeedbackShow(false);
        setFeedbackMessage('');
    }
    
    const profilePic = require('../../../assets/images/blue_profile_pic.jpg')  // TODO: real data
    
    const pendingRequest = deletionRequestExists ? <DeletionRequestStatus message={"Probas"} isError={true}/> : <></>
    const deleteButton = deletionRequestExists ? <></> :  <DeleteRow id={id} feedbackFunc={setFeedbackPopup} deleteNotifFunc={setDeletionRequest}/>
    if(!!instructor){
        return (<>
                <FeedbackPopUp changeToShow={feedbackShow} isError={feedbackType} message={feedbackMessage} resetData={reset}/> 
                 {pendingRequest}
                <Container >  
                    <Row className='mt-5' >  
                        <Col sm={20} >
                            <div className="borderedBlock">    
                                <Col sm={true} >
                 
                                    {/* Profile pic row*/}
                                    <Col sm={15} align='center'>
                                        <img src={profilePic} className="profilePicture rounded-circle" ></img>
                                    </Col>
                                    <Form>
                                        <Row className='mt-3'>  </Row>
                                        <InputElems instructor={instructor} feedbackFun={setFeedbackPopup}/>
                                    </Form>
                                </Col>
                            </div>
                            
                        </Col>
                    </Row>  
                    <Row className='mt-4'>  </Row>
                    {deleteButton}
                   
                </Container >
            </>
        );
    }   
}

function DeleteRow({id, feedbackFunc, deleteNotifFunc}){
    const [popUp, setPopUp] = useState(false);
    // adds class to darken background color
    const duringPopUp = popUp ? " during-popup" : "";
    const [reason, setReason] = useState('');
    const [feedback, setFeedback] = useState('');

    const firstUpdate = useRef(true);

    useEffect(() => {
        if (reason==='') {
            return;
          }
        createDeleteRequest(id, reason, setFeedback);
    }, [reason])

    useEffect(() => {
        if (firstUpdate.current) {
            firstUpdate.current = false;
            return;
          }
        if(!!feedback)
         { feedbackFunc(false, 'Successfuly created delete requeset for your profile!');
            deleteNotifFunc(true);
         }
         else{
            feedbackFunc(true, 'Oops, something went wrong please try again!');
         }
                     
    }, [feedback])

    return  <>
                <Row className='mt-3'>
                    <Col sm={15} align='center'>
                    <Button variant="custom" type="submit" className={"formButton" + duringPopUp} onClick={()=>{setPopUp(true);}} >
                            <FontAwesomeIcon icon={faTrashCan}/>
                            Delete My Profile 
                    </Button>
                    </Col>
                </Row>
                {popUp && <DeleteRequest setPopUp={setPopUp} PropFunc={setReason}/>}
            </>
}


function InputElems({instructor, feedbackFun}){

    const [currentInstructor, setCurrentInstructor] = useState(instructor);
    const [originalInstructor, setOriginalInstructor] = useState(instructor);

    const [inputs, setInputs] = useState({});
    const [validations, setValidations] = useState({firstName: true, 
                                                     lastName: true, 
                                                     address: true, 
                                                     city: true, 
                                                     state: true, 
                                                     phoneNum: true});

    const handleChange = (event, validationFunc) => {
        const name = event.target.name;
        var value = event.target.value;
        const valid = validationFunc(value)
        setInputs(values => ({...values, [name]: value}))
        setValidations(values => ({...values, [name]: valid}))
      }

     // Form validation for button disable
     const [formValid, setFormValid] = useState(true);
     
     useEffect(() => {
        setFormValid(validations.firstName && 
                     validations.lastName && 
                     validations.address && 
                     validations.city && 
                     validations.state && 
                     validations.phoneNum);
      }, [validations]);
      

     // Feedback from request to back, to create popUp
     const [feedback, setFeedback] = useState('');
     const firstUpdate = useRef(true);

      useEffect(() => {
        if (firstUpdate.current) {
            firstUpdate.current = false;
            return;
          }
        console.log("feedback ", feedback)
        if(!!feedback){
            feedbackFun(false, 'Successfuly updated profile!'); 
            setCurrentInstructor(feedback);
            setOriginalInstructor(feedback);
        }
        else{ feedbackFun(true, 'Oops, something went wrong please try again!'); }

    }, [feedback])

    function refreshData(){
        setCurrentInstructor(originalInstructor);
    }
     // JSX
    return <div>
               <AllLabeledInputs validations={validations} instructor={currentInstructor} handleChange={handleChange}/>
                
                <Row className='mt-5'>  </Row>
                
                <Row className='mt-2'>
                    <Col sm={4} align='left'>
                    
                    </Col>
                    
                    <Col sm={2} align='left'>
                        <Button variant="custom" disabled={!formValid} type="submit" className='formButton' onClick={(e) => prepareForUpdate(instructor, inputs, setFeedback, e)} >
                            Save Changes
                        </Button>
                    </Col>
                    <Col sm={2} align='right'>
                        <Button variant="custom" type="reset" className="formButton" onClick={refreshData}>
                            Cancel Changes
                        </Button>
                    </Col>
                    <Col sm={4}/>
                </Row>
            </div>
}

const prepareForUpdate = (instructor, instructorData, setFeedback, e) =>{
    let instructorCopy = {...instructor};
    
    instructorCopy.firstName = instructorData.firstName || instructor.firstName;
    instructorCopy.lastName = instructorData.lastName || instructor.lastName;
    instructorCopy.address = instructorData.address || instructor.address;
    instructorCopy.city = instructorData.city || instructor.city;
    instructorCopy.state = instructorData.state || instructor.state;
    instructorCopy.phoneNum = instructorData.phoneNum || instructor.phoneNum;
    updateInstructor(instructorCopy, setFeedback);
    e.preventDefault();
};


function AllLabeledInputs({validations, instructor, handleChange}){
    return <>
    <LabeledInputWithErrMessage isValid={validations.firstName} label="First Name" inputName="firstName" defaultValue={instructor.firstName} required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
    <LabeledInputWithErrMessage isValid={validations.lastName} label="Last Name" inputName="lastName" defaultValue={instructor.lastName} required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
    <LabeledInputWithErrMessage isValid={validations.address} label="Address" inputName="address" defaultValue={instructor.address} required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
    <LabeledInputWithErrMessage isValid={validations.city} label="City" inputName="city" defaultValue={instructor.city} required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
    <LabeledInputWithErrMessage isValid={validations.state} label="State" inputName="state" defaultValue={instructor.state} required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
    <LabeledInputWithErrMessage isValid={validations.phoneNum} label="Phone number" inputName="phoneNum" defaultValue={instructor.phoneNum} required onChangeFunc={handleChange} validationFunc={checkNumInput} hoverTitile={onlyNumbers}/>
    </>
}