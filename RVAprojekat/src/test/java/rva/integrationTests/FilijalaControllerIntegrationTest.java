package rva.integrationTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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

import rva.models.Banka;
import rva.models.Filijala;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FilijalaControllerIntegrationTest {
	
	@Autowired
	TestRestTemplate template;
	
	int highestId;
	
	
	 void createHighestId() {
		ResponseEntity<List<Filijala>> response = template.exchange("/filijala", HttpMethod.GET, null, new ParameterizedTypeReference<List<Filijala>>() {});
		ArrayList<Filijala> list = (ArrayList<Filijala>)response.getBody();
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
	void testGetAllFilijalas() {
	ResponseEntity<List<Filijala>> response = template.exchange("/filijala", HttpMethod.GET, null, new ParameterizedTypeReference<List<Filijala>>() {});
		
	int statusCode = response.getStatusCode().value();
	List<Filijala> filijala = response.getBody();
	
	assertEquals(200, statusCode);
	assertTrue(!filijala.isEmpty());
	}
	@Test
	@Order(2)
	void testGetFilijalaById() {
		int id = 1;
		ResponseEntity<Filijala> response = template.getForEntity("/filijala/id/" + id, Filijala.class);
		
		int statusCode = response.getStatusCode().value();
		Filijala filijala = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(filijala);
		assertEquals(id, filijala.getId());
	}
	@Test
	@Order(3)
	void testGetFilijalaByPosedujeSef() {
		boolean posedujeSef = true;
		ResponseEntity<List<Filijala>> response = template.exchange("/filijala/posedujeSef/"+posedujeSef, HttpMethod.GET,null,
																							new ParameterizedTypeReference<List<Filijala>>() {});
		int statusCode = response.getStatusCode().value();
		List<Filijala> filijala = response.getBody();
		assertEquals(200,statusCode);
		assertTrue(!filijala.isEmpty());
		for(Filijala f: filijala) {
		assertTrue(f.getPosedujeSef());
		}
		
	}
	@Test
	@Order(4)
	void testCreateFilijala() {
		Filijala filijala = new Filijala();
		filijala.setAdresa("POST test");  
		filijala.setBrojPultova(10);
		filijala.setPosedujeSef(true);
		
		HttpEntity<Filijala> entity = new HttpEntity<Filijala>(filijala);
		createHighestId();
		
		ResponseEntity<Filijala> response = template.exchange("/filijala", HttpMethod.POST,entity,Filijala.class);
		
		int statusCode = response.getStatusCode().value();
		Filijala createdFilijala = response.getBody();
		
		assertEquals(201,statusCode);
		assertEquals(highestId,createdFilijala.getId());
		assertEquals(filijala.getAdresa(),createdFilijala.getAdresa());
		assertEquals(filijala.getBrojPultova(),createdFilijala.getBrojPultova());
		assertEquals(filijala.getPosedujeSef(),createdFilijala.getPosedujeSef());
		assertEquals("/filijala/"+highestId,response.getHeaders().getLocation().getPath());
	}
	@Test
	@Order(5)
	void testUpdateFilijala() {
		Filijala filijala = new Filijala();
		filijala.setAdresa("PUT test");   
		filijala.setBrojPultova(5);
		filijala.setPosedujeSef(true);
		
		HttpEntity<Filijala> entity = new HttpEntity<Filijala>(filijala);
		getHighestId();
		
		ResponseEntity<Filijala> response = template.exchange("/filijala/id/"+highestId, HttpMethod.PUT,entity,Filijala.class);
	
		int statusCode = response.getStatusCode().value();
		Filijala updatedFilijala = response.getBody();
		
		assertEquals(200,statusCode);
		assertEquals(highestId,updatedFilijala.getId());
		assertEquals(filijala.getAdresa(),updatedFilijala.getAdresa());
		assertEquals(filijala.getPosedujeSef(),updatedFilijala.getPosedujeSef());
		assertEquals(filijala.getBrojPultova(),updatedFilijala.getBrojPultova());
	}
	@Test
	@Order(6)
	void testDeleteFilijala() {
		getHighestId();
		ResponseEntity<String> response = template.exchange("/filijala/id/"+highestId, HttpMethod.DELETE,null,String.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertTrue(response.getBody().contains("has been succesfully deleted"));
	}
	@Test
	@Order(7)
	void testGetFilijalaByBanka() {
		long bankaId = 1;
		ResponseEntity<List<Filijala>> response = template.exchange("/filijala/banka/" + bankaId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Filijala>>(){});
		int statusCode = response.getStatusCode().value();
		List<Filijala> filijale =  response.getBody();
		
		assertEquals(200, statusCode );
		assertNotNull(filijale.get(0));
		for(Filijala f: filijale) {
			assertTrue(f.getBanka().getId() == 1);
		}		
	}
}
