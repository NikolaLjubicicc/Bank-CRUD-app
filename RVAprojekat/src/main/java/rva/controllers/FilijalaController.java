package rva.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.models.Banka;
import rva.models.Filijala;
import rva.services.BankaService;
import rva.services.FilijalaService;

@RestController
public class FilijalaController {
	@Autowired
	private FilijalaService service;
	@Autowired
	private BankaService bankaService;
	
	
	
	@GetMapping("/filijala")
	public List<Filijala> getAllFilijalas()
	{
		return service.getAll();
	}
	
	@GetMapping("/filijala/id/{id}")
	public ResponseEntity<?> getFilijalaById(@PathVariable int id){
		Optional<Filijala> filijala = service.findById(id);
		if(filijala.isPresent()) {
			return ResponseEntity.ok(filijala.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: "+id+" does not exist");
	}
	
	@GetMapping("/filijala/posedujeSef/{posedujeSef}")
	public ResponseEntity<?> getFilijalaByPosedujeSef(@PathVariable boolean posedujeSef){
		List<Filijala> filijala = service.getFilijalabyPosedujeSef(posedujeSef);
		if(filijala.isEmpty()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource with "+"Poseduje sef:" +posedujeSef+" could not be found");
		}
		return ResponseEntity.ok(filijala);
	}

	@PostMapping("/filijala")
	public ResponseEntity<?> createFilijala(@RequestBody Filijala filijala){
		if(service.existsById(filijala.getId())) {
			return ResponseEntity.status(409).body("Resource with inserted values "+"alredy exists");
		}
		Filijala savedFilijala = service.create(filijala);
		URI uri = URI.create("/filijala/"+savedFilijala.getId());
		return ResponseEntity.created(uri).body(savedFilijala);
	}
	
	@PutMapping("/filijala/id/{id}")
	public ResponseEntity<?> updateFilijala(@RequestBody Filijala filijala, @PathVariable int id) {
		Optional<Filijala> updatedFilijala = service.update(filijala, id);
		if(updatedFilijala.isPresent()) {
			return ResponseEntity.ok(updatedFilijala);
		}
		return ResponseEntity.status(404).body("Resource with requested ID: "+id+" cannot be updated as it doesnt exist");
	}
	
	@DeleteMapping("/filijala/id/{id}")
	public ResponseEntity<?> deleteFilijala(@PathVariable int id){
		if(service.existsById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: "+id+" has been succesfully deleted");
		}
		return ResponseEntity.status(404).body("Resource with ID: "+id+" cannot be deleted as it doesnt exist");
	}
	
	@GetMapping("/filijala/banka/{foreignKey}")
	public ResponseEntity<?> getFilijalaByBanka(@PathVariable int foreignKey){
		
		Optional<Banka> banka = bankaService.findById(foreignKey);
		if(banka.isPresent()) {
			List<Filijala> filijale= service.findByForeignKey(banka.get());
			if(!filijale.isEmpty()) {
				return ResponseEntity.ok(filijale);
			}else {
				return ResponseEntity.status(404).body("Resource with requested foreign key:"+foreignKey+"do not exist");
			}
		}
		return ResponseEntity.status(400).body("Foreign key: " + foreignKey + " does not exist");
	}
}
