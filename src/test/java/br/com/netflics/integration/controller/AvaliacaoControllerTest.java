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
import br.com.netflics.model.Avaliacao;
import br.com.netflics.fixture.FixtureUtils;
import br.com.six2six.fixturefactory.Fixture;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("classpath:init-data-Avaliacao.sql")
public class AvaliacaoControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	private static final String URL = "/rs/crud/avaliacaos";

	@BeforeClass
	public static void setUp() {
		FixtureUtils.init();
	}

	@Before
	public void before() {
	}

	@Test
	public void testAddAvaliacao() throws Exception {

		Avaliacao avaliacao = Fixture.from(Avaliacao.class).gimme("novo");
		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Avaliacao> responseEntity = withBasicAuth.postForEntity(URL, avaliacao, Avaliacao.class);

		HttpStatus status = responseEntity.getStatusCode();
		Avaliacao resultAvaliacao = responseEntity.getBody();

		assertEquals("Incorrect Response Status: ", HttpStatus.CREATED, status);
		assertNotNull("A not null gender should be returned: ", resultAvaliacao);
		assertNotNull("A not null gender identifier should be returned:", resultAvaliacao.getId());
	}

	@Test
	public void testGetAvaliacao() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Avaliacao> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Avaliacao.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();
		Avaliacao resultAvaliacao = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultAvaliacao);
		assertEquals("A id gender == 1 must be returned: ", resultAvaliacao.getId(), new Integer(1));
	}

	@Test
	public void testGetPagerAvaliacao() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager> responseEntity = withBasicAuth.getForEntity(URL, Pager.class);

		HttpStatus status = responseEntity.getStatusCode();
		Pager<Avaliacao> resultPagerAvaliacao = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultPagerAvaliacao);
	}

	@Test
	public void testGetAvaliacaoNotExist() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Avaliacao> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Avaliacao.class, new Long(100));

		HttpStatus status = responseEntity.getStatusCode();
		Avaliacao resultAvaliacao = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND, status);
		assertNull(resultAvaliacao);
	}

	@Test
	public void testGetAvaliacaoByParameter() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager<Avaliacao>> responseEntity = withBasicAuth.exchange(URL + "?nO_NAME={nO_NAME}", HttpMethod.GET, null, new ParameterizedTypeReference<Pager<Avaliacao>>() {}, "nO_NAME avaliacao1");
		Pager<Avaliacao> avaliacaos = responseEntity.getBody();
		HttpStatus status = responseEntity.getStatusCode();

		avaliacaos.getItems().forEach(new Consumer<Avaliacao>() {
			@Override
			public void accept(Avaliacao avaliacao) {
			}
		});

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertTrue("A Array of Avaliacao should be returned ", avaliacaos.getItems().size() > 0);
	}
	
	@Test
	public void testUpdateAvaliacao() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		Avaliacao avaliacao = Fixture.from(Avaliacao.class).gimme("novo");
		avaliacao.setId(1);

		HttpEntity<Avaliacao> requestEntity = new HttpEntity<Avaliacao>(avaliacao);

		ResponseEntity<Avaliacao> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.PUT, requestEntity, Avaliacao.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);

	}

	@Test
	public void testDeleteAvaliacao() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Boolean> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.DELETE, null, Boolean.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		ResponseEntity<Avaliacao> responseTesteDelete = withBasicAuth.getForEntity(URL + "/{id}", Avaliacao.class, new Integer(1));

		HttpStatus responseTesteDeleteStatus = responseTesteDelete.getStatusCode();
		Avaliacao resultAvaliacao = responseTesteDelete.getBody();

		assertEquals("Incorrect Response Status after delete the avaliacao id = 1", HttpStatus.NOT_FOUND, responseTesteDeleteStatus);
		assertNull(resultAvaliacao);

		assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT, status);

	}
}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06