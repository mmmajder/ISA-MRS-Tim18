import { Button, Modal } from 'react-bootstrap';

export function ConfirmModal({show, handleClose, message}){
    return (
        <>  
          <Modal show={show} onHide={() => handleClose("Cancel")}>
          <Modal.Header closeButton>
            <Modal.Title>Warning!</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            {message}
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={() => handleClose("Cancel")}>
              Close
            </Button>
            <Button variant="primary" onClick={() => handleClose("Save")}>
              Ok
            </Button>
          </Modal.Footer>
        </Modal>
        </>
      );
}