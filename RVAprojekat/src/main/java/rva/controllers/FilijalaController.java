package rva.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import rva.models.Banka;
import rva.models.Filijala;
import rva.services.BankaService;
import rva.services.FilijalaService;


public class FilijalaController {
	private FilijalaService service;
	private BankaService bankaService;
	@GetMapping("/filijala/banka/{foreignKey}")
	public ResponseEntity<?> getStavkeByArtikl(@PathVariable int foreignKey){
		
		Optional<Banka> banka = bankaService.findById(foreignKey);
		if(banka.isPresent()) {
			List<Filijala> filijale= service.findByForeignKey(banka.get());
			if(!filijale.isEmpty()) {
				return ResponseEntity.ok(filijale);
			}else {
				return ResponseEntity.status(404).body("Resource with requested foreign key:"+foreignKey+"do not exist");
			}
		}
		return ResponseEntity.status(404).body("Foreign key: " + foreignKey + " does not exist");
	}
}
