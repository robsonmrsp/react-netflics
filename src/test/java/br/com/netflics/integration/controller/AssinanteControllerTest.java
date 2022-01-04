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
import br.com.netflics.model.Assinante;
import br.com.netflics.fixture.FixtureUtils;
import br.com.six2six.fixturefactory.Fixture;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("classpath:init-data-Assinante.sql")
public class AssinanteControllerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	private static final String URL = "/rs/crud/assinantes";

	@BeforeClass
	public static void setUp() {
		FixtureUtils.init();
	}

	@Before
	public void before() {
	}

	@Test
	public void testAddAssinante() throws Exception {

		Assinante assinante = Fixture.from(Assinante.class).gimme("novo");
		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Assinante> responseEntity = withBasicAuth.postForEntity(URL, assinante, Assinante.class);

		HttpStatus status = responseEntity.getStatusCode();
		Assinante resultAssinante = responseEntity.getBody();

		assertEquals("Incorrect Response Status: ", HttpStatus.CREATED, status);
		assertNotNull("A not null gender should be returned: ", resultAssinante);
		assertNotNull("A not null gender identifier should be returned:", resultAssinante.getId());
	}

	@Test
	public void testGetAssinante() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Assinante> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Assinante.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();
		Assinante resultAssinante = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultAssinante);
		assertEquals("A id gender == 1 must be returned: ", resultAssinante.getId(), new Integer(1));
	}

	@Test
	public void testGetPagerAssinante() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager> responseEntity = withBasicAuth.getForEntity(URL, Pager.class);

		HttpStatus status = responseEntity.getStatusCode();
		Pager<Assinante> resultPagerAssinante = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertNotNull("A not null gender should be returned: ", resultPagerAssinante);
	}

	@Test
	public void testGetAssinanteNotExist() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Assinante> responseEntity = withBasicAuth.getForEntity(URL + "/{id}", Assinante.class, new Long(100));

		HttpStatus status = responseEntity.getStatusCode();
		Assinante resultAssinante = responseEntity.getBody();

		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND, status);
		assertNull(resultAssinante);
	}

	@Test
	public void testGetAssinanteByParameter() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Pager<Assinante>> responseEntity = withBasicAuth.exchange(URL + "?telefone={telefone}", HttpMethod.GET, null, new ParameterizedTypeReference<Pager<Assinante>>() {}, "telefone assinante1");
		Pager<Assinante> assinantes = responseEntity.getBody();
		HttpStatus status = responseEntity.getStatusCode();

		assinantes.getItems().forEach(new Consumer<Assinante>() {
			@Override
			public void accept(Assinante assinante) {
				assertEquals("A not null Assinante should be returned white the 'name' = 'telefone assinante1'", assinante.getTelefone(), "telefone assinante1");
			}
		});

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);
		assertTrue("A Array of Assinante should be returned ", assinantes.getItems().size() > 0);
	}
	
	@Test
	public void testUpdateAssinante() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		Assinante assinante = Fixture.from(Assinante.class).gimme("novo");
		assinante.setId(1);

		HttpEntity<Assinante> requestEntity = new HttpEntity<Assinante>(assinante);

		ResponseEntity<Assinante> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.PUT, requestEntity, Assinante.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		assertEquals("Incorrect Response Status", HttpStatus.OK, status);

	}

	@Test
	public void testDeleteAssinante() throws Exception {

		TestRestTemplate withBasicAuth = testRestTemplate.withBasicAuth("jsetup", "123456");

		ResponseEntity<Boolean> responseEntity = withBasicAuth.exchange(URL + "/{id}", HttpMethod.DELETE, null, Boolean.class, new Integer(1));

		HttpStatus status = responseEntity.getStatusCode();

		ResponseEntity<Assinante> responseTesteDelete = withBasicAuth.getForEntity(URL + "/{id}", Assinante.class, new Integer(1));

		HttpStatus responseTesteDeleteStatus = responseTesteDelete.getStatusCode();
		Assinante resultAssinante = responseTesteDelete.getBody();

		assertEquals("Incorrect Response Status after delete the assinante id = 1", HttpStatus.NOT_FOUND, responseTesteDeleteStatus);
		assertNull(resultAssinante);

		assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT, status);

	}
}
//generated by JSetup v0.95 :  at 23 de jun de 2021 23:11:06