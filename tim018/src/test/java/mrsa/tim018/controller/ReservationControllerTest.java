package mrsa.tim018.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import mrsa.tim018.constants.ReservationConstants;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ReservationControllerTest {
	private static final String URL_PREFIX = "/reservation";

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
	public void testGetCurrentReservations() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/current/" + ReservationConstants.DB_ID_CLIENT + "?assetType=" + ReservationConstants.ASSET_TYPE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(1)))
		.andExpect(jsonPath("$.[*].totalPrice").value(hasItem(100.00)));
		;
	}
	
	@Test
	public void testGetReservation() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/" + ReservationConstants.DB_ID))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1L))
		.andExpect(jsonPath("$.totalPrice").value(100))
		.andExpect(jsonPath("$.assetReviewId").value(IsNull.nullValue()))
		.andExpect(jsonPath("$.clientReviewId").value(IsNull.nullValue()))
		.andExpect(jsonPath("$.renterReviewId").value(IsNull.nullValue()))
		
		;
	}
	
	
	

}
