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

import br.com.netflics.core.model.Tenant;

import br.com.netflics.core.persistence.pagination.SearchParameters;
import br.com.netflics.core.security.SpringSecurityUserContext;
import br.com.netflics.core.rs.exception.ValidationException;

import br.com.netflics.json.JsonUser;
import br.com.netflics.model.User;
import br.com.netflics.rs.UserController;
import br.com.netflics.service.UserService;
import br.com.netflics.util.MockMvcTestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserErrorMockTest {

	static MockHttpSession mockHttpSession = MockMvcTestUtil.getSession();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService service;
	@MockBean
	private SpringSecurityUserContext context;

	@Test
	public void errorGetitingUserById() throws Exception {
		when(service.get(any(Integer.class), any(Tenant.class))).thenThrow(new RuntimeException("Error Getting User"));
		when(context.getTenant()).thenReturn(new Tenant());
		this.mockMvc.perform(get("/rs/crud/users/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting User")));
	}

	@Test
	public void errorGetitingAllPagerUser() throws Exception {
		when(service.get(any(SearchParameters.class),any(Tenant.class))).thenThrow(new RuntimeException("Error Getting User"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(get("/rs/crud/users").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting User")));
	}

	@Test
	public void errorSavingUser() throws Exception {
		when(service.save(any(User.class))).thenThrow(new RuntimeException("Error creating User"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(post("/rs/crud/users").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error creating User")));
	}
	
	@Test
	public void errorSavingWithValidationUser() throws Exception {
		when(service.save(any(User.class))).thenThrow(new ValidationException("Error creating-validating User"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(post("/rs/crud/users").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error creating-validating User")));
	}
	
	@Test
	public void errorUpdatingUser() throws Exception {
		when(service.update(any(User.class))).thenThrow(new RuntimeException("Error updating User"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(put("/rs/crud/users/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error updating User")));
	}
	
	@Test
	public void errorUpdatingWithValidationUser() throws Exception {
		when(service.update(any(User.class))).thenThrow(new ValidationException("Error updating-validating User"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(put("/rs/crud/users/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error updating-validating User")));
	}

	@Test
	public void errorDeletingUser() throws Exception {
		when(service.delete(any(Integer.class),any(Tenant.class))).thenThrow(new RuntimeException("Error removing User"));
		when(context.getTenant()).thenReturn(new Tenant());
		this.mockMvc.perform(delete("/rs/crud/users/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error removing User")));
	}

}