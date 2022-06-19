import  React,  { useState, useContext, useEffect, useRef} from 'react';
import { BoldLink, BoxContainer, FormContainer, Input, MutedLink, SubmitButton } from "./common";
import { Marginer } from "./marginer";
import { AccountContext } from "./AccountContext";
import { loginRequest, getLogged } from "../../../services/api/LoginApi"
import {setToken,setRole, setUsername, getToken} from "../../../services/AuthService/AuthService"
import { useNavigate } from 'react-router';
import { Button } from 'react-bootstrap';
import { checkLettersInput, onlyLetters } from '../../../services/utils/InputValidation';
import { LabeledInputWithErrMessage } from '../LabeledInput';
import '../../../assets/styles/login.css';
import { Form, Row, Col} from 'react-bootstrap';
import { getApiCall } from '../../../services/Configs';

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
  // const getUserCallback = (user) => {
  //   console.log("user")
  //   console.log(user)
  //   if(!user){
  //     // error message popup
  //     handleLogin({});
  //     setRole();
  //   }
  //   else
  //   {
  //     setRole(user.userType);
  //     handleLogin(user.userType);
  //     navigate('/home')
  //   }
  // }

  const loginCallback = (returnData) => {
    if(!returnData){
      alert('Oops, incorrect email/password, or your account has not been enabled yet')
      // error message popup
    }
    else
    {
      setToken(returnData.token);
      while(returnData.token.accessToken !== getToken()){
        console.log(returnData.token.accessToken);
        console.log(getToken());
      }
      setRole(returnData.userType);
      handleLogin(returnData.userType);
      navigate('/home', {replace: true} )
    }
  }
  /*
    <LabeledInputWithErrMessage isValid={validations.email} label="Email*" inputName="email" required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
    <LabeledInputWithErrMessage isValid={validations.password}label="Password*" inputName="password" required onChangeFunc={handleChange} validationFunc={checkLettersInput} hoverTitile={onlyLetters}/>
    
    */
  return(
    <>
      <BoxContainer>
        <FormContainer>
          <Marginer direction="vertical" margin="5em" />
          <Input type="email" name="username" placeholder="Email" onChange={(e) => handleChange(e)} />
          <Input type="password" name="password" placeholder="Password" onChange={(e) => handleChange(e)}/>

        </FormContainer>
        <Marginer direction="vertical" margin={10} />
        {/*<MutedLink href="#">Forgot your password?</MutedLink>*/}
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
      <Marginer direction="vertical" margin="3em" />
      <Button variant="custom" type="submit" className="formButton" onClick={() => {setToken(null); handleLogin('Guest'); setRole('Guest'); navigate('/home')}} >Continue as a guest  </Button>
    </>
  );
}
