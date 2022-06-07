import '../../assets/styles/form.css';
import '../../assets/styles/asset.css';
import {useState, useEffect, useCallback} from 'react';
import {getAssetPhotoIdsFromServer, deletePhotoOnServer} from '../../services/api/ImageApi';
import AssetScrollerPhotoItem from '../asset/AssetScrollerPhotoItem';

export default function AssetScrollerPhotos({photoIds, deletePhotoFun, doesOnlyOnePhotoExists}){

    const [scrollerPhotoItems, setScrollerPhotoItems] = useState();
    
    useEffect(() => {
        if (photoIds !== undefined){
            let photoItems = photoIds.map((id) => <AssetScrollerPhotoItem photoId={id} key={id.id} isOnlyPhoto={doesOnlyOnePhotoExists} deletePhotoFun={deletePhotoFun} />)
            setScrollerPhotoItems(photoItems);
        }
    }, [photoIds, deletePhotoFun, doesOnlyOnePhotoExists]);

    return (<div className="imageScroller borderedBlockNoShadow">
                {scrollerPhotoItems}
            </div>
            );
}
