package rvarepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rva.models.Filijala;
import rva.models.KorisnikUsluge;
import rva.models.Usluga;

@Repository
public interface UslugaRepository extends JpaRepository<Usluga, Integer>{
	List<Usluga> findByProvizijaLessThanOrderByDesc(double provizija);
	
	List<Usluga> findByFilijala(Filijala filijala);
	List<Usluga> findByKorisnikUsluge(KorisnikUsluge korisnik);
}
