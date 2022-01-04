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
import br.com.netflics.model.Ator;
import br.com.netflics.fixture.FixtureUtils;
import br.com.six2six.fixturefactory.Fixture;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("classpath:init-data-Ator.sql")
public class AtorControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	private static final String URL = "/rs/crud/ators";

	@BeforeClass
	public static void setUp() {
		FixtureUtils.init();
	}

	@Before
	public void before() {
	}

	@Test
	public void testAddAtor() throws Exception {

		Ator ator = Fixture.from(Ator.class).gimme("novo");
		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Ator> responseEntity = withBasicAuth.postForEntity(URL, ator, Ator.class);

		HttpStatus status = responseEntity.getStatusCode();
		Ator resultAtor = responseEntity.getBody();

		assertEquals("Incorrect Response Status: ", HttpStatus.CREATED, status);
		assertNotNull("A not null gender should be returned: ", resultAtor);
		assertNotNull("A not null gender identifier should be returned:", resultAtor.getId());
	}

	@Test
	public void testGetAtor() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Ator> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Ator.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();
		Ator resultAtor = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultAtor);
		assertEquals("A id gender == 1 must be returned: ", resultAtor.getId(), new Integer(1));
	}

	@Test
	public void testGetPagerAtor() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager> responseEntity = withBasicAuth.getForEntity(URL, Pager.class);

		HttpStatus status = responseEntity.getStatusCode();
		Pager<Ator> resultPagerAtor = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultPagerAtor);
	}

	@Test
	public void testGetAtorNotExist() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Ator> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Ator.class, new Long(100));

		HttpStatus status = responseEntity.getStatusCode();
		Ator resultAtor = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND, status);
		assertNull(resultAtor);
	}

	@Test
	public void testGetAtorByParameter() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager<Ator>> responseEntity = withBasicAuth.exchange(URL + "?biografia={biografia}", HttpMethod.GET, null, new ParameterizedTypeReference<Pager<Ator>>() {}, "biografia ator1");
		Pager<Ator> ators = responseEntity.getBody();
		HttpStatus status = responseEntity.getStatusCode();

		ators.getItems().forEach(new Consumer<Ator>() {
			@Override
			public void accept(Ator ator) {
				assertEquals("A not null Ator should be returned white the 'name' = 'biografia ator1'", ator.getBiografia(), "biografia ator1");
			}
		});

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertTrue("A Array of Ator should be returned ", ators.getItems().size() > 0);
	}
	
	@Test
	public void testUpdateAtor() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		Ator ator = Fixture.from(Ator.class).gimme("novo");
		ator.setId(1);

		HttpEntity<Ator> requestEntity = new HttpEntity<Ator>(ator);

		ResponseEntity<Ator> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.PUT, requestEntity, Ator.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);

	}

	@Test
	public void testDeleteAtor() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Boolean> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.DELETE, null, Boolean.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		ResponseEntity<Ator> responseTesteDelete = withBasicAuth.getForEntity(URL + "/{id}", Ator.class, new Integer(1));

		HttpStatus responseTesteDeleteStatus = responseTesteDelete.getStatusCode();
		Ator resultAtor = responseTesteDelete.getBody();

		assertEquals("Incorrect Response Status after delete the ator id = 1", HttpStatus.NOT_FOUND, responseTesteDeleteStatus);
		assertNull(resultAtor);

		assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT, status);

	}
}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06