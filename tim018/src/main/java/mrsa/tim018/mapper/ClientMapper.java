package mrsa.tim018.mapper;

import mrsa.tim018.dto.ClientDTO;
import mrsa.tim018.model.Client;
import mrsa.tim018.utils.StringUtils;

public class ClientMapper {

	public static Client mapToClient(Client client, ClientDTO clientDTO) {
		client.setFirstName(StringUtils.capitalizeAllWords(clientDTO.getFirstName()));
		client.setLastName(StringUtils.capitalizeAllWords(clientDTO.getLastName()));
		client.setDeleted(clientDTO.isDeleted());
		client.setAddress(StringUtils.capitalizeFirstWord(clientDTO.getAddress()));
		client.setCity(StringUtils.capitalizeFirstWord(clientDTO.getCity()));
		client.setState(StringUtils.capitalizeFirstWord(clientDTO.getState()));
		client.setPhoneNum(clientDTO.getPhoneNum());
		client.setLoyaltyPoints(clientDTO.getLoyaltyPoints());
		client.setPenaltyPoints(clientDTO.getPenaltyPoints());
		
		return client;
	}
}
