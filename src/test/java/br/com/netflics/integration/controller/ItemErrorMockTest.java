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

import br.com.netflics.json.JsonItem;
import br.com.netflics.model.Item;
import br.com.netflics.rs.ItemController;
import br.com.netflics.service.ItemService;
import br.com.netflics.util.MockMvcTestUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemErrorMockTest {

	static MockHttpSession mockHttpSession = MockMvcTestUtil.getSession();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ItemService service;
	@MockBean
	private SpringSecurityUserContext context;

	@Test
	public void errorGetitingItemById() throws Exception {
		when(service.get(any(Integer.class))).thenThrow(new RuntimeException("Error Getting Item"));
		this.mockMvc.perform(get("/rs/crud/items/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Item")));
	}

	@Test
	public void errorGetitingAllPagerItem() throws Exception {
		when(service.get(any(SearchParameters.class))).thenThrow(new RuntimeException("Error Getting Item"));

		this.mockMvc.perform(get("/rs/crud/items").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error Getting Item")));
	}

	@Test
	public void errorSavingItem() throws Exception {
		when(service.save(any(Item.class))).thenThrow(new RuntimeException("Error creating Item"));

		this.mockMvc.perform(post("/rs/crud/items").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error creating Item")));
	}
	
	@Test
	public void errorSavingWithValidationItem() throws Exception {
		when(service.save(any(Item.class))).thenThrow(new ValidationException("Error creating-validating Item"));

		this.mockMvc.perform(post("/rs/crud/items").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error creating-validating Item")));
	}
	
	@Test
	public void errorUpdatingItem() throws Exception {
		when(service.update(any(Item.class))).thenThrow(new RuntimeException("Error updating Item"));

		this.mockMvc.perform(put("/rs/crud/items/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error updating Item")));
	}
	
	@Test
	public void errorUpdatingWithValidationItem() throws Exception {
		when(service.update(any(Item.class))).thenThrow(new ValidationException("Error updating-validating Item"));

		this.mockMvc.perform(put("/rs/crud/items/1").session(mockHttpSession).contentType(MediaType.APPLICATION_JSON).content("{}"))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("Error updating-validating Item")));
	}

	@Test
	public void errorDeletingItem() throws Exception {
		when(service.delete(any(Integer.class))).thenThrow(new RuntimeException("Error removing Item"));
		this.mockMvc.perform(delete("/rs/crud/items/1").session(mockHttpSession))
			.andExpect(status().is5xxServerError())
			.andExpect(content().string(containsString("Error removing Item")));
	}

}