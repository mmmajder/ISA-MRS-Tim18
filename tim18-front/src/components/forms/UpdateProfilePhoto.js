import {  Row, Col, Button} from 'react-bootstrap';
import '../../assets/styles/form.css';
import {useState, useEffect, useCallback} from 'react';
import { getLogged } from '../../services/api/LoginApi';
import {uploadProfilePhotoToServer, getPhotoFromServer} from '../../services/api/ImageApi';

export default function UpdateProfilePhoto(){

    const [user, setUser] = useState();
    const [profilePhotoId, setProfilePhotoId] = useState();

    const [image, setImage] = useState();
    const [hasSelectedImage, setHasSelectedImage] = useState(false);
    const [profilePhoto, setProfilePhoto] = useState();

    const selectImage = (event) => {
        setImage(event.target.files);
        setHasSelectedImage(true);
    }

    const uploadProfilePhoto = useCallback(
        (e) => {
            if (!image){
                return
            }
            let currentImage = image[0];
            uploadProfilePhotoToServer(currentImage, user.id).then(
                (response) => {
                    setProfilePhotoId(response.data);
                }
            );
        }, [user, image]
    )

    useEffect(() => {
        getLogged(setUser);
    }, [])

    useEffect(() =>{
        if (!user){
            return
        }
        setProfilePhotoId(user.profilePhotoId);
        console.log("postavio useeffect profilnuId")
    }, [user])

    useEffect(() => {
        if (!profilePhotoId){
            return
        }
        getPhotoFromServer(profilePhotoId).then((response) =>{
            let photo = `data:image/jpeg;base64,${response.data}`
            setProfilePhoto(photo);
        });
    }, [profilePhotoId])

    if(!!user){
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
