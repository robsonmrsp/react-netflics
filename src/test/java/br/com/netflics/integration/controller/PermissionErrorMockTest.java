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

import br.com.netflics.json.JsonPermission;
import br.com.netflics.model.Permission;
import br.com.netflics.rs.PermissionController;
import br.com.netflics.service.PermissionService;
import br.com.netflics.util.MockMvcTestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(PermissionController.class)
public class PermissionErrorMockTest {

	static MockHttpSession mockHttpSession = MockMvcTestUtil.getSession();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PermissionService service;
	@MockBean
	private SpringSecurityUserContext context;

	@Test
	public void errorGetitingPermissionById() throws Exception {
		when(service.get(any(Integer.class))).thenThrow(new RuntimeException("Error Getting Permission"));
		this.mockMvc.perform(get("/rs/crud/permissions/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Permission")));
	}

	@Test
	public void errorGetitingAllPagerPermission() throws Exception {
		when(service.get(any(SearchParameters.class))).thenThrow(new RuntimeException("Error Getting Permission"));

		this.mockMvc.perform(get("/rs/crud/permissions").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Permission")));
	}

	@Test
	public void errorSavingPermission() throws Exception {
		when(service.save(any(Permission.class))).thenThrow(new RuntimeException("Error creating Permission"));

		this.mockMvc.perform(post("/rs/crud/permissions").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error creating Permission")));
	}
	
	@Test
	public void errorSavingWithValidationPermission() throws Exception {
		when(service.save(any(Permission.class))).thenThrow(new ValidationException("Error creating-validating Permission"));

		this.mockMvc.perform(post("/rs/crud/permissions").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error creating-validating Permission")));
	}
	
	@Test
	public void errorUpdatingPermission() throws Exception {
		when(service.update(any(Permission.class))).thenThrow(new RuntimeException("Error updating Permission"));

		this.mockMvc.perform(put("/rs/crud/permissions/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error updating Permission")));
	}
	
	@Test
	public void errorUpdatingWithValidationPermission() throws Exception {
		when(service.update(any(Permission.class))).thenThrow(new ValidationException("Error updating-validating Permission"));

		this.mockMvc.perform(put("/rs/crud/permissions/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error updating-validating Permission")));
	}

	@Test
	public void errorDeletingPermission() throws Exception {
		when(service.delete(any(Integer.class))).thenThrow(new RuntimeException("Error removing Permission"));
		this.mockMvc.perform(delete("/rs/crud/permissions/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error removing Permission")));
	}

}