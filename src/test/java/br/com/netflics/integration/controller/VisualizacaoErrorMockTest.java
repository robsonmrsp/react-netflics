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

import br.com.netflics.json.JsonVisualizacao;
import br.com.netflics.model.Visualizacao;
import br.com.netflics.rs.VisualizacaoController;
import br.com.netflics.service.VisualizacaoService;
import br.com.netflics.util.MockMvcTestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(VisualizacaoController.class)
public class VisualizacaoErrorMockTest {

	static MockHttpSession mockHttpSession = MockMvcTestUtil.getSession();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private VisualizacaoService service;
	@MockBean
	private SpringSecurityUserContext context;

	@Test
	public void errorGetitingVisualizacaoById() throws Exception {
		when(service.get(any(Integer.class), any(Tenant.class))).thenThrow(new RuntimeException("Error Getting Visualizacao"));
		when(context.getTenant()).thenReturn(new Tenant());
		this.mockMvc.perform(get("/rs/crud/visualizacaos/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Visualizacao")));
	}

	@Test
	public void errorGetitingAllPagerVisualizacao() throws Exception {
		when(service.get(any(SearchParameters.class),any(Tenant.class))).thenThrow(new RuntimeException("Error Getting Visualizacao"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(get("/rs/crud/visualizacaos").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Visualizacao")));
	}

	@Test
	public void errorSavingVisualizacao() throws Exception {
		when(service.save(any(Visualizacao.class))).thenThrow(new RuntimeException("Error creating Visualizacao"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(post("/rs/crud/visualizacaos").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error creating Visualizacao")));
	}
	
	@Test
	public void errorSavingWithValidationVisualizacao() throws Exception {
		when(service.save(any(Visualizacao.class))).thenThrow(new ValidationException("Error creating-validating Visualizacao"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(post("/rs/crud/visualizacaos").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error creating-validating Visualizacao")));
	}
	
	@Test
	public void errorUpdatingVisualizacao() throws Exception {
		when(service.update(any(Visualizacao.class))).thenThrow(new RuntimeException("Error updating Visualizacao"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(put("/rs/crud/visualizacaos/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error updating Visualizacao")));
	}
	
	@Test
	public void errorUpdatingWithValidationVisualizacao() throws Exception {
		when(service.update(any(Visualizacao.class))).thenThrow(new ValidationException("Error updating-validating Visualizacao"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(put("/rs/crud/visualizacaos/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error updating-validating Visualizacao")));
	}

	@Test
	public void errorDeletingVisualizacao() throws Exception {
		when(service.delete(any(Integer.class),any(Tenant.class))).thenThrow(new RuntimeException("Error removing Visualizacao"));
		when(context.getTenant()).thenReturn(new Tenant());
		this.mockMvc.perform(delete("/rs/crud/visualizacaos/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error removing Visualizacao")));
	}

}