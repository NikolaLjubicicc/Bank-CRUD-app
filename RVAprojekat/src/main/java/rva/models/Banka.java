package rva.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;


	
@Entity
public class Banka implements Serializable{
	private static final long serialVersionVID=1L;
	@Id
	@SequenceGenerator(name="BANKA_SEQ_GENERATOR",sequenceName="BANKA_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BANKA_SEQ_GENERATOR")
	private int id;
	private String naziv;
	private String kontakt;
	private int pib;
	@OneToMany(mappedBy = "banka", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Filijala> filijala;
	
	
	public List<Filijala> getFilijala() {
		return filijala;
	}

	public void setFilijala(List<Filijala> filijala) {
		this.filijala = filijala;
	}

	public Banka() {
		
	}
	
	public Banka(int id, String naziv, String kontakt, int pib) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.kontakt = kontakt;
		this.pib = pib;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getKontakt() {
		return kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}

	public int getPib() {
		return pib;
	}

	public void setPib(int pib) {
		this.pib = pib;
	}


	
}
