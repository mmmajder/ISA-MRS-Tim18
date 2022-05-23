import {  Row, Col, Button} from 'react-bootstrap';
import '../../assets/styles/form.css';
import '../../assets/styles/asset.css';
import AssetScrollerPhotos from '../asset/AssetScrollerPhotos';
import {useParams} from 'react-router-dom';
import { getAssetById } from '../../services/api/AssetApi';
import {useState, useEffect, useCallback} from 'react';
import {getAssetPhotoIdsFromServer, deletePhotoOnServer, uploadAssetPhotoToServer} from '../../services/api/ImageApi';

export default function UpdateAssetPhotos(){

    const {id} = useParams();
    const [asset, setAsset] = useState({});
    const [image, setImage] = useState();
    const [hasSelectedImage, setHasSelectedImage] = useState(false);

    const [photoIds, setPhotoIds] = useState();
    const [doesOnlyOnePhotoExists, setOnlyOnePhotoExists] = useState(false);

    const getPhotoIds = useCallback(
        (e) => {
            if (!asset){
                return
            }
            getAssetPhotoIdsFromServer(asset.id).then((response) =>{
                let photoIds = response.data;
                setPhotoIds(photoIds);
            });
        }, [asset]
    )

    useEffect(() => {
        getPhotoIds();
    }, [getPhotoIds]);

    useEffect(() => {
        if (!!photoIds && photoIds.length === 1){
            setOnlyOnePhotoExists(true);
        } else{
            setOnlyOnePhotoExists(false);
        }
    }, [photoIds, setOnlyOnePhotoExists]);


    const deletePhoto = useCallback(
        (photoId) => {
            deletePhotoOnServer(photoId).then( 
                (response) => {
                    const index = photoIds.indexOf(photoId);
                    if (index > -1) {
                        let newPhotoIds = JSON.parse(JSON.stringify(photoIds)); // deep copy array
                        newPhotoIds.splice(index, 1); // 2nd parameter means remove one item only
                        setPhotoIds(newPhotoIds);
                    }
                })
        },[photoIds]
    )

    useEffect(() => {
        getAssetById(id).then((requestData) =>{
            setAsset(!!requestData ? requestData.data : {});
        });
    }, [id])

    const selectImage = (event) => {
        setImage(event.target.files);
        setHasSelectedImage(true);
    }

    const uploadAssetPhoto = useCallback(
        () => {
            if (!image){
                return;
            }
            console.log("tusmo")
            let currentImage = image[0];
            uploadAssetPhotoToServer(currentImage, asset.id).then(
                (response) =>{
                    const newPhotoId = response.data;
                    let newPhotoIds = JSON.parse(JSON.stringify(photoIds)); // deep copy array
                    newPhotoIds.push(newPhotoId);
                    setPhotoIds(newPhotoIds);
                }
            );
        }, [image, asset, setPhotoIds, photoIds]
    )

    return (<>
            <div className="borderedBlock mt-3">  
                <Row>
                    <Col sm={6} align='center'>
                        <AssetScrollerPhotos photoIds={photoIds} deletePhotoFun={deletePhoto} doesOnlyOnePhotoExists={doesOnlyOnePhotoExists}/>
                    </Col>
                    <Col sm={6} >
                        <div >
                            <div className="form-group">
                                <label>Upload New Asset Photo </label>
                                <input type="file" className="form-control" name="file" onChange={selectImage} />
                            </div>
                        </div>
                        <Row className="mt-3">
                            <Col sm={12} align="center" >
                            <Button variant="custom" type="submit" className='formButton' disabled={!hasSelectedImage} onClick={uploadAssetPhoto}>
                                Upload
                            </Button>
                            </Col>
                        </Row>
                    </Col>
                </Row>
                
                
            </div>
        </>
    );
}
