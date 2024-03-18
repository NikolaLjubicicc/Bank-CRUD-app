package rva.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class KorisnikUsluge implements Serializable{
	@Id
	@SequenceGenerator(name="KORISNIK_USLUGE_SEQ_GENERATOR",sequenceName="KORISNIK_USLUGE_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="KORISNIK_USLUGE_SEQ_GENERATOR")
	private int id;
	private String ime;
	private String prezime;
	private String maticniBroj;
	
	@OneToMany(mappedBy = "korisnikusluge", cascade = CascadeType.REMOVE)
	private List<Usluga> usluga;
	
	public KorisnikUsluge() {
		
	}
	public KorisnikUsluge(int id, String ime, String prezime, String maticniBroj) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.maticniBroj = maticniBroj;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getMaticniBroj() {
		return maticniBroj;
	}
	public void setMaticniBroj(String maticniBroj) {
		this.maticniBroj = maticniBroj;
	}

	
}
