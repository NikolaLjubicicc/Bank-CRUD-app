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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BankaControllerIntegrationTest {

	@Autowired
	TestRestTemplate template;
	
	int highestId;
	
	void createHighestId() {
		ResponseEntity<List<Banka>> response = template.exchange("/banka", HttpMethod.GET, null, new ParameterizedTypeReference<List<Banka>>() {});
		ArrayList<Banka> list = (ArrayList<Banka>)response.getBody();
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
	void testGetAllBankas() {
		ResponseEntity<List<Banka>> response = template.exchange("/banka", HttpMethod.GET, null, new ParameterizedTypeReference<List<Banka>>() {});
		
	int statusCode = response.getStatusCode().value();
	List<Banka> banka = response.getBody();
	
	//Test
	assertEquals(200, statusCode);
	assertTrue(!banka.isEmpty());
	}
	
	@Test
	@Order(2)
	void testGetBankaById() {
		int id = 1;
		ResponseEntity<Banka> response = template.getForEntity("/banka/id/" + id, Banka.class);
		
		int statusCode = response.getStatusCode().value();
		Banka banka = response.getBody();
		
		assertEquals(200, statusCode);
		assertNotNull(banka);
		assertEquals(id, banka.getId());
	}
	@Test
	@Order(3)
	void testGetBankasByNaziv() {
		String naziv = "Otp";
		ResponseEntity<List<Banka>> response = template.exchange("/banka/naziv/"+naziv, HttpMethod.GET,null,
																							new ParameterizedTypeReference<List<Banka>>() {});
		int statusCode = response.getStatusCode().value();
		List<Banka> banka = response.getBody();
		String nazivBanke= banka.get(0).getNaziv();
		assertEquals(200,statusCode);
		assertTrue(!banka.isEmpty());
		assertTrue(nazivBanke.startsWith(naziv));
    }
	
	@Test
	@Order(4)
	void testCreateBanka() {
		Banka banka = new Banka();
		banka.setNaziv("POST test");
		banka.setKontakt("Post kontakt");
		
		HttpEntity<Banka> entity = new HttpEntity<Banka>(banka);
		createHighestId();
		
		ResponseEntity<Banka> response = template.exchange("/banka", HttpMethod.POST,entity,Banka.class);
		
		int statusCode = response.getStatusCode().value();
		Banka createdBanka = response.getBody();
		
		assertEquals(201,statusCode);
		assertEquals(highestId,createdBanka.getId());
		assertEquals(banka.getNaziv(),createdBanka.getNaziv());
		assertEquals(banka.getKontakt(),createdBanka.getKontakt());
		assertEquals("/banka/"+highestId,response.getHeaders().getLocation().getPath());
	}
	@Test
	@Order(5)
	void testUpdateBanka() {
		Banka banka = new Banka();
		banka.setNaziv("PUT test");
		banka.setKontakt("PUT kontakt");
		
		HttpEntity<Banka> entity = new HttpEntity<Banka>(banka);
		getHighestId();
		
		ResponseEntity<Banka> response = template.exchange("/banka/id/"+highestId, HttpMethod.PUT,entity,Banka.class);
		
		int statusCode = response.getStatusCode().value();
		Banka updatedBanka = response.getBody();
		
		assertEquals(200,statusCode);
		assertEquals(highestId,updatedBanka.getId());
		assertEquals(banka.getNaziv(),updatedBanka.getNaziv());
		assertEquals(banka.getKontakt(),updatedBanka.getKontakt());
	}
	@Test
	@Order(6)
	void testDeleteBanka() {
		getHighestId();
		ResponseEntity<String> response = template.exchange("/banka/id/"+highestId, HttpMethod.DELETE,null,String.class);
		
		int statusCode = response.getStatusCode().value();
		
		assertEquals(200, statusCode);
		assertTrue(response.getBody().contains("has been succesfully deleted"));
	}

}
