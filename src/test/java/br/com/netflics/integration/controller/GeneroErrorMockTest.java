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

import br.com.netflics.json.JsonGenero;
import br.com.netflics.model.Genero;
import br.com.netflics.rs.GeneroController;
import br.com.netflics.service.GeneroService;
import br.com.netflics.util.MockMvcTestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(GeneroController.class)
public class GeneroErrorMockTest {

	static MockHttpSession mockHttpSession = MockMvcTestUtil.getSession();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GeneroService service;
	@MockBean
	private SpringSecurityUserContext context;

	@Test
	public void errorGetitingGeneroById() throws Exception {
		when(service.get(any(Integer.class), any(Tenant.class))).thenThrow(new RuntimeException("Error Getting Genero"));
		when(context.getTenant()).thenReturn(new Tenant());
		this.mockMvc.perform(get("/rs/crud/generos/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Genero")));
	}

	@Test
	public void errorGetitingAllPagerGenero() throws Exception {
		when(service.get(any(SearchParameters.class),any(Tenant.class))).thenThrow(new RuntimeException("Error Getting Genero"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(get("/rs/crud/generos").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Genero")));
	}

	@Test
	public void errorSavingGenero() throws Exception {
		when(service.save(any(Genero.class))).thenThrow(new RuntimeException("Error creating Genero"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(post("/rs/crud/generos").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error creating Genero")));
	}
	
	@Test
	public void errorSavingWithValidationGenero() throws Exception {
		when(service.save(any(Genero.class))).thenThrow(new ValidationException("Error creating-validating Genero"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(post("/rs/crud/generos").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error creating-validating Genero")));
	}
	
	@Test
	public void errorUpdatingGenero() throws Exception {
		when(service.update(any(Genero.class))).thenThrow(new RuntimeException("Error updating Genero"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(put("/rs/crud/generos/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error updating Genero")));
	}
	
	@Test
	public void errorUpdatingWithValidationGenero() throws Exception {
		when(service.update(any(Genero.class))).thenThrow(new ValidationException("Error updating-validating Genero"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(put("/rs/crud/generos/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error updating-validating Genero")));
	}

	@Test
	public void errorDeletingGenero() throws Exception {
		when(service.delete(any(Integer.class),any(Tenant.class))).thenThrow(new RuntimeException("Error removing Genero"));
		when(context.getTenant()).thenReturn(new Tenant());
		this.mockMvc.perform(delete("/rs/crud/generos/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error removing Genero")));
	}

}