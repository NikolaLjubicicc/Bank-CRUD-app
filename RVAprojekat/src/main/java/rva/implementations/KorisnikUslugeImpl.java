package rva.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.models.KorisnikUsluge;
import rvarepository.KorisnikUslugeRepository;
import services.KorisnikUslugeService;
@Component
public class KorisnikUslugeImpl implements KorisnikUslugeService {
	@Autowired
	private KorisnikUslugeRepository repo;
	
	@Override
	public List<KorisnikUsluge> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(int id) {
		return repo.existsById(id);
	}

	@Override
	public KorisnikUsluge create(KorisnikUsluge t) {
		return repo.save(t);
	}

	@Override
	public Optional<KorisnikUsluge> update(KorisnikUsluge t, int id) {
		if(existsById(id)) {
			return Optional.of(repo.save(t));
	}
	return Optional.empty();
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);
	}

	@Override
	public List<KorisnikUsluge> getKorisnikUslugebyMaticniBroj(String maticni_broj) {
		return repo.findByMaticniBrojContainingIngoreCase(maticni_broj);
	}

}
