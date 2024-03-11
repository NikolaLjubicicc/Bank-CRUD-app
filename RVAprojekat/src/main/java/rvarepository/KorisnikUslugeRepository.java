package rvarepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rva.models.KorisnikUsluge;

@Repository
public interface KorisnikUslugeRepository  extends JpaRepository<KorisnikUsluge, Integer>{
	List<KorisnikUsluge> findByMaticniBrojContainingIngoreCase(String maticni_broj);
}
