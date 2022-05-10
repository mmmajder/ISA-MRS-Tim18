import {useState, useEffect, useRef} from 'react';
import { onlyLetters, onlyNumbers, checkLettersInput, checkNumInput, repairInput } from '../../../services/utils/InputValidation';
import React, { useContext } from "react";
import {
  BoldLink,
  BoxContainer,
  FormContainer,
  Input,
  MutedLink,
  SubmitButton,
} from "./common";
import { Marginer } from "./marginer";
import { AccountContext } from "./AccountContext";
import { LabeledInputWithErrMessage } from "../LabeledInput";
import { Form, Button } from 'react-bootstrap';
import { sendRegistrationRequest } from '../../../services/api/LoginApi';
import { useNavigate } from 'react-router';
import FeedbackPopUp from '../PopUps/FeedbackPopUp';

export function SignupForm(props) {
  const navigate = useNavigate();
  const { switchToSignin } = useContext(AccountContext);
  const [inputs, setInputs] = useState({});
  const [validations, setValidations] = useState({firstName: true, 
                                                  lastName: true, 
                                                  address: true, 
                                                  city: true, 
                                                  state: true, 
                                                  phoneNum: true,
                                                  email: true,
                                                  password: true,
                                                  userType: true});

  const handleChange = (event, validationFunc) => {
      const name = event.target.name;
      var value = event.target.value;
      console.log(name)
      console.log(value)
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
                  validations.phoneNum &&
                  validations.email &&
                  validations.password);
  }, [validations]);
  

  // Feedback from request to back, to create popUp
  const [feedback, setFeedback] = useState('');
  const [feedbackType, setFeedbackType] = useState(true);
  const [feedbackMessage, setFeedbackMessage] = useState('');
  const [feedbackShow, setFeedbackShow] = useState(false);

  const firstUpdate = useRef(true);

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

  useEffect(() => {
    if (firstUpdate.current) {
        firstUpdate.current = false;
        return;
      }
    if(!!feedback){
        navigate('/welcome/', { replace: false })
        setFeedbackPopup(false, 'Successfuly created request for profile, Please check your email!'); 
        /*setCurrentClient(feedback);
        setOriginalClient(feedback);*/
    }
    else{ setFeedbackPopup(true, 'Oops, something went wrong please try again!'); }

  }, [feedback])

  let userTypes = [
    { id: 'Client', name: 'Client'},
    { id: 'BoatRenter', name: 'Boat Renter'},
    { id: 'ResortRenter', name: 'Resort Renter' },
    { id: 'FishingInstructor', name: 'Fishing Instructor' },
  ];


  return (
    <>
    <BoxContainer>
      
      <FormContainer>

        <Form.Select style={{'text-align': "center"}}aria-label="Default select example" name="userType" id="assets"  onChange={(e)=>{handleChange(e, checkLettersInput);}}>
          { userTypes.map((userType) => <option key={userType.id} value={userType.id}>{userType.name}</option>) }
        </Form.Select>
        <LabeledInputWithErrMessage isValid={validations.email} label="Email*" inputName="email" required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
        <LabeledInputWithErrMessage isValid={validations.password} label="Password*" inputName="password" required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
        <LabeledInputWithErrMessage isValid={true} label="Retype password*" inputName="retypedPassword" required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
        <LabeledInputWithErrMessage isValid={validations.firstName} label="First Name*" inputName="firstName" required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
        <LabeledInputWithErrMessage isValid={validations.lastName} label="Last Name*" inputName="lastName"required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
        <LabeledInputWithErrMessage isValid={validations.address} label="Address*" inputName="address"  required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
        <LabeledInputWithErrMessage isValid={validations.city} label="City*" inputName="city" required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
        <LabeledInputWithErrMessage isValid={validations.state} label="State*" inputName="state"  required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
        <LabeledInputWithErrMessage isValid={validations.phoneNum} label="Phone number*" inputName="phoneNum"  required onChangeFunc={handleChange} validationFunc={checkNumInput} hoverTitile={onlyNumbers}/>
            
      </FormContainer>
      <Marginer direction="vertical" margin="2em" />
      <SubmitButton type="submit" disabled={!formValid}  onClick={(e) => prepareForUpdate(inputs, setFeedback, e)}>Sign up</SubmitButton>
      <Marginer direction="vertical" margin="1em" />
      <MutedLink href="#">
        Already have an account?
        <BoldLink href="#" onClick={switchToSignin}>
          Sign in
        </BoldLink>
      </MutedLink>
    </BoxContainer>
    
    </>
  );
}
const prepareForUpdate = (data, setFeedback, e) =>{
  sendRegistrationRequest(setFeedback, data);
};