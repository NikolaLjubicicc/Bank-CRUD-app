package rva.integrationTests;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import rva.models.Usluga;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UslugaControllerIntegrationTest {

	 @Autowired
	TestRestTemplate template;
	
	int highestId;
	
	void createHighestId() {
		ResponseEntity<List<Usluga>> response = template.exchange("/usluga", HttpMethod.GET, null, new ParameterizedTypeReference<List<Usluga>>() {});
		ArrayList<Usluga> list = (ArrayList<Usluga>)response.getBody();
		for(int i = 0;i<list.size();i++) {
			if(highestId <=list.get(i).getId()) {
				highestId = list.get(i).getId()+1;
			}
		}
	}
	
	void getHighestId() {
		createHighestId();
		highestId--;
	} 
	 
	
	@Test
	@Order(1)
	void testgetAllUslugas() {
		
	ResponseEntity<List<Usluga>> response = template.exchange("/usluga", HttpMethod.GET, null, new ParameterizedTypeReference<List<Usluga>>() {});
		
	int statusCode = response.getStatusCode().value();
	List<Usluga> usluga = response.getBody();
	
	assertEquals(200, statusCode); 
	assertTrue(!usluga.isEmpty());
		 
	}
	@Test
	@Order(2)
	void testGetUslugaById() {
		
		int id = 1;
		ResponseEntity<Usluga> response = template.getForEntity("/usluga/id/" + id, Usluga.class);
		
		int statusCode = response.getStatusCode().value();
		Usluga usluga = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(usluga);
		assertEquals(id, usluga.getId()); 
		
	}
	@Test
	@Order(3)
	void testGetUslugaByProvizija() {
		
		double provizija = 1;
		ResponseEntity<List<Usluga>> response = template.exchange("/usluga/provizija/" + provizija, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Usluga>>(){});
		int statusCode = response.getStatusCode().value();
		List<Usluga> provizije = response.getBody();

		assertEquals(200, statusCode );
		assertNotNull(provizije.get(0));
		for(Usluga u: provizije) {
			assertTrue(u.getProvizija() > provizija);
		}
	
	}
	@Test
	@Order(4)
	void testCreateUsluga() {

		Usluga usluga = new Usluga();
		usluga.setProvizija(50);
		usluga.setNaziv("POST test");
		usluga.setOpisUsluge("POST opis usluge test");
		Date datumUgovora = new Date();
		usluga.setDatumUgovora(datumUgovora);
		HttpEntity<Usluga> entity = new HttpEntity<Usluga>(usluga);
		createHighestId();
		
		ResponseEntity<Usluga> response = template.exchange("/usluga", HttpMethod.POST,entity,Usluga.class);
		
		int statusCode = response.getStatusCode().value();
		Usluga createdUsluga = response.getBody();
		
		assertEquals(201,statusCode);
		assertEquals(highestId,createdUsluga.getId());
		assertEquals(usluga.getNaziv(),createdUsluga.getNaziv());
		assertEquals(usluga.getProvizija(),createdUsluga.getProvizija());
		assertEquals(usluga.getOpisUsluge(),createdUsluga.getOpisUsluge());
		assertEquals(datumUgovora,response.getBody().getDatumUgovora());
		assertEquals("/usluga/"+highestId,response.getHeaders().getLocation().getPath());
		
	}
	@Test
	@Order(5)
	void testUpdateUsluga() {
		
		Usluga usluga = new Usluga();
		usluga.setProvizija(51);
		usluga.setNaziv("PUT naziv");
		usluga.setOpisUsluge("PUT opis usluge test");
		
		
		HttpEntity<Usluga> entity = new HttpEntity<Usluga>(usluga);
		getHighestId();
		
		ResponseEntity<Usluga> response = template.exchange("/usluga/id/"+highestId, HttpMethod.PUT,entity,Usluga.class);
		
		int statusCode = response.getStatusCode().value();
		Usluga updatedUsluga = response.getBody();
		
		assertEquals(200,statusCode);
		assertEquals(highestId,updatedUsluga.getId());
		assertEquals(usluga.getNaziv(),updatedUsluga.getNaziv());
		assertEquals(usluga.getOpisUsluge(),updatedUsluga.getOpisUsluge());
		assertEquals(usluga.getProvizija(),updatedUsluga.getProvizija());
		 
	}
	@Test
	@Order(6)
	void testDeleteUsluga() {
		getHighestId();
		ResponseEntity<String> response = template.exchange("/usluga/id/"+highestId, HttpMethod.DELETE,null,String.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertTrue(response.getBody().contains("has been succesfully deleted"));
	}
	@Test
	@Order(7)
	void testGetUslugaByFilijala() {
		int filijalaId = 1;
		ResponseEntity<List<Usluga>> response = template.exchange("/usluga/filijala/" + filijalaId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Usluga>>(){});
		int statusCode = response.getStatusCode().value();
		List<Usluga> usluge =  response.getBody();
		
		assertEquals(200, statusCode );
		assertNotNull(usluge.get(0));
		for(Usluga u: usluge) {
			assertTrue(u.getFilijala().getId() == 1);
		}
	}
	@Test
	@Order(8)
	void testGetUslugaByKorisnikUsluge() {
		int korisnikUslugeId = 1;
		ResponseEntity<List<Usluga>> response = template.exchange("/usluga/korisnikusluge/" + korisnikUslugeId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Usluga>>(){});
		int statusCode = response.getStatusCode().value();
		List<Usluga> usluge =  response.getBody();
		
		assertEquals(200, statusCode );
		assertNotNull(usluge.get(0));
		for(Usluga u: usluge) {
			assertTrue(u.getKorisnikUsluge().getId() == 1);
		}
	}
	

}
