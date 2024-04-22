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


import rva.models.KorisnikUsluge;
import rva.services.KorisnikUslugeService;
@CrossOrigin
@RestController
public class KorisnikUslugeController {
	@Autowired
	private KorisnikUslugeService service;

	@GetMapping("/korisnikusluge")
	public List<KorisnikUsluge> getAllKorisnikUsluge(){
		return service.getAll();
	}
	@GetMapping("/korisnikusluge/id/{id}")
	public ResponseEntity<?> getKorisnikUslugeById(@PathVariable int id){
		Optional<KorisnikUsluge> korisnikusluge = service.findById(id);
		if(korisnikusluge.isPresent()) {
			return ResponseEntity.ok(korisnikusluge.get());
		}
		return ResponseEntity.status(404).body("Resource with requested ID: "+id+" does not exist"); 
	}
	
	@GetMapping("/korisnikusluge/maticniBroj/{maticniBroj}")
	public ResponseEntity<?> getKorisnikUslugeByMaticniBroj(@PathVariable String maticniBroj){
		List<KorisnikUsluge> korisnikusluge = service.getKorisnikUslugebyMaticniBroj(maticniBroj);
		if(korisnikusluge.isEmpty()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource with "+"maticni broj:" +maticniBroj+" could not be found");
		}
		return ResponseEntity.ok(korisnikusluge);
	}

	@PostMapping("/korisnikusluge")
	public ResponseEntity<?> createKorisnikUsluge(@RequestBody KorisnikUsluge korisnikusluge){
		if(service.existsById(korisnikusluge.getId())) {
			return ResponseEntity.status(409).body("Resource with inserted values "+"alredy exists");
		}
		KorisnikUsluge savedKorisnikUsluge = service.create(korisnikusluge);
		URI uri = URI.create("/korisnikusluge/"+savedKorisnikUsluge.getId());
		return ResponseEntity.created(uri).body(savedKorisnikUsluge);
	}

	 @PutMapping("/korisnikusluge/id/{id}")
	 public ResponseEntity<?> updateKorisnikUsluge(@RequestBody KorisnikUsluge korisnikusluge, @PathVariable int id){
		 Optional<KorisnikUsluge> updatedKorisnikUsluge = service.update(korisnikusluge, id);
		 if(updatedKorisnikUsluge.isPresent()) {
			 return ResponseEntity.ok(updatedKorisnikUsluge);
		 }
		 return ResponseEntity.status(404).body("Resource with requested ID: "+id+" cannot be updated as it doesnt exist");
	 }

	 @DeleteMapping("/korisnikusluge/id/{id}")
	 public ResponseEntity<?> deleteKorisnikUsluge(@PathVariable int id){
		 if(service.existsById(id)) {
				service.delete(id);
				return ResponseEntity.ok("Resource with ID: "+id+" has been succesfully deleted");
	     }
		 return ResponseEntity.status(400).body("Resource with ID: "+id+" cannot be deleted as it doesnt exist");
    }
}
