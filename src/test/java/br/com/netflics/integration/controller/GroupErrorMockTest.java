/** generated: 23 de jun de 2021 23:11:06 **/
package br.com.netflics.integration.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.security.SpringSecurityUserContext;
import br.com.netflics.core.rs.exception.ValidationException;

import br.com.netflics.json.JsonGroup;
import br.com.netflics.model.Group;
import br.com.netflics.rs.GroupController;
import br.com.netflics.service.GroupService;
import br.com.netflics.util.MockMvcTestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(GroupController.class)
public class GroupErrorMockTest {

	static MockHttpSession mockHttpSession = MockMvcTestUtil.getSession();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GroupService service;
	@MockBean
	private SpringSecurityUserContext context;

	@Test
	public void errorGetitingGroupById() throws Exception {
		when(service.get(any(Integer.class))).thenThrow(new RuntimeException("Error Getting Group"));
		this.mockMvc.perform(get("/rs/crud/groups/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Group")));
	}

	@Test
	public void errorGetitingAllPagerGroup() throws Exception {
		when(service.get(any(SearchParameters.class))).thenThrow(new RuntimeException("Error Getting Group"));

		this.mockMvc.perform(get("/rs/crud/groups").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Group")));
	}

	@Test
	public void errorSavingGroup() throws Exception {
		when(service.save(any(Group.class))).thenThrow(new RuntimeException("Error creating Group"));

		this.mockMvc.perform(post("/rs/crud/groups").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error creating Group")));
	}
	
	@Test
	public void errorSavingWithValidationGroup() throws Exception {
		when(service.save(any(Group.class))).thenThrow(new ValidationException("Error creating-validating Group"));

		this.mockMvc.perform(post("/rs/crud/groups").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error creating-validating Group")));
	}
	
	@Test
	public void errorUpdatingGroup() throws Exception {
		when(service.update(any(Group.class))).thenThrow(new RuntimeException("Error updating Group"));

		this.mockMvc.perform(put("/rs/crud/groups/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error updating Group")));
	}
	
	@Test
	public void errorUpdatingWithValidationGroup() throws Exception {
		when(service.update(any(Group.class))).thenThrow(new ValidationException("Error updating-validating Group"));

		this.mockMvc.perform(put("/rs/crud/groups/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error updating-validating Group")));
	}

	@Test
	public void errorDeletingGroup() throws Exception {
		when(service.delete(any(Integer.class))).thenThrow(new RuntimeException("Error removing Group"));
		this.mockMvc.perform(delete("/rs/crud/groups/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error removing Group")));
	}

}