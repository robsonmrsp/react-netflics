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

import br.com.netflics.json.JsonCritico;
import br.com.netflics.model.Critico;
import br.com.netflics.rs.CriticoController;
import br.com.netflics.service.CriticoService;
import br.com.netflics.util.MockMvcTestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(CriticoController.class)
public class CriticoErrorMockTest {

	static MockHttpSession mockHttpSession = MockMvcTestUtil.getSession();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CriticoService service;
	@MockBean
	private SpringSecurityUserContext context;

	@Test
	public void errorGetitingCriticoById() throws Exception {
		when(service.get(any(Integer.class), any(Tenant.class))).thenThrow(new RuntimeException("Error Getting Critico"));
		when(context.getTenant()).thenReturn(new Tenant());
		this.mockMvc.perform(get("/rs/crud/criticos/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Critico")));
	}

	@Test
	public void errorGetitingAllPagerCritico() throws Exception {
		when(service.get(any(SearchParameters.class),any(Tenant.class))).thenThrow(new RuntimeException("Error Getting Critico"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(get("/rs/crud/criticos").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Critico")));
	}

	@Test
	public void errorSavingCritico() throws Exception {
		when(service.save(any(Critico.class))).thenThrow(new RuntimeException("Error creating Critico"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(post("/rs/crud/criticos").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error creating Critico")));
	}
	
	@Test
	public void errorSavingWithValidationCritico() throws Exception {
		when(service.save(any(Critico.class))).thenThrow(new ValidationException("Error creating-validating Critico"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(post("/rs/crud/criticos").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error creating-validating Critico")));
	}
	
	@Test
	public void errorUpdatingCritico() throws Exception {
		when(service.update(any(Critico.class))).thenThrow(new RuntimeException("Error updating Critico"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(put("/rs/crud/criticos/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error updating Critico")));
	}
	
	@Test
	public void errorUpdatingWithValidationCritico() throws Exception {
		when(service.update(any(Critico.class))).thenThrow(new ValidationException("Error updating-validating Critico"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(put("/rs/crud/criticos/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error updating-validating Critico")));
	}

	@Test
	public void errorDeletingCritico() throws Exception {
		when(service.delete(any(Integer.class),any(Tenant.class))).thenThrow(new RuntimeException("Error removing Critico"));
		when(context.getTenant()).thenReturn(new Tenant());
		this.mockMvc.perform(delete("/rs/crud/criticos/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error removing Critico")));
	}

}