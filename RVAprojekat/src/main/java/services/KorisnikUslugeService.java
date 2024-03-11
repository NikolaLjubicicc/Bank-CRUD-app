package services;

import java.util.List;

import org.springframework.stereotype.Service;

import rva.models.KorisnikUsluge;
@Service
public interface KorisnikUslugeService extends CrudService<KorisnikUsluge> {
	List<KorisnikUsluge> getKorisnikUslugebyMaticniBroj(String maticni_broj);
}
