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
import br.com.netflics.model.Classificacao;
import br.com.netflics.fixture.FixtureUtils;
import br.com.six2six.fixturefactory.Fixture;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("classpath:init-data-Classificacao.sql")
public class ClassificacaoControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	private static final String URL = "/rs/crud/classificacaos";

	@BeforeClass
	public static void setUp() {
		FixtureUtils.init();
	}

	@Before
	public void before() {
	}

	@Test
	public void testAddClassificacao() throws Exception {

		Classificacao classificacao = Fixture.from(Classificacao.class).gimme("novo");
		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Classificacao> responseEntity = withBasicAuth.postForEntity(URL, classificacao, Classificacao.class);

		HttpStatus status = responseEntity.getStatusCode();
		Classificacao resultClassificacao = responseEntity.getBody();

		assertEquals("Incorrect Response Status: ", HttpStatus.CREATED, status);
		assertNotNull("A not null gender should be returned: ", resultClassificacao);
		assertNotNull("A not null gender identifier should be returned:", resultClassificacao.getId());
	}

	@Test
	public void testGetClassificacao() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Classificacao> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Classificacao.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();
		Classificacao resultClassificacao = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultClassificacao);
		assertEquals("A id gender == 1 must be returned: ", resultClassificacao.getId(), new Integer(1));
	}

	@Test
	public void testGetPagerClassificacao() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager> responseEntity = withBasicAuth.getForEntity(URL, Pager.class);

		HttpStatus status = responseEntity.getStatusCode();
		Pager<Classificacao> resultPagerClassificacao = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultPagerClassificacao);
	}

	@Test
	public void testGetClassificacaoNotExist() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Classificacao> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Classificacao.class, new Long(100));

		HttpStatus status = responseEntity.getStatusCode();
		Classificacao resultClassificacao = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND, status);
		assertNull(resultClassificacao);
	}

	@Test
	public void testGetClassificacaoByParameter() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager<Classificacao>> responseEntity = withBasicAuth.exchange(URL + "?nome={nome}", HttpMethod.GET, null, new ParameterizedTypeReference<Pager<Classificacao>>() {}, "nome classificacao1");
		Pager<Classificacao> classificacaos = responseEntity.getBody();
		HttpStatus status = responseEntity.getStatusCode();

		classificacaos.getItems().forEach(new Consumer<Classificacao>() {
			@Override
			public void accept(Classificacao classificacao) {
				assertEquals("A not null Classificacao should be returned white the 'name' = 'nome classificacao1'", classificacao.getNome(), "nome classificacao1");
			}
		});

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertTrue("A Array of Classificacao should be returned ", classificacaos.getItems().size() > 0);
	}
	
	@Test
	public void testUpdateClassificacao() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		Classificacao classificacao = Fixture.from(Classificacao.class).gimme("novo");
		classificacao.setId(1);

		HttpEntity<Classificacao> requestEntity = new HttpEntity<Classificacao>(classificacao);

		ResponseEntity<Classificacao> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.PUT, requestEntity, Classificacao.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);

	}

	@Test
	public void testDeleteClassificacao() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Boolean> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.DELETE, null, Boolean.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		ResponseEntity<Classificacao> responseTesteDelete = withBasicAuth.getForEntity(URL + "/{id}", Classificacao.class, new Integer(1));

		HttpStatus responseTesteDeleteStatus = responseTesteDelete.getStatusCode();
		Classificacao resultClassificacao = responseTesteDelete.getBody();

		assertEquals("Incorrect Response Status after delete the classificacao id = 1", HttpStatus.NOT_FOUND, responseTesteDeleteStatus);
		assertNull(resultClassificacao);

		assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT, status);

	}
}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06