package mrsa.tim018.dto;

import java.util.ArrayList;
import java.util.List;

public class AssetsListDTO {
	
	private ArrayList<AssetDTO> assets;
	public AssetsListDTO() {}
	
	public AssetsListDTO(List<AssetDTO> assets) {
		this.assets = (ArrayList<AssetDTO>) assets;
	}

	public List<AssetDTO> getAssets() {
		return assets;
	}

	public void setAssets(List<AssetDTO> assets) {
		this.assets = (ArrayList<AssetDTO>) assets;
	}
	
	
}
