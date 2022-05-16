import  React,  { useState, useContext, useEffect, useRef} from 'react';
import { BoldLink, BoxContainer, FormContainer, Input, MutedLink, SubmitButton } from "./common";
import { Marginer } from "./marginer";
import { AccountContext } from "./AccountContext";
import { loginRequest, getLogged } from "../../../services/api/LoginApi"
import {setToken,setRole} from "../../../services/AuthService/AuthService"
import { useNavigate } from 'react-router';
import { Button } from 'react-bootstrap';
import { checkLettersInput, onlyLetters } from '../../../services/utils/InputValidation';
import { LabeledInputWithErrMessage } from '../LabeledInput';

export function LoginForm({handleLogin}) {
  const { switchToSignup } = useContext(AccountContext);
  const [inputs, setInputs] = useState({});
  const [validations, setValidations] = useState({email: true, password: true});

  const handleChange = (event) => {
    const name = event.target.name;
    var value = event.target.value;
    setInputs(values => ({...values, [name]: value}))
  }

  const navigate = useNavigate();
  const getUserCallback = (user) => {
    console.log("user")
    console.log(user)
    if(!user){
      // error message popup
      handleLogin({});
      setRole();
    }
    else
    {
      setRole(user.userType);
      handleLogin(user.userType);
      navigate('/home')
    }
  }
  const loginCallback = (returnData) => {
    if(!returnData){
      // error message popup
    }
    else
    {
      setToken(returnData);
      getLogged(getUserCallback);
    }
  }
  /*
    <LabeledInputWithErrMessage isValid={validations.email} label="Email*" inputName="email" required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
    <LabeledInputWithErrMessage isValid={validations.password}label="Password*" inputName="password" required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
    
    */
  
  return(
      <BoxContainer>
        <FormContainer>
          <Marginer direction="vertical" margin="5em" />
          <Input type="email" name="username" placeholder="Email" onChange={(e) => handleChange(e)} />
          <Input type="password" name="password" placeholder="Password" onChange={(e) => handleChange(e)}/>

        </FormContainer>
        <Marginer direction="vertical" margin={10} />
        <MutedLink href="#">Forgot your password?</MutedLink>
        <Marginer direction="vertical" margin="7.5em" />
        
        <Button variant="custom" type="submit" className="formButton" onClick={() => {loginRequest(inputs, loginCallback);}} > Sign in  </Button>

        <Marginer direction="vertical" margin="2em" />
        <MutedLink href="#">
          Don't have an account?{" "}
          <BoldLink href="#" onClick={switchToSignup}>
            Sign up
          </BoldLink>
        </MutedLink>
        
      </BoxContainer>
  );
}

/*import React, { useContext } from "react";
import { Marginer } from "../marginer";
import { AccountContext } from "./accountContext";
import '../../../assets/styles/login.css'
import { Form, Button} from 'react-bootstrap';

export default function LoginForm() {
  const { switchToSignup } = useContext(AccountContext);

  return (
    <div className="BoxContainer">
        <Form className="FormContainer">
            <Form.Control className="InputStyle" type="email" placeholder="Email" />
            <Form.Control className="InputStyle" type="password" placeholder="Password" />
        </Form>

        <Marginer direction="vertical" margin={10} />
        <a className="MutedLink" href="#">Forget your password?</a>
        <Marginer direction="vertical" margin="1.6em" />
        <Button className="SubmitButton" type="submit">Signin</Button>
        <Marginer direction="vertical" margin="1em" />
        
        <a className="MutedLink" href="#">
            Don't have an accoun?{" "}
            <a className="BoldLink" href="#" onClick={switchToSignup}>
                Sign up
            </a>
        </a>
    </div>
  );
}*/