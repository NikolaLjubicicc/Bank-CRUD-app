package rva.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.models.Filijala;
import rva.models.KorisnikUsluge;
import rva.models.Usluga;
import rvarepository.UslugaRepository;
import services.UslugaService;
@Component
public class UslugaServiceImpl implements UslugaService {
	@Autowired
	private UslugaRepository repo;
	
	@Override
	public List<Usluga> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(int id) {
		return repo.existsById(id);
	}

	@Override
	public Usluga create(Usluga t) {
		return repo.save(t);
	}

	@Override
	public Optional<Usluga> update(Usluga t, int id) {
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
	public List<Usluga> getUslugabyProvizija(double provizija) {
		return repo.findByProvizijaLessThanOrderByDesc(provizija);
	}

	@Override
	public List<Usluga> findByForeignKey(Filijala filijala) {
		return repo.findByFilijala(filijala);
	}

	@Override
	public List<Usluga> findByForeignKey(KorisnikUsluge korisnik) {
		return repo.findByKorisnikUsluge(korisnik);
	}

}
