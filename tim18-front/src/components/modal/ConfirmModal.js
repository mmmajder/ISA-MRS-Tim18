import { Button, Modal } from 'react-bootstrap';

export function ConfirmModal({title, show, handleClose, message}){
    return (
        <>  
          <Modal show={show} onHide={() => handleClose("Cancel")}>
          <Modal.Header closeButton>
            <Modal.Title>{title}</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            {message}
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={() => handleClose("Cancel")}>
              Cancel
            </Button>
            <Button variant="primary" onClick={() => handleClose("Save")}>
              Ok
            </Button>
          </Modal.Footer>
        </Modal>
        </>
      );
}