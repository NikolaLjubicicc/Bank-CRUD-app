package rva.models;

import java.io.Serializable;
import java.util.List;

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
	private String maticni_broj;
	
	@OneToMany(mappedBy = "korisnik")
	private List<Usluga> usluga;
	
	public KorisnikUsluge() {
		
	}
	public KorisnikUsluge(int id, String ime, String prezime, String maticni_broj) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.maticni_broj = maticni_broj;
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
	public String getMaticni_broj() {
		return maticni_broj;
	}
	public void setMaticni_broj(String maticni_broj) {
		this.maticni_broj = maticni_broj;
	}

	
}
