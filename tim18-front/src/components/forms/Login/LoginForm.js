import  React,  { useState, useContext, useEffect, useRef} from 'react';
import { BoldLink, BoxContainer, FormContainer, Input, MutedLink, SubmitButton } from "./common";
import { Marginer } from "./marginer";
import { AccountContext } from "./AccountContext";
import { loginRequest, getLogged } from "../../../services/api/LoginApi"
import {setToken,setRole} from "../../../services/AuthService/AuthService"
import { useNavigate } from 'react-router';



export function LoginForm({handleLogin}) {
  const { switchToSignup } = useContext(AccountContext);
  const [inputs, setInputs] = useState({});

  const handleChange = (event) => {
    const name = event.target.name;
    var value = event.target.value;
    setInputs(values => ({...values, [name]: value}))
  }

  const navigate = useNavigate();
  const getUserCallback = (user) => {
    if(!user){
      // error message popup
      handleLogin({});
      setRole();
    }
    else
    {
      console.log(user);
      setRole(user.userType);
      handleLogin(user.userType);
      navigate('/welcome/', { replace: false })
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
  
/*<LabeledInputWithErrMessage isValid={validations.firstName} label="First Name" inputName="firstName" defaultValue={client.firstName} required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
<LabeledInputWithErrMessage isValid={validations.lastName} label="Last Name" inputName="lastName" defaultValue={client.lastName} required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>*/
  
  return(
      <BoxContainer>
        <FormContainer>
          <Input type="email" name="username" placeholder="Email" onChange={(e) => handleChange(e)} />
          <Input type="password" name="password" placeholder="Password" onChange={(e) => handleChange(e)}/>
        </FormContainer>
        <Marginer direction="vertical" margin={10} />
        <MutedLink href="#">Forgot your password?</MutedLink>
        <Marginer direction="vertical" margin="7.5em" />
        <SubmitButton type="submit" onClick={() => {loginRequest(inputs, loginCallback);}}>
          Sign in
        </SubmitButton>
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