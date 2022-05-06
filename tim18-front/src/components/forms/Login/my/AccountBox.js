import React, { useState } from "react";
import { motion } from "framer-motion";
import styled from "styled-components";
import { AccountContext } from "../AccountContext";
import '../../../assets/styles/login.css'

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
  const BackDrop = styled(motion.div)`
  width: 160%;
  height: 550px;
  position: absolute;
  display: flex;
  flex-direction: column;
  border-radius: 50%;
  transform: rotate(60deg);
  top: -290px;
  left: -70px;
  background: rgb(241, 196, 15);
  background: linear-gradient(
    58deg,
    rgba(241, 196, 15, 1) 20%,
    rgba(243, 172, 18, 1) 100%
  );
`;
  
  export function AccountBox(props) {
    const [isExpanded, setExpanded] = useState(false);
    const [active, setActive] = useState("signup");
  
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
  
    return (
        <AccountContext.Provider value={contextValue}>
          <div className="BoxContainer">
            <div className="TopContainer">
              <BackDrop
                initial={false}
                animate={isExpanded ? "expanded" : "collapsed"}
                variants={backdropVariants}
                transition={expandingTransition}
              />
              {active === "signin" && (
                  <div className="HeaderContainer">
                      <h2 className="HeaderText">Welcome</h2>
                      <h2 className="HeaderText">Back</h2>
                      <h5 className="SmallText">Please sign-in to continue!</h5>
                  </div>
              )}
              {active === "signup" && (
                  <div className="HeaderContainer">
                      <h2 className="HeaderText">Create</h2>
                      <h2 className="HeaderText">Account</h2>
                      <h5 className="SmallText">Please sign-up to continue!</h5>
                  </div>
              )}
            </div>
            <div className="InnerContainer">
              {active === "signin" && <LoginForm props={this} />}
              {/*active === "signup" && <SignupForm />*/}
            </div>
          </div>
        </AccountContext.Provider>

    );
  }
  