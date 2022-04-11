import { Form, Row, Col} from 'react-bootstrap';

export default function LabeledInput({label, inputName, placeholder, onChangeFunc}){
    return <Row className='mt-2'>
        <Col sm={2} align='right'><Form.Label>{label}</Form.Label></Col>
        <Col sm={9}><Form.Control name={inputName} placeholder={placeholder}  onChange={(e) => onChangeFunc(e.target.value)}></Form.Control></Col>
        <Col sm={1}/>
    </Row>
}