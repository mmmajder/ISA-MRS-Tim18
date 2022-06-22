package mrsa.tim018.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import mrsa.tim018.constants.DeletationRequestConstants;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class DeleteationRequestControllerTest {
	private static final String URL_PREFIX = "/deletationRequest";
	
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
	@WithMockUser(username = "username", roles={"USER"})
	public void testGetDeletationRequest() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/" + DeletationRequestConstants.DB_ID))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(DeletationRequestConstants.DB_ID.longValue()))
		.andExpect(jsonPath("$.reason").value(DeletationRequestConstants.DB_REASON));
	}
	
	@Test
	@WithMockUser(username = "username", roles={"ADMIN"})
	public void testGetAllDeletationRequests() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/all"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(DeletationRequestConstants.DB_COUNT.intValue())))
		.andExpect(jsonPath("$.[*].id").value(hasItem(DeletationRequestConstants.DB_ID.intValue())))
		.andExpect(jsonPath("$.[*].reason").value(hasItem(DeletationRequestConstants.DB_REASON)));
	}
	
	@Test
	@WithMockUser(username = "username", roles={"ADMIN"})
	public void testGetPendingDeletationRequests() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/pending"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(DeletationRequestConstants.DB_COUNT.intValue())))
		.andExpect(jsonPath("$.[*].id").value(hasItem(DeletationRequestConstants.DB_ID.intValue())))
		.andExpect(jsonPath("$.[*].reason").value(hasItem(DeletationRequestConstants.DB_REASON)));
	}
	
	
}
