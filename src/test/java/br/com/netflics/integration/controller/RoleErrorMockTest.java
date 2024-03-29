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

import br.com.netflics.json.JsonRole;
import br.com.netflics.model.Role;
import br.com.netflics.rs.RoleController;
import br.com.netflics.service.RoleService;
import br.com.netflics.util.MockMvcTestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(RoleController.class)
public class RoleErrorMockTest {

	static MockHttpSession mockHttpSession = MockMvcTestUtil.getSession();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RoleService service;
	@MockBean
	private SpringSecurityUserContext context;

	@Test
	public void errorGetitingRoleById() throws Exception {
		when(service.get(any(Integer.class))).thenThrow(new RuntimeException("Error Getting Role"));
		this.mockMvc.perform(get("/rs/crud/roles/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Role")));
	}

	@Test
	public void errorGetitingAllPagerRole() throws Exception {
		when(service.get(any(SearchParameters.class))).thenThrow(new RuntimeException("Error Getting Role"));

		this.mockMvc.perform(get("/rs/crud/roles").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Role")));
	}

	@Test
	public void errorSavingRole() throws Exception {
		when(service.save(any(Role.class))).thenThrow(new RuntimeException("Error creating Role"));

		this.mockMvc.perform(post("/rs/crud/roles").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error creating Role")));
	}
	
	@Test
	public void errorSavingWithValidationRole() throws Exception {
		when(service.save(any(Role.class))).thenThrow(new ValidationException("Error creating-validating Role"));

		this.mockMvc.perform(post("/rs/crud/roles").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error creating-validating Role")));
	}
	
	@Test
	public void errorUpdatingRole() throws Exception {
		when(service.update(any(Role.class))).thenThrow(new RuntimeException("Error updating Role"));

		this.mockMvc.perform(put("/rs/crud/roles/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error updating Role")));
	}
	
	@Test
	public void errorUpdatingWithValidationRole() throws Exception {
		when(service.update(any(Role.class))).thenThrow(new ValidationException("Error updating-validating Role"));

		this.mockMvc.perform(put("/rs/crud/roles/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error updating-validating Role")));
	}

	@Test
	public void errorDeletingRole() throws Exception {
		when(service.delete(any(Integer.class))).thenThrow(new RuntimeException("Error removing Role"));
		this.mockMvc.perform(delete("/rs/crud/roles/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error removing Role")));
	}

}