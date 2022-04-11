import { Form, Row, Col, Button } from 'react-bootstrap';
import LabeledInput from './LabeledInput';
import LabeledTextarea from './LabeledTextarea';
import '../../assets/styles/form.css';


export default function CreateResortForm(){

    return <>
    <Row className='mt-5' >
        <Col sm={2} />
        <div className="borderedBlock">
            <Col sm={true} >
                <Form>
                    <LabeledInput label="Name" inputName="name" placeholder="Type name of your resort" />
                    <LabeledInput label="Address" inputName="address" placeholder="Type address of your resort" />
                    <LabeledTextarea label="Description" inputName="description" placeholder="Type description of your resort" />
                    <LabeledTextarea label="Rules" inputName="rules" placeholder="Type rules of your resort" />
                    <Row className='mt-2'>
                        <Col sm={3} align='right'><Form.Label>Number of people</Form.Label></Col>
                        <Col sm={2}><Form.Control name="rules"  type="number" min="0"></Form.Control></Col>
                        <Col sm={3} align='right'><Form.Label>Cancelation fee in % </Form.Label></Col>
                        <Col sm={2}><Form.Control name="rules"  type="number" min="0" max="100"></Form.Control></Col>
                        <Col sm={2}/>
                    </Row>
                    <Row className='mt-2'>
                        <Col sm={4}/>
                        <Col sm={4} align='center'>
                            <Button variant="custom" type="submit" className='formButton'>
                                Create resort
                            </Button>
                        </Col>
                        <Col sm={4}/>
                    </Row>
                </Form>
            </Col>
        </div>
        <Col sm={2} />
    </Row>
    </>
}