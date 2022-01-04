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

import br.com.netflics.json.JsonClassificacao;
import br.com.netflics.model.Classificacao;
import br.com.netflics.rs.ClassificacaoController;
import br.com.netflics.service.ClassificacaoService;
import br.com.netflics.util.MockMvcTestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(ClassificacaoController.class)
public class ClassificacaoErrorMockTest {

	static MockHttpSession mockHttpSession = MockMvcTestUtil.getSession();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClassificacaoService service;
	@MockBean
	private SpringSecurityUserContext context;

	@Test
	public void errorGetitingClassificacaoById() throws Exception {
		when(service.get(any(Integer.class), any(Tenant.class))).thenThrow(new RuntimeException("Error Getting Classificacao"));
		when(context.getTenant()).thenReturn(new Tenant());
		this.mockMvc.perform(get("/rs/crud/classificacaos/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Classificacao")));
	}

	@Test
	public void errorGetitingAllPagerClassificacao() throws Exception {
		when(service.get(any(SearchParameters.class),any(Tenant.class))).thenThrow(new RuntimeException("Error Getting Classificacao"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(get("/rs/crud/classificacaos").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Classificacao")));
	}

	@Test
	public void errorSavingClassificacao() throws Exception {
		when(service.save(any(Classificacao.class))).thenThrow(new RuntimeException("Error creating Classificacao"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(post("/rs/crud/classificacaos").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error creating Classificacao")));
	}
	
	@Test
	public void errorSavingWithValidationClassificacao() throws Exception {
		when(service.save(any(Classificacao.class))).thenThrow(new ValidationException("Error creating-validating Classificacao"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(post("/rs/crud/classificacaos").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error creating-validating Classificacao")));
	}
	
	@Test
	public void errorUpdatingClassificacao() throws Exception {
		when(service.update(any(Classificacao.class))).thenThrow(new RuntimeException("Error updating Classificacao"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(put("/rs/crud/classificacaos/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error updating Classificacao")));
	}
	
	@Test
	public void errorUpdatingWithValidationClassificacao() throws Exception {
		when(service.update(any(Classificacao.class))).thenThrow(new ValidationException("Error updating-validating Classificacao"));
		when(context.getTenant()).thenReturn(new Tenant());

		this.mockMvc.perform(put("/rs/crud/classificacaos/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error updating-validating Classificacao")));
	}

	@Test
	public void errorDeletingClassificacao() throws Exception {
		when(service.delete(any(Integer.class),any(Tenant.class))).thenThrow(new RuntimeException("Error removing Classificacao"));
		when(context.getTenant()).thenReturn(new Tenant());
		this.mockMvc.perform(delete("/rs/crud/classificacaos/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error removing Classificacao")));
	}

}