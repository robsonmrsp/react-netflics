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
import br.com.netflics.model.Critico;
import br.com.netflics.fixture.FixtureUtils;
import br.com.six2six.fixturefactory.Fixture;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("classpath:init-data-Critico.sql")
public class CriticoControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	private static final String URL = "/rs/crud/criticos";

	@BeforeClass
	public static void setUp() {
		FixtureUtils.init();
	}

	@Before
	public void before() {
	}

	@Test
	public void testAddCritico() throws Exception {

		Critico critico = Fixture.from(Critico.class).gimme("novo");
		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Critico> responseEntity = withBasicAuth.postForEntity(URL, critico, Critico.class);

		HttpStatus status = responseEntity.getStatusCode();
		Critico resultCritico = responseEntity.getBody();

		assertEquals("Incorrect Response Status: ", HttpStatus.CREATED, status);
		assertNotNull("A not null gender should be returned: ", resultCritico);
		assertNotNull("A not null gender identifier should be returned:", resultCritico.getId());
	}

	@Test
	public void testGetCritico() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Critico> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Critico.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();
		Critico resultCritico = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultCritico);
		assertEquals("A id gender == 1 must be returned: ", resultCritico.getId(), new Integer(1));
	}

	@Test
	public void testGetPagerCritico() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager> responseEntity = withBasicAuth.getForEntity(URL, Pager.class);

		HttpStatus status = responseEntity.getStatusCode();
		Pager<Critico> resultPagerCritico = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultPagerCritico);
	}

	@Test
	public void testGetCriticoNotExist() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Critico> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Critico.class, new Long(100));

		HttpStatus status = responseEntity.getStatusCode();
		Critico resultCritico = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND, status);
		assertNull(resultCritico);
	}

	@Test
	public void testGetCriticoByParameter() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager<Critico>> responseEntity = withBasicAuth.exchange(URL + "?nome={nome}", HttpMethod.GET, null, new ParameterizedTypeReference<Pager<Critico>>() {}, "nome critico1");
		Pager<Critico> criticos = responseEntity.getBody();
		HttpStatus status = responseEntity.getStatusCode();

		criticos.getItems().forEach(new Consumer<Critico>() {
			@Override
			public void accept(Critico critico) {
				assertEquals("A not null Critico should be returned white the 'name' = 'nome critico1'", critico.getNome(), "nome critico1");
			}
		});

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertTrue("A Array of Critico should be returned ", criticos.getItems().size() > 0);
	}
	
	@Test
	public void testUpdateCritico() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		Critico critico = Fixture.from(Critico.class).gimme("novo");
		critico.setId(1);

		HttpEntity<Critico> requestEntity = new HttpEntity<Critico>(critico);

		ResponseEntity<Critico> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.PUT, requestEntity, Critico.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);

	}

	@Test
	public void testDeleteCritico() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Boolean> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.DELETE, null, Boolean.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		ResponseEntity<Critico> responseTesteDelete = withBasicAuth.getForEntity(URL + "/{id}", Critico.class, new Integer(1));

		HttpStatus responseTesteDeleteStatus = responseTesteDelete.getStatusCode();
		Critico resultCritico = responseTesteDelete.getBody();

		assertEquals("Incorrect Response Status after delete the critico id = 1", HttpStatus.NOT_FOUND, responseTesteDeleteStatus);
		assertNull(resultCritico);

		assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT, status);

	}
}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06