import {  Row, Col, Button} from 'react-bootstrap';
import '../../assets/styles/form.css';
import {useState, useEffect, useCallback} from 'react';
import { getLogged } from '../../services/api/LoginApi';
import {uploadProfilePhotoToServer, getPhotoFromServer} from '../../services/api/ImageApi';

export default function UpdateProfilePhoto(){

    const [renter, setRenter] = useState();
    const [image, setImage] = useState();
    const [hasSelectedImage, setHasSelectedImage] = useState(false);
    const [profilePhoto, setProfilePhoto] = useState();

    const selectImage = (event) => {
        setImage(event.target.files);
        setHasSelectedImage(true);
    }

    const uploadProfilePhoto = (event) => {
        console.log("tusmo")
        let currentImage = image[0];
        uploadProfilePhotoToServer(currentImage, renter.id);
    }

    const getProfilePhoto = useCallback(
        (e) => {
            getPhotoFromServer(renter.profilePhotoId).then((response) =>{
                let photo = `data:image/jpeg;base64,${response.data}`
                setProfilePhoto(photo);
            });
        }, [renter]
    )

    useEffect(() => {
        getLogged(setRenter);
    }, [])

    useEffect(() => {
        if (!!renter && !!renter.profilePhotoId){
            console.log(renter)
            console.log(renter.id)
            console.log(renter.profilePhotoId)
            getProfilePhoto()
        } else{
            setProfilePhoto(require('../../assets/images/blue_profile_pic.jpg'))
        }
    }, [renter])

    if(!!renter){
        return (<>
                <div className="borderedBlock mt-3">  
                    <Row>
                        <Col sm={4} align='center'>
                            <img src={profilePhoto} className="profilePicture rounded-circle" ></img>
                        </Col>
                        <Col sm={8} >
                            <div >
                                <div className="form-group">
                                    <label>Upload Your New Photo </label>
                                    <input type="file" className="form-control" name="file" onChange={selectImage} />
                                </div>
                            </div>
                        </Col>
                    </Row>
                    <Row>
                        <Col sm={12} align="center" >
                        <Button variant="custom" type="submit" className='formButton' disabled={!hasSelectedImage} onClick={uploadProfilePhoto}>
                                Upload
                            </Button>
                        </Col>
                    </Row>
                    
                </div>
            </>
        );
    }   
}
