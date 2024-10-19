package rva.controllers;

import java.net.URI;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.models.Banka;
import rva.models.Filijala;
import rva.models.KorisnikUsluge;
import rva.models.Usluga;
import rva.services.FilijalaService;
import rva.services.KorisnikUslugeService;
import rva.services.UslugaService;
@CrossOrigin
@RestController
public class UslugaController {
	@Autowired
	private UslugaService service;
	@Autowired
	private KorisnikUslugeService korisnikUslugeService;
	@Autowired
	private FilijalaService filijalaService;

	@GetMapping("/usluga")
	public List<Usluga> getAllUslugas(){
		return service.getAll();
	}
	
	@GetMapping("/usluga/id/{id}")
	public ResponseEntity<?> getUslugaById(@PathVariable int id){
		Optional<Usluga> usluga = service.findById(id);
		if(usluga.isPresent()) {
			return ResponseEntity.ok(usluga.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: "+id+" does not exist");
	}
	
	@GetMapping("/usluga/provizija/{provizija}")
	public ResponseEntity<?> getUslugaByProvizija(@PathVariable double provizija){
		List<Usluga> usluga = service.getUslugabyProvizija(provizija);
		if(usluga.isEmpty()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource with "+"Provizija: " +provizija+" could not be found");
		}
		return ResponseEntity.ok(usluga);
	}
	
	@PostMapping("/usluga")
	public ResponseEntity<?> createUsluga(@RequestBody Usluga usluga){
		if(service.existsById(usluga.getId())) {
			return ResponseEntity.status(409).body("Resource with inserted values "+"alredy exists");
		}
		Usluga savedUsluga = service.create(usluga);
		URI uri = URI.create("/usluga/"+savedUsluga.getId());
		return ResponseEntity.created(uri).body(savedUsluga);
	}

	@PutMapping("/usluga/id/{id}")
	public ResponseEntity<?> updateUsluga(@RequestBody Usluga usluga, @PathVariable int id){
		
		Optional<Usluga> updatedUsluga = service.update(usluga, id);
		if(updatedUsluga.isPresent())
		{
			return ResponseEntity.ok(updatedUsluga.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: "+id+" cannot be updated as it doesnt exist");
	}

	@DeleteMapping("/usluga/id/{id}")
	public ResponseEntity<?> deleteUsluga(@PathVariable int id){
		if(service.existsById(id)) {
			service.delete(id);
			return ResponseEntity.ok("Resource with ID: "+id+" has been succesfully deleted");
		}
		return ResponseEntity.status(404).body("Resource with ID: "+id+" cannot be deleted as it doesnt exist");
	}

	@GetMapping("usluga/filijala/{foreignKey}")
	public ResponseEntity<?> getUslugaByFilijala(@PathVariable int foreignKey){
		Optional<Filijala> filijala = filijalaService.findById(foreignKey);
		if(filijala.isPresent()) {
			List<Usluga> usluga = service.findByForeignKey(filijala.get());
			if(!usluga.isEmpty()) {
				return ResponseEntity.ok(usluga);
			} else {
				return ResponseEntity.status(404).body("Resource with requested foreign key:"+foreignKey+"do not exist");
			}
		}
		return ResponseEntity.status(400).body("Foreign key: " + foreignKey + " does not exist");
	}
	@GetMapping("usluga/korisnikusluge/{foreignKey}")
	public ResponseEntity<?> getUslugaByKorisnikUsluge(@PathVariable int foreignKey){
		Optional<KorisnikUsluge> korisnikusluge = korisnikUslugeService.findById(foreignKey);
		if(korisnikusluge.isPresent()) {
			List<Usluga> usluga = service.findByForeignKey(korisnikusluge.get());
			if(!usluga.isEmpty()) {
				return ResponseEntity.ok(usluga);
			} else {
				return ResponseEntity.status(404).body("Resource with requested foreign key:"+foreignKey+"do not exist");
			}
		}
		return ResponseEntity.status(400).body("Foreign key: " + foreignKey + " does not exist");
	}
	
}
