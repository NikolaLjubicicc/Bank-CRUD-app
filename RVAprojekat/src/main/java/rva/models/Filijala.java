package rva.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private int brojPultova;
	private Boolean posedujeSef;
	
	@OneToMany(mappedBy = "filijala")
	@JsonIgnore
	private List<Usluga> usluga;
	
	@ManyToOne
	@JoinColumn(name = "banka")
	private Banka banka;
	
	public Filijala() {
		
	}
	public Filijala(int id, String adresa, int brojPultova, Boolean posedujeSef) {
		super();
		this.id = id;
		this.adresa = adresa;
		this.brojPultova = brojPultova;
		this.posedujeSef = posedujeSef;
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
		return brojPultova;
	}
	public void setBrojPultova(int brojPultova) {
		this.brojPultova = brojPultova;
	}
	public Boolean getPoseduje_sef() {
		return posedujeSef;
	}
	public void setPosedujeSef(Boolean posedujeSef) {
		this.posedujeSef = posedujeSef;
	}

	
}
