/** generated: 23 de jun de 2021 23:11:06 **/
package br.com.netflics.integration.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.function.Consumer;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.netflics.core.persistence.pagination.Pager;
import br.com.netflics.model.Visualizacao;
import br.com.netflics.fixture.FixtureUtils;
import br.com.six2six.fixturefactory.Fixture;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("classpath:init-data-Visualizacao.sql")
public class VisualizacaoControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	private static final String URL = "/rs/crud/visualizacaos";

	@BeforeClass
	public static void setUp() {
		FixtureUtils.init();
	}

	@Before
	public void before() {
	}

	@Test
	public void testAddVisualizacao() throws Exception {

		Visualizacao visualizacao = Fixture.from(Visualizacao.class).gimme("novo");
		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Visualizacao> responseEntity = withBasicAuth.postForEntity(URL, visualizacao, Visualizacao.class);

		HttpStatus status = responseEntity.getStatusCode();
		Visualizacao resultVisualizacao = responseEntity.getBody();

		assertEquals("Incorrect Response Status: ", HttpStatus.CREATED, status);
		assertNotNull("A not null gender should be returned: ", resultVisualizacao);
		assertNotNull("A not null gender identifier should be returned:", resultVisualizacao.getId());
	}

	@Test
	public void testGetVisualizacao() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Visualizacao> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Visualizacao.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();
		Visualizacao resultVisualizacao = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultVisualizacao);
		assertEquals("A id gender == 1 must be returned: ", resultVisualizacao.getId(), new Integer(1));
	}

	@Test
	public void testGetPagerVisualizacao() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager> responseEntity = withBasicAuth.getForEntity(URL, Pager.class);

		HttpStatus status = responseEntity.getStatusCode();
		Pager<Visualizacao> resultPagerVisualizacao = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultPagerVisualizacao);
	}

	@Test
	public void testGetVisualizacaoNotExist() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Visualizacao> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Visualizacao.class, new Long(100));

		HttpStatus status = responseEntity.getStatusCode();
		Visualizacao resultVisualizacao = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND, status);
		assertNull(resultVisualizacao);
	}

	@Test
	public void testGetVisualizacaoByParameter() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager<Visualizacao>> responseEntity = withBasicAuth.exchange(URL + "?percentualAssistido={percentualAssistido}", HttpMethod.GET, null, new ParameterizedTypeReference<Pager<Visualizacao>>() {}, "percentualAssistido visualizacao1");
		Pager<Visualizacao> visualizacaos = responseEntity.getBody();
		HttpStatus status = responseEntity.getStatusCode();

		visualizacaos.getItems().forEach(new Consumer<Visualizacao>() {
			@Override
			public void accept(Visualizacao visualizacao) {
				assertEquals("A not null Visualizacao should be returned white the 'name' = 'percentualAssistido visualizacao1'", visualizacao.getPercentualAssistido(), "percentualAssistido visualizacao1");
			}
		});

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertTrue("A Array of Visualizacao should be returned ", visualizacaos.getItems().size() > 0);
	}
	
	@Test
	public void testUpdateVisualizacao() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		Visualizacao visualizacao = Fixture.from(Visualizacao.class).gimme("novo");
		visualizacao.setId(1);

		HttpEntity<Visualizacao> requestEntity = new HttpEntity<Visualizacao>(visualizacao);

		ResponseEntity<Visualizacao> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.PUT, requestEntity, Visualizacao.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);

	}

	@Test
	public void testDeleteVisualizacao() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Boolean> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.DELETE, null, Boolean.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		ResponseEntity<Visualizacao> responseTesteDelete = withBasicAuth.getForEntity(URL + "/{id}", Visualizacao.class, new Integer(1));

		HttpStatus responseTesteDeleteStatus = responseTesteDelete.getStatusCode();
		Visualizacao resultVisualizacao = responseTesteDelete.getBody();

		assertEquals("Incorrect Response Status after delete the visualizacao id = 1", HttpStatus.NOT_FOUND, responseTesteDeleteStatus);
		assertNull(resultVisualizacao);

		assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT, status);

	}
}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06