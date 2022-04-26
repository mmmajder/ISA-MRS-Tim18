import React, {useState} from 'react'
import {crateAdventure} from '../../services/api/AdventureApi'
import { Form, Row, Col, Button } from 'react-bootstrap';
import {LabeledInput} from './LabeledInput';
import LabeledTextarea from './LabeledTextarea';

const CreateAdventureForm = () => {

  const [name, setName] = useState('');
  const [address, setAddress] = useState('');
  const [description, setDescription] = useState('');
  const [rules, setRules] = useState('');
  const [numOfPeople, setNumOfPeople] = useState(1);
  const [cancelationFee, setCancelationFee] = useState(0);

  const adventureJson = {name, address, description, rules, numOfPeople, cancelationFee}
  const createdUser = crateAdventure(adventureJson)


  return (
    <Row className='mt-5'>
      <Col sm={2}/>
      <div className='borderedBlock'>
        <form>
          <LabeledInput label="Name" inputName="name" placeholder="Type name of your resort" required onChangeFunc={setName}/>
          <LabeledInput label="Address" inputName="address" placeholder="Type address of your resort" required onChangeFunc={setAddress}/>
          <LabeledTextarea label="Description" inputName="description" placeholder="Type description of your resort" required onChangeFunc={setDescription}/>
          <LabeledTextarea label="Rules" inputName="rules" placeholder="Type rules of your resort" required onChangeFunc={setRules}/>
          <Row className='mt-2'>
            <Col sm={3} align='right'><Form.Label>Number of people</Form.Label></Col>
            <Col sm={2}>
                <Form.Control name="numOfPeople"  type="number" min="1" required
                    value={numOfPeople} 
                    onChange={(e) => setNumOfPeople(e.target.value)}>
                </Form.Control>
            </Col>
            <Col sm={3} align='right'><Form.Label>Cancelation fee in % </Form.Label></Col>
            <Col sm={2}>
                <Form.Control name="cancelationFee"  type="number" min="0" max="100" required
                    value={cancelationFee} 
                    onChange={(e) => setCancelationFee(e.target.value)}>
                </Form.Control>
            </Col>
            <Col sm={2}/>
        </Row>
        <Row className='mt-2'>
            <Col sm={4}/>
            <Col sm={4} align='center'>
                <Button variant="custom" type="submit" className='formButton' onClick={createdUser}>
                    Create resort
                </Button>
            </Col>
            <Col sm={4}/>
        </Row>
        </form>
      </div>
      <Col sm={2}/>
    </Row>
  )
}

export default CreateAdventureForm