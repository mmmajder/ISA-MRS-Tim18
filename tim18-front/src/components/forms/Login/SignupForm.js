import {useState, useEffect, useRef} from 'react';
import { onlyLetters, onlyNumbers, checkLettersInput, checkNumInput, repairInput, checkEmailInput, checkPasswordInput, checkSecondPasswordInput } from '../../../services/utils/InputValidation';
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

export function SignupForm({feedbackFunc}) {
  const navigate = useNavigate();
  const [feedback, setFeedback] = useState('');
  const firstUpdate = useRef(true);
  const { switchToSignin } = useContext(AccountContext);
  const [inputs, setInputs] = useState({userType: 'Client'});
  // Form validation for button disable
  const [formValid, setFormValid] = useState(true);
  let userTypes = [
    { id: 'Client', name: 'Client'},
    { id: 'BoatRenter', name: 'Boat Renter'},
    { id: 'ResortRenter', name: 'Resort Renter' },
    { id: 'FishingInstructor', name: 'Fishing Instructor' },
  ];
  const [validations, setValidations] = useState({firstName: true, lastName: true, address: true, city: true, state: true, 
                                                  phoneNum: true, email: true, retypedPassword: true, password: true, userType: true});

  const handleChange = (event, validationFunc) => {
      const name = event.target.name;
      var value = event.target.value;
      let valid;
      if(name==='retypedPassword'){
        valid = checkSecondPasswordInput(value, inputs.password);
      }
      else{
        valid = validationFunc(value);
      }
      setInputs(values => ({...values, [name]: value}))
      setValidations(values => ({...values, [name]: valid}))
    }

  useEffect(() => {
    setFormValid(validations.firstName && 
                  validations.lastName && 
                  validations.address && 
                  validations.city && 
                  validations.state && 
                  validations.phoneNum &&
                  validations.email &&
                  validations.retypedPassword &&
                  validations.password);
  }, [validations]);
  
  useEffect(() => {
    if (firstUpdate.current) {
        firstUpdate.current = false;
        return;
      }
    if(!!feedback){
        console.log(feedback);
        alert('Successfuly created request for profile, Please check your email!');
        //feedbackFunc(false, 'Successfuly created request for profile, Please check your email!'); 
    }
    else{ 
      alert('Oops, something went wrong please try again! Possible that this email already exists');
     }

  }, [feedback])

  
  return (
    <>
    <BoxContainer>
      
      <FormContainer>

        <Form.Select style={{textAlign: "center"}}aria-label="Default select example" name="userType" id="assets"  onChange={(e)=>{handleChange(e, checkLettersInput);}}>
          { userTypes.map((userType) => <option key={userType.id} value={userType.id}>{userType.name}</option>) }
        </Form.Select>
        <LabeledInputWithErrMessage isValid={validations.email} label="Email*" inputName="email" required onChangeFunc={handleChange} validationFunc={checkEmailInput} hoverTitile={onlyLetters}/>
        <LabeledInputWithErrMessage isValid={validations.password} label="Password*" inputName="password" required onChangeFunc={handleChange} validationFunc={checkPasswordInput} hoverTitile={onlyLetters}/>
        <LabeledInputWithErrMessage isValid={validations.retypedPassword} label="Retype password*" inputName="retypedPassword" required onChangeFunc={handleChange} validationFunc={checkSecondPasswordInput} hoverTitile={onlyLetters}/>
        <LabeledInputWithErrMessage isValid={validations.firstName} label="First Name*" inputName="firstName" required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
        <LabeledInputWithErrMessage isValid={validations.lastName} label="Last Name*" inputName="lastName"required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
        <LabeledInputWithErrMessage isValid={validations.address} label="Address*" inputName="address"  required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
        <LabeledInputWithErrMessage isValid={validations.city} label="City*" inputName="city" required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
        <LabeledInputWithErrMessage isValid={validations.state} label="State*" inputName="state"  required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
        <LabeledInputWithErrMessage isValid={validations.phoneNum} label="Phone number*" inputName="phoneNum"  required onChangeFunc={handleChange} validationFunc={checkNumInput} hoverTitile={onlyNumbers}/>
            
      </FormContainer>
      <Marginer direction="vertical" margin="2em" />
      <Button variant="custom" type="submit" className="formButton" onClick={(e) => sendRegistrationRequest(setFeedback, inputs)} > Sign up  </Button>
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