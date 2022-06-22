package mrsa.tim018.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import mrsa.tim018.constants.AssetConstants;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class AssetControllerTest {
	private static final String URL_PREFIX = "/assets";
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testGetAssets() throws Exception {
		mockMvc.perform(get(URL_PREFIX))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(4)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(1000004)))
		.andExpect(jsonPath("$.[*].name").value(hasItem("Dubrovnik Pirate")))
		.andExpect(jsonPath("$.[*].address").value(hasItem("Dubrovnik")))
		.andExpect(jsonPath("$.[*].assetType").value(hasItem("BOAT")));
		;
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteAsset() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + "/" + AssetConstants.DB_ID))
		.andExpect(status().isAccepted());
	}
	
	
	@Test
	public void testGetAsset() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/" + AssetConstants.DB_ID))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1000001))
		.andExpect(jsonPath("$.name").value("Maldivian hut on water"))
		.andExpect(jsonPath("$.address").value("Orchid Magu 7, Maadhad, 57887, Maldives"))
		.andExpect(jsonPath("$.assetType").value("RESORT"));
		;
	}
	
	
	
	
}
