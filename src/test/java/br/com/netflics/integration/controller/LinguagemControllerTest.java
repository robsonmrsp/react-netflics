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
import br.com.netflics.model.Linguagem;
import br.com.netflics.fixture.FixtureUtils;
import br.com.six2six.fixturefactory.Fixture;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("classpath:init-data-Linguagem.sql")
public class LinguagemControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	private static final String URL = "/rs/crud/linguagems";

	@BeforeClass
	public static void setUp() {
		FixtureUtils.init();
	}

	@Before
	public void before() {
	}

	@Test
	public void testAddLinguagem() throws Exception {

		Linguagem linguagem = Fixture.from(Linguagem.class).gimme("novo");
		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Linguagem> responseEntity = withBasicAuth.postForEntity(URL, linguagem, Linguagem.class);

		HttpStatus status = responseEntity.getStatusCode();
		Linguagem resultLinguagem = responseEntity.getBody();

		assertEquals("Incorrect Response Status: ", HttpStatus.CREATED, status);
		assertNotNull("A not null gender should be returned: ", resultLinguagem);
		assertNotNull("A not null gender identifier should be returned:", resultLinguagem.getId());
	}

	@Test
	public void testGetLinguagem() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Linguagem> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Linguagem.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();
		Linguagem resultLinguagem = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultLinguagem);
		assertEquals("A id gender == 1 must be returned: ", resultLinguagem.getId(), new Integer(1));
	}

	@Test
	public void testGetPagerLinguagem() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager> responseEntity = withBasicAuth.getForEntity(URL, Pager.class);

		HttpStatus status = responseEntity.getStatusCode();
		Pager<Linguagem> resultPagerLinguagem = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultPagerLinguagem);
	}

	@Test
	public void testGetLinguagemNotExist() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Linguagem> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Linguagem.class, new Long(100));

		HttpStatus status = responseEntity.getStatusCode();
		Linguagem resultLinguagem = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND, status);
		assertNull(resultLinguagem);
	}

	@Test
	public void testGetLinguagemByParameter() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager<Linguagem>> responseEntity = withBasicAuth.exchange(URL + "?nome={nome}", HttpMethod.GET, null, new ParameterizedTypeReference<Pager<Linguagem>>() {}, "nome linguagem1");
		Pager<Linguagem> linguagems = responseEntity.getBody();
		HttpStatus status = responseEntity.getStatusCode();

		linguagems.getItems().forEach(new Consumer<Linguagem>() {
			@Override
			public void accept(Linguagem linguagem) {
				assertEquals("A not null Linguagem should be returned white the 'name' = 'nome linguagem1'", linguagem.getNome(), "nome linguagem1");
			}
		});

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertTrue("A Array of Linguagem should be returned ", linguagems.getItems().size() > 0);
	}
	
	@Test
	public void testUpdateLinguagem() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		Linguagem linguagem = Fixture.from(Linguagem.class).gimme("novo");
		linguagem.setId(1);

		HttpEntity<Linguagem> requestEntity = new HttpEntity<Linguagem>(linguagem);

		ResponseEntity<Linguagem> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.PUT, requestEntity, Linguagem.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);

	}

	@Test
	public void testDeleteLinguagem() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Boolean> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.DELETE, null, Boolean.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		ResponseEntity<Linguagem> responseTesteDelete = withBasicAuth.getForEntity(URL + "/{id}", Linguagem.class, new Integer(1));

		HttpStatus responseTesteDeleteStatus = responseTesteDelete.getStatusCode();
		Linguagem resultLinguagem = responseTesteDelete.getBody();

		assertEquals("Incorrect Response Status after delete the linguagem id = 1", HttpStatus.NOT_FOUND, responseTesteDeleteStatus);
		assertNull(resultLinguagem);

		assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT, status);

	}
}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06