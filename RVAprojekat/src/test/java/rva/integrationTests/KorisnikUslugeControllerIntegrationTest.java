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


import rva.models.KorisnikUsluge;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class KorisnikUslugeControllerIntegrationTest {

	@Autowired
	TestRestTemplate template;
	int highestId;
	
	void createHighestId() {
		ResponseEntity<List<KorisnikUsluge>> response = template.exchange ("/korisnikusluge", HttpMethod.GET, null, new ParameterizedTypeReference<List<KorisnikUsluge>>() {});
		ArrayList<KorisnikUsluge> list = (ArrayList<KorisnikUsluge>)response.getBody();
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
	void testGetAllKorisnikUsluge() {
		
		 ResponseEntity<List<KorisnikUsluge>> response = template.exchange("/korisnikusluge", HttpMethod.GET, null, new ParameterizedTypeReference<List<KorisnikUsluge>>() {});
		
	int statusCode = response.getStatusCode().value();
	List<KorisnikUsluge> korisnikusluge = response.getBody();
	
	assertEquals(200, statusCode);
	assertTrue(!korisnikusluge.isEmpty());
	
	}
	
	@Test
	@Order(2)
	void testGetKorisnikUslugeById(){
		
		 int id = 1;
		ResponseEntity<KorisnikUsluge> response = template.getForEntity("/korisnikusluge/id/" + id, KorisnikUsluge.class);
		
		int statusCode = response.getStatusCode().value();
		KorisnikUsluge korisnikusluge = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(korisnikusluge);
		assertEquals(id, korisnikusluge.getId());
		
	}
	@Test
	@Order(3)
	 	void testGetKorisnikUslugeByMaticniBroj() {
		String maticniBroj = "2007002698512";
		ResponseEntity<List<KorisnikUsluge>> response = template.exchange("/korisnikusluge/maticniBroj/"+maticniBroj, HttpMethod.GET,null,
																							new ParameterizedTypeReference<List<KorisnikUsluge>>() {});
		int statusCode = response.getStatusCode().value();
		List<KorisnikUsluge> korisnikusluge = response.getBody();
		String mbr= korisnikusluge.get(0).getMaticniBroj();
		assertEquals(200,statusCode);
		assertTrue(!korisnikusluge.isEmpty());
		assertTrue(mbr.startsWith(maticniBroj));
    }
	@Test
	@Order(4)
	void testCreateKorisnikUsluge() {
		KorisnikUsluge korisnikusluge = new KorisnikUsluge();
		korisnikusluge.setIme("POST test");
		korisnikusluge.setMaticniBroj("POST maticni broj test");
		korisnikusluge.setPrezime("Post prezime test");
	
		HttpEntity<KorisnikUsluge> entity = new HttpEntity<KorisnikUsluge>(korisnikusluge);
		createHighestId();
		
		ResponseEntity<KorisnikUsluge> response = template.exchange("/korisnikusluge", HttpMethod.POST,entity,KorisnikUsluge.class);
		
		int statusCode = response.getStatusCode().value();
		KorisnikUsluge createdKorisnikUsluge = response.getBody();
		
		assertEquals(201,statusCode);
		assertEquals(highestId,createdKorisnikUsluge.getId());
		assertEquals(korisnikusluge.getIme(),createdKorisnikUsluge.getIme());
		assertEquals(korisnikusluge.getMaticniBroj(),createdKorisnikUsluge.getMaticniBroj());
		assertEquals(korisnikusluge.getPrezime(),createdKorisnikUsluge.getPrezime());
		assertEquals("/korisnikusluge/"+highestId,response.getHeaders().getLocation().getPath());
	}
	@Test
	@Order(5)
	void testUpdateKorisnikUsluge() {
		
		KorisnikUsluge korisnikusluge = new KorisnikUsluge();
		korisnikusluge.setIme("PUT test");
		korisnikusluge.setMaticniBroj("PUT maticni broj test");
		korisnikusluge.setPrezime("PUT prezime test");
		
		HttpEntity<KorisnikUsluge> entity = new HttpEntity<KorisnikUsluge>(korisnikusluge);
		getHighestId();
		
		ResponseEntity<KorisnikUsluge> response = template.exchange("/korisnikusluge/id/"+highestId, HttpMethod.PUT,entity,KorisnikUsluge.class);
		
		int statusCode = response.getStatusCode().value();
		KorisnikUsluge updatedKorisnikUsluge = response.getBody();
		
		assertEquals(200,statusCode);
		assertEquals(highestId,updatedKorisnikUsluge.getId());
		assertEquals(korisnikusluge.getIme(),updatedKorisnikUsluge.getIme());
		assertEquals(korisnikusluge.getPrezime(),updatedKorisnikUsluge.getPrezime());
		assertEquals(korisnikusluge.getMaticniBroj(),updatedKorisnikUsluge.getMaticniBroj()); 
		 
	}
	@Test
	@Order(6)
	void testDeleteKorisnikUsluge() {
		
		getHighestId();
		ResponseEntity<String> response = template.exchange("/korisnikusluge/id/"+highestId, HttpMethod.DELETE,null,String.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertTrue(response.getBody().contains("has been succesfully deleted")); 
		 
	}


}
