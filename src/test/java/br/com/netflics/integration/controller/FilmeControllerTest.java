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
import br.com.netflics.model.Filme;
import br.com.netflics.fixture.FixtureUtils;
import br.com.six2six.fixturefactory.Fixture;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("classpath:init-data-Filme.sql")
public class FilmeControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	private static final String URL = "/rs/crud/filmes";

	@BeforeClass
	public static void setUp() {
		FixtureUtils.init();
	}

	@Before
	public void before() {
	}

	@Test
	public void testAddFilme() throws Exception {

		Filme filme = Fixture.from(Filme.class).gimme("novo");
		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Filme> responseEntity = withBasicAuth.postForEntity(URL, filme, Filme.class);

		HttpStatus status = responseEntity.getStatusCode();
		Filme resultFilme = responseEntity.getBody();

		assertEquals("Incorrect Response Status: ", HttpStatus.CREATED, status);
		assertNotNull("A not null gender should be returned: ", resultFilme);
		assertNotNull("A not null gender identifier should be returned:", resultFilme.getId());
	}

	@Test
	public void testGetFilme() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Filme> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Filme.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();
		Filme resultFilme = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultFilme);
		assertEquals("A id gender == 1 must be returned: ", resultFilme.getId(), new Integer(1));
	}

	@Test
	public void testGetPagerFilme() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager> responseEntity = withBasicAuth.getForEntity(URL, Pager.class);

		HttpStatus status = responseEntity.getStatusCode();
		Pager<Filme> resultPagerFilme = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultPagerFilme);
	}

	@Test
	public void testGetFilmeNotExist() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Filme> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Filme.class, new Long(100));

		HttpStatus status = responseEntity.getStatusCode();
		Filme resultFilme = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND, status);
		assertNull(resultFilme);
	}

	@Test
	public void testGetFilmeByParameter() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager<Filme>> responseEntity = withBasicAuth.exchange(URL + "?tituloOriginal={tituloOriginal}", HttpMethod.GET, null, new ParameterizedTypeReference<Pager<Filme>>() {}, "tituloOriginal filme1");
		Pager<Filme> filmes = responseEntity.getBody();
		HttpStatus status = responseEntity.getStatusCode();

		filmes.getItems().forEach(new Consumer<Filme>() {
			@Override
			public void accept(Filme filme) {
				assertEquals("A not null Filme should be returned white the 'name' = 'tituloOriginal filme1'", filme.getTituloOriginal(), "tituloOriginal filme1");
			}
		});

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertTrue("A Array of Filme should be returned ", filmes.getItems().size() > 0);
	}
	
	@Test
	public void testUpdateFilme() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		Filme filme = Fixture.from(Filme.class).gimme("novo");
		filme.setId(1);

		HttpEntity<Filme> requestEntity = new HttpEntity<Filme>(filme);

		ResponseEntity<Filme> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.PUT, requestEntity, Filme.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);

	}

	@Test
	public void testDeleteFilme() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Boolean> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.DELETE, null, Boolean.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		ResponseEntity<Filme> responseTesteDelete = withBasicAuth.getForEntity(URL + "/{id}", Filme.class, new Integer(1));

		HttpStatus responseTesteDeleteStatus = responseTesteDelete.getStatusCode();
		Filme resultFilme = responseTesteDelete.getBody();

		assertEquals("Incorrect Response Status after delete the filme id = 1", HttpStatus.NOT_FOUND, responseTesteDeleteStatus);
		assertNull(resultFilme);

		assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT, status);

	}
}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06