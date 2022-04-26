
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

export function SignupForm(props) {
  const { switchToSignin } = useContext(AccountContext);

  return (
    <BoxContainer>
      <FormContainer>
        <Input type="text" placeholder="Full Name" />
        <Input type="email" placeholder="Email" />
        <Input type="password" placeholder="Password" />
        <Input type="password" placeholder="Confirm Password" />
      </FormContainer>
      <Marginer direction="vertical" margin={10} />
      <SubmitButton type="submit">Signup</SubmitButton>
      <Marginer direction="vertical" margin="1em" />
      <MutedLink href="#">
        Already have an account?
        <BoldLink href="#" onClick={switchToSignin}>
          Signin
        </BoldLink>
      </MutedLink>
    </BoxContainer>
  );
}
/*import React, { useContext } from "react";
import { Marginer } from "./Marginer";
import { AccountContext } from "./AccountContext";
import { Form, Button} from 'react-bootstrap';
import '../../../assets/styles/login.css'

export function SignupForm() {
  const { switchToSignin } = useContext(AccountContext);

  return (
    <div className="BoxContainer">
        <Form className="FormContainer">
            <Form.Control className="InputStyle" type="text" placeholder="Full Name" />
            <Form.Control className="InputStyle" type="email" placeholder="Email" />
            <Form.Control className="InputStyle" type="password" placeholder="Password" />
            <Form.Control className="InputStyle" type="password" placeholder="Confirm Password" />
        </Form>
        <Marginer direction="vertical" margin={10} />
        <Button className="SubmitButton" type="submit">Signin</Button>
        <Marginer direction="vertical" margin="1em" />
        <a className="MutedLink" href="#">
            Already have an account?{" "}
            <a className="BoldLink" href="#" onClick={switchToSignin}>
                Sign in
            </a>
        </a>
    </div>
  );
}*/