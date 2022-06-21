package mrsa.tim018.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import mrsa.tim018.constants.AssetConstants;
import mrsa.tim018.dto.AssetDTO;
import mrsa.tim018.model.Asset;
import mrsa.tim018.model.AssetCalendar;
import mrsa.tim018.model.AssetType;
import mrsa.tim018.model.Renter;
import mrsa.tim018.model.Resort;
import mrsa.tim018.repository.AssetRepository;
import mrsa.tim018.repository.ResortRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssetServiceTest {
	
	@Mock
	private AssetRepository assetRepositoryMock;
	
	@Mock
	private Asset assetMock;
	
	@Mock
	private ResortRepository resortRepositoryMock;
	
	@InjectMocks
	private AssetService assetService;
	
	@Test
    @Transactional
    @Rollback(true) // uključeno po default-u, ne mora se navesti
	public void testCreateAsset() throws Exception{
		Asset asset = new Asset();
		asset.setId(1L);
		asset.setAddress("address");
		asset.setAssetType(AssetType.BOAT);
		asset.setAverageRating(0);
		asset.setCalendar(new AssetCalendar());
		asset.setCancelationConditions(0);
		asset.setDeleted(false);
		asset.setDescription("description");
		asset.setName("name");
		asset.setPrice(0);
		asset.setRenter(new Renter());
		asset.setRules("rules");
		asset.setSubscriptions(new ArrayList<>());
		
		List<Asset> assets = new ArrayList<Asset>();
		assets.add(new Asset(1L, false, AssetType.BOAT, "name", "address", "description", new ArrayList<>(), "rules", 0, 0, 0));
		when(assetRepositoryMock.findAll()).thenReturn(assets);
		when(assetRepositoryMock.save(asset)).thenReturn(asset);
		
		int dbSizeBeforeAdd = assetService.findAll().size();
		
		Asset dbAsset = assetService.save(asset);
		
		assets.add(asset);
		when(assetRepositoryMock.findAll()).thenReturn(assets);
		
		assertThat(dbAsset).isNotNull();
		

		List<Asset> assetVerify = assetService.findAll();
        assertThat(assets).hasSize(dbSizeBeforeAdd + 1); //verifikacija da je novi student upisan u bazu
        
        dbAsset = assets.get(assets.size() - 1); // preuzimanje poslednjeg studenta
        
        assertThat(dbAsset.getID()).isEqualTo(1);
        
        verify(assetRepositoryMock, times(2)).findAll();
        verify(assetRepositoryMock, times(1)).save(asset);
        verifyNoMoreInteractions(assetRepositoryMock);
		
	}
	@Test
    @Transactional
    @Rollback(true)
    public void testUpdateAsset() throws Exception{
		AssetDTO assetDTO = new AssetDTO();
		assetDTO.setId(AssetConstants.DB_ID);
		assetDTO.setAddress("newadresa");
		assetDTO.setAssetType(AssetType.RESORT);
		assetDTO.setDescription("newdescription");
		assetDTO.setName("newname");
		assetDTO.setCancelationConditions(5);
		assetDTO.setNumOfPeople(2);
		assetDTO.setRules("newrules");

		Asset currentAsset = new Asset();
		currentAsset.setAddress("address");
		currentAsset.setAssetType(AssetType.RESORT);
		currentAsset.setAverageRating(5);
		currentAsset.setCalendar(new AssetCalendar());
		currentAsset.setCancelationConditions(10);
		currentAsset.setDeleted(false);
		currentAsset.setDescription("description");
		currentAsset.setId(AssetConstants.DB_ID);
		currentAsset.setName("name");
		currentAsset.setNumOfPeople(5);
		currentAsset.setPrice(100);
		currentAsset.setPrices(new ArrayList<>());
		currentAsset.setRenter(new Renter());
		currentAsset.setRules("rules");
		currentAsset.setSubscriptions(new ArrayList<>());
		
		when(assetRepositoryMock.findById(1000001L)).thenReturn(Optional.of(currentAsset));
		Asset assetForUpdate = assetService.findById(AssetConstants.DB_ID);
		assetForUpdate.setAddress(AssetConstants.NEW_ADDRESS);
		assetForUpdate.setDescription(AssetConstants.NEW_DESCRIPTION);
		assetForUpdate.setName(AssetConstants.NEW_NAME);
		assetForUpdate.setCancelationConditions(AssetConstants.NEW_CONDITIONS);
		assetForUpdate.setNumOfPeople(AssetConstants.NEW_NUMBER);
		assetForUpdate.setRules(AssetConstants.NEW_RULES);
		
		when(assetRepositoryMock.save(assetForUpdate)).thenReturn( assetForUpdate);
		
		Asset newAsset = assetService.save(assetForUpdate);
		
		when(assetRepositoryMock.findById(1000001L)).thenReturn(Optional.of(newAsset));
		
		assertThat(assetForUpdate).isNotNull();
		
		assetForUpdate = assetService.findById(AssetConstants.DB_ID); // verifikacija da se u bazi nalaze izmenjeni podaci
        assertThat(assetForUpdate.getAddress()).isEqualTo(AssetConstants.NEW_ADDRESS); 
        assertThat(assetForUpdate.getDescription()).isEqualTo(AssetConstants.NEW_DESCRIPTION);
        assertThat(assetForUpdate.getName()).isEqualTo(AssetConstants.NEW_NAME);
        assertThat(assetForUpdate.getCancelationConditions()).isEqualTo(AssetConstants.NEW_CONDITIONS); 
        assertThat(assetForUpdate.getNumOfPeople()).isEqualTo(AssetConstants.NEW_NUMBER);
        assertThat(assetForUpdate.getRules()).isEqualTo(AssetConstants.NEW_RULES);
        
        verify(assetRepositoryMock, times(1)).save(assetForUpdate);

    }
	@Test
    @Transactional
    @Rollback(true)
	public void testDeleteAsset() throws Exception{
		Asset asset = new Asset();
		asset.setAddress("address");
		asset.setAssetType(AssetType.RESORT);
		asset.setAverageRating(5);
		asset.setCalendar(new AssetCalendar());
		asset.setCancelationConditions(10);
		asset.setDeleted(false);
		asset.setDescription("description");
		asset.setId(AssetConstants.DB_ID);
		asset.setName("name");
		asset.setNumOfPeople(5);
		asset.setPrice(100);
		asset.setPrices(new ArrayList<>());
		asset.setRenter(new Renter());
		asset.setRules("rules");
		asset.setSubscriptions(new ArrayList<>());
		
		when(assetRepositoryMock.findById(1000001L)).thenReturn(Optional.of(asset));
		
		Asset assetForDelete = assetService.findById(1000001L);
		assetForDelete.setDeleted(true);
		
		when(assetRepositoryMock.save(assetForDelete)).thenReturn(assetForDelete);
		assetForDelete = assetService.save(assetForDelete);
		
		assertThat(assetForDelete).isNotNull();
		
		when(assetRepositoryMock.findById(1000001L)).thenReturn(Optional.of(assetForDelete));
		
		assetForDelete = assetService.findById(AssetConstants.DB_ID); // verifikacija da se u bazi nalaze izmenjeni podaci
        assertThat(assetForDelete.isDeleted()).isEqualTo(true);
		
	}

}
