import React, { useState, useEffect, useRef } from "react";
import styled from "styled-components";
import { LoginForm} from "./LoginForm";
import { motion } from "framer-motion";
import { AccountContext } from "./AccountContext";
import { SignupForm } from "./SignupForm";
import { Router, Routes, Route } from "react-router-dom";
import FeedbackPopUpFixed from "../PopUps/FeedbackPopUp-fixed";
import { Row, Col } from "react-bootstrap";

const BoxContainer = styled.div`
  width: 60%;
  min-height: 100%;
  display: flex;
  flex-direction: column;
  border-radius: 19px;
  background-color: #fff;
  
  position: relative;
  overflow: hidden;
  border: solid 3px var(--dark-blue);
  box-shadow: 0 0 5px var(--custom-dark-grey);
`;

const TopContainer = styled.div`
  height: 400px;
  display: flex;
  flex-direction: column;
  align: center;
  justify-content: flex-start;
  padding: 0 2em;
  
`;

const BackDrop = styled(motion.div)`
  
  position: absolute;
  display: flex;
  flex-basis:100%;
  
  top: -92%;  
  left: -101%; 
  
  background:  var(--dark-blue);
  
`;

const HeaderContainer = styled.div`

  width: 100%;
  display: flex;
  flex-direction: column;
`;

const HeaderText = styled.h2`
  font-size: 55px;
  font-weight: 600;
  line-height: 1;
  color: #fff;
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
  padding: 3em 4.8em;
`;

const ImageConatiner = styled.div`
  position: absolute;
  width: 150%;
  height: 200px;
  display: flex;
  flex-basis:50%;
  padding: 3em 30em;  
  overflow: hidden;

`;

const backdropVariants = {
  expanded: {
    width: "233%",
    height: "1050px",
    borderRadius: "20%",
    transform: "rotate(60deg)",
  },
  collapsed: {
    width: "160%",
    height: "550px",
    borderRadius: "50%",
    transform: "rotate(60deg)",
  },
};

const expandingTransition = {
  type: "spring",
  duration: 2.3,
  stiffness: 30,
};

export function AccountBox({handleLogin}) {
  const [isExpanded, setExpanded] = useState(false);
  const [active, setActive] = useState("signin");

  const playExpandingAnimation = () => {
    setExpanded(true);
    setTimeout(() => {
      setExpanded(false);
    }, expandingTransition.duration * 1000 - 1500);
  };

  const switchToSignup = () => {
    playExpandingAnimation();
    setTimeout(() => {
      setActive("signup");
    }, 400);
  };

  const switchToSignin = () => {
    playExpandingAnimation();
    setTimeout(() => {
      setActive("signin");
    }, 400);
  };


  const contextValue = { switchToSignup, switchToSignin };
  const logo = require('../../../assets/images/island_logo.png')
  const message = "Successfully created request! Please check your email to confirm your identity";
  const firstUpdate = useRef(true);

  const [feedbackType, setFeedbackType] = useState(true);
  const [feedbackShow, setFeedbackShow] = useState(false);
  const [feedbackMessage, setFeedbackMessage] = useState('');

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

  return (
   <> 
    <FeedbackPopUpFixed changeToShow={feedbackShow} isError={feedbackType} message={feedbackMessage} resetData={reset}/> 
    <AccountContext.Provider value={contextValue}>
      <BoxContainer>
        <TopContainer>
          <BackDrop
            initial={false}
            animate={isExpanded ? "expanded" : "collapsed"}
            variants={backdropVariants}
            transition={expandingTransition}
          />
          <ImageConatiner>
            <TitleText>Hakuna Matata</TitleText>
            <img src={logo} alt="logo" /> 
          </ImageConatiner>
          {active === "signin" && (
            <HeaderContainer>
              <HeaderText>Welcome</HeaderText>
              <HeaderText>Back</HeaderText>
              <SmallText>Please sign-in to continue!</SmallText>
            </HeaderContainer>
          )}
          {active === "signup" && (
            <HeaderContainer>
              <HeaderText>Create</HeaderText>
              <HeaderText>Account</HeaderText>
              <SmallText>Please sign-up to continue!</SmallText>
            </HeaderContainer>
          )}
          
        </TopContainer>
        <InnerContainer>
          {active === "signin" && <LoginForm handleLogin={handleLogin}/>}
          {active === "signup" && <SignupForm feedbackFunc={setFeedbackPopup}/>}
        </InnerContainer>
      </BoxContainer>
      </AccountContext.Provider>
    </>
  );
}