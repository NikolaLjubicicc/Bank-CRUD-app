package rva.models;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Usluga implements Serializable{
	@Id
	@SequenceGenerator(name="USLUGA_SEQ_GENERATOR",sequenceName="USLUGA_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USLUGA_SEQ_GENERATOR")
	private int id;
	private String naziv;
	private String opisUsluge;
	private Date datumUgovora;
	private double provizija;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "filijala")
	private Filijala filijala;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "korisnik")
	private KorisnikUsluge korisnikusluge;
	
	public Usluga() {
		
	}
	
	public Usluga(int id, String naziv, String opisUsluge, Date datumUgovora, double provizija) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opisUsluge = opisUsluge;
		this.datumUgovora = datumUgovora;
		this.provizija = provizija;
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
	public String getOpisUsluge() {
		return opisUsluge;
	}
	public void setOpisUsluge(String opisUsluge) {
		this.opisUsluge = opisUsluge;
	}
	public Date getDatumUgovora() {
		return datumUgovora;
	}
	public void setDatumUgovora(Date datumUgovora) {
		this.datumUgovora = datumUgovora;
	}
	public double getProvizija() {
		return provizija;
	}
	public void setProvizija(double provizija) {
		this.provizija = provizija;
	}


	
}
