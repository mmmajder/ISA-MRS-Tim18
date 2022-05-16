import  React,  { useState, useEffect, useRef} from 'react';
import { BoxContainer } from "./common";
import { Marginer } from "./marginer";
import { useNavigate } from 'react-router';
import { Button } from 'react-bootstrap';
import '../../../assets/styles/login.css';
import { Form, Row, Col} from 'react-bootstrap';
import styled from "styled-components";
import { useParams } from 'react-router-dom';
import { getVerificationCode } from '../../../services/api/LoginApi'

export function Confirmation({}) {
  const navigate = useNavigate();
  const logo = require('../../../assets/images/island_logo.png')
  const email = require('../../../assets/images/email.png')
  
  const okMessage = "Congratulation, your account has been verified!";
  const errMessage = "Sorry, we could not verify account. It may be already verified, or verification code is incorrect";

  const { code } = useParams();

  const [isValidCode, setIsValidCode] = useState({});
  const [message, setMessage] = useState("");
  const firstUpdate = useRef(true);

  useEffect(() => {
    async function fetchClient(){
        await getVerificationCode(setIsValidCode, code);
    }
    fetchClient();
  }, [code]) 

  useEffect(() => {
    console.log(isValidCode)
    if (firstUpdate.current) {
        firstUpdate.current = false;
        return;
      }
    if(isValidCode === null || isValidCode === undefined){
        setMessage(errMessage);
    }
    else{
        setMessage(okMessage);
    }
  }, [isValidCode]) 

  
  
  return(
      <BoxContainer>
        <TopContainer>
          <ImageConatiner>
            <TitleText>Hakuna Matata</TitleText>
            <img src={logo} alt="logo" /> 
          </ImageConatiner>
        </TopContainer>
        <div className="text-center" style={{padding: "2em 4.8em"}}>
            <HeaderText>{message}</HeaderText>
        </div>
        <InnerContainer>
        <BoxContainer>
            <img height="420px" width="960px" src={email} alt="logo" /> 
        </BoxContainer>
        </InnerContainer>
      <Button variant="custom" type="submit" className="formButton" onClick={() => {navigate("/login")}} > Click here to login  </Button>
      <Marginer direction="vertical" margin="1em" />
      </BoxContainer>
  );
}



const TopContainer = styled.div`
  height: 270px;
  display: flex;
  flex-direction: column;
  align: center;
  justify-content: flex-start;
  
  
`;

const HeaderText = styled.h3`
  font-size: 40px;
  font-weight: 500;
  line-height: 1;
  color:  black;
  z-index: 10;
  margin: 0;
`;

const TitleText = styled.h2`
  font-size: 55px;
  font-weight: 600;
  line-height: 1;
  color: var(--dark-blue);
  z-index: 10;
  margin: 0;
`;

const SmallText = styled.h5`
  color: #fff;
  font-weight: 500;
  font-size: 20px;
  z-index: 10;
  margin: 0;
  margin-top: 7px;
`;

const InnerContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  padding: 2em 4.8em;
`;

const MessageContainer = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  padding: 2em 4.8em;
`;

const ImageConatiner = styled.div`
  position: absolute;
  width: 100%;
  height: 200px;
  display: flex;
  padding: 2em 6em;  
  overflow: hidden;
`;
