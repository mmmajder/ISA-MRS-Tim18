import { Form, Row, Col} from 'react-bootstrap';

export default function LabeledTextarea({label, inputName, placeholder}){
    return <Row className='mt-2'>
        <Col sm={2} align='right'><Form.Label>{label}</Form.Label></Col>
        <Col sm={9}><Form.Control name={inputName} placeholder={placeholder} as="textarea"></Form.Control></Col>
        <Col sm={1}/>
    </Row>
}