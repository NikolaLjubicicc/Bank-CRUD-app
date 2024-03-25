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
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import rva.models.Banka;
import rva.models.KorisnikUsluge;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class KorisnikUslugeControllerTest {

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
	
	//Test
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

}
