package rva.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rva.models.Banka;
import rva.models.Filijala;
import rvarepository.FilijalaRepository;
import services.FilijalaService;
@Component
public class FilijalaServiceImpl implements FilijalaService {
	@Autowired
	private FilijalaRepository repo;
	
	@Override
	public List<Filijala> getAll() {
		return repo.findAll();
	}

	@Override
	public boolean existsById(int id) {
		return repo.existsById(id);
	}

	@Override
	public Filijala create(Filijala t) {
		return repo.save(t);
	}

	@Override
	public Optional<Filijala> update(Filijala t, int id) {
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
	public List<Filijala> getFilijalabyPosedujeSef(boolean poseduje_sef) {
		return repo.findByPosedujeSefEquals(poseduje_sef);
	}

	@Override
	public List<Filijala> findByForeignKey(Banka banka) {
		return repo.findByBanka(banka);
	}

}
