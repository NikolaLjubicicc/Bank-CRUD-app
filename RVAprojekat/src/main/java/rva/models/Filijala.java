package rva.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Filijala implements Serializable{
	@Id
	@SequenceGenerator(name="FILIJALA_SEQ_GENERATOR",sequenceName="FILIJALA_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FILIJALA_SEQ_GENERATOR")
	private int id;
	private String adresa;
	private int broj_pultova;
	private Boolean poseduje_sef;
	
	@OneToMany(mappedBy = "filijala")
	private List<Usluga> usluga;
	
	@ManyToOne
	@JoinColumn(name = "dobavljac")
	private Banka banka;
	
	public Filijala() {
		
	}
	public Filijala(int id, String adresa, int broj_pultova, Boolean poseduje_sef) {
		super();
		this.id = id;
		this.adresa = adresa;
		this.broj_pultova = broj_pultova;
		this.poseduje_sef = poseduje_sef;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public int getBroj_pultova() {
		return broj_pultova;
	}
	public void setBroj_pultova(int broj_pultova) {
		this.broj_pultova = broj_pultova;
	}
	public Boolean getPoseduje_sef() {
		return poseduje_sef;
	}
	public void setPoseduje_sef(Boolean poseduje_sef) {
		this.poseduje_sef = poseduje_sef;
	}

	
}
