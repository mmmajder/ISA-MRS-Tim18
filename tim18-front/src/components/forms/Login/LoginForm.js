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

export function LoginForm(props) {
  const { switchToSignup } = useContext(AccountContext);

  return (
    <BoxContainer>
      <FormContainer>
        <Input type="email" placeholder="Email" />
        <Input type="password" placeholder="Password" />
      </FormContainer>
      <Marginer direction="vertical" margin={10} />
      <MutedLink href="#">Forget your password?</MutedLink>
      <Marginer direction="vertical" margin="7.5em" />
      <SubmitButton type="submit">Sign in</SubmitButton>
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