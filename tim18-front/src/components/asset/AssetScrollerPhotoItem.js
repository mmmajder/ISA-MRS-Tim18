import {  Row, Col, Button} from 'react-bootstrap';
import '../../assets/styles/form.css';
import '../../assets/styles/asset.css';
import {useState, useEffect, useCallback} from 'react';
import {deletePhotoOnServer, getPhotoFromServer} from '../../services/api/ImageApi';

export default function AssetScrollerPhotoItem({photoId, deletePhotoFun, isOnlyPhoto}){

    const [assetPhoto, setAssetPhoto] = useState();

    const getPhoto = useCallback(
        (e) => {
            if (!photoId){
                return
            }
            getPhotoFromServer(photoId).then((response) =>{
                console.log(response);
                let photo = `data:image/jpeg;base64,${response.data}`
                setAssetPhoto(photo);
            });
        }, [photoId]
    )

    useEffect(
        (e) => {
            getPhoto();
        }, [getPhoto]
    )

    const deletePhoto = useCallback(
        () => {
            deletePhotoFun(photoId);
        }, [deletePhotoFun, photoId]
    )

    return (<Row className="mb-3">
                <Col sm={6} align='center'>
                    <img src={assetPhoto} ></img>
                </Col>
                <Col sm={6} align='center'>
                <Button variant="custom" type="submit" className='formButton  mt-4' onClick={deletePhoto} disabled={isOnlyPhoto}>
                    Delete
                </Button>
                </Col>
            </Row>
            );
}
