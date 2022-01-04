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
import br.com.netflics.model.Genero;
import br.com.netflics.fixture.FixtureUtils;
import br.com.six2six.fixturefactory.Fixture;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("classpath:init-data-Genero.sql")
public class GeneroControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	private static final String URL = "/rs/crud/generos";

	@BeforeClass
	public static void setUp() {
		FixtureUtils.init();
	}

	@Before
	public void before() {
	}

	@Test
	public void testAddGenero() throws Exception {

		Genero genero = Fixture.from(Genero.class).gimme("novo");
		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Genero> responseEntity = withBasicAuth.postForEntity(URL, genero, Genero.class);

		HttpStatus status = responseEntity.getStatusCode();
		Genero resultGenero = responseEntity.getBody();

		assertEquals("Incorrect Response Status: ", HttpStatus.CREATED, status);
		assertNotNull("A not null gender should be returned: ", resultGenero);
		assertNotNull("A not null gender identifier should be returned:", resultGenero.getId());
	}

	@Test
	public void testGetGenero() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Genero> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Genero.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();
		Genero resultGenero = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultGenero);
		assertEquals("A id gender == 1 must be returned: ", resultGenero.getId(), new Integer(1));
	}

	@Test
	public void testGetPagerGenero() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager> responseEntity = withBasicAuth.getForEntity(URL, Pager.class);

		HttpStatus status = responseEntity.getStatusCode();
		Pager<Genero> resultPagerGenero = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultPagerGenero);
	}

	@Test
	public void testGetGeneroNotExist() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Genero> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Genero.class, new Long(100));

		HttpStatus status = responseEntity.getStatusCode();
		Genero resultGenero = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND, status);
		assertNull(resultGenero);
	}

	@Test
	public void testGetGeneroByParameter() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager<Genero>> responseEntity = withBasicAuth.exchange(URL + "?nome={nome}", HttpMethod.GET, null, new ParameterizedTypeReference<Pager<Genero>>() {}, "nome genero1");
		Pager<Genero> generos = responseEntity.getBody();
		HttpStatus status = responseEntity.getStatusCode();

		generos.getItems().forEach(new Consumer<Genero>() {
			@Override
			public void accept(Genero genero) {
				assertEquals("A not null Genero should be returned white the 'name' = 'nome genero1'", genero.getNome(), "nome genero1");
			}
		});

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertTrue("A Array of Genero should be returned ", generos.getItems().size() > 0);
	}
	
	@Test
	public void testUpdateGenero() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		Genero genero = Fixture.from(Genero.class).gimme("novo");
		genero.setId(1);

		HttpEntity<Genero> requestEntity = new HttpEntity<Genero>(genero);

		ResponseEntity<Genero> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.PUT, requestEntity, Genero.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);

	}

	@Test
	public void testDeleteGenero() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Boolean> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.DELETE, null, Boolean.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		ResponseEntity<Genero> responseTesteDelete = withBasicAuth.getForEntity(URL + "/{id}", Genero.class, new Integer(1));

		HttpStatus responseTesteDeleteStatus = responseTesteDelete.getStatusCode();
		Genero resultGenero = responseTesteDelete.getBody();

		assertEquals("Incorrect Response Status after delete the genero id = 1", HttpStatus.NOT_FOUND, responseTesteDeleteStatus);
		assertNull(resultGenero);

		assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT, status);

	}
}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06