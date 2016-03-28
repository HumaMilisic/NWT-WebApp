package com.example.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the REL_KORISNIK_X_STATUS database table.
 * 
 */
@Entity @IdClass(RelKorisnikXStatusID.class)
@Table(name="REL_KORISNIK_X_STATUS")
//@NamedQuery(name="RelKorisnikXStatus.findAll", query="SELECT r FROM RelKorisnikXStatus r")
public class RelKorisnikXStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	private String brisanje;

	private String izmjena;

	private String kreiranje;

	private String potpisivanje;

	private String pregled;

	private String preuzimanje;

	//bi-directional many-to-one association to RelacijaKorisnik
	@Id
	@ManyToOne
	@JoinColumn(name="RELACIJA_KORISNIK")
	private RelacijaKorisnik relacijaKorisnikBean;

	//bi-directional many-to-one association to Status
	@Id
	@JsonManagedReference //dodala
	@ManyToOne
	@JoinColumn(name="STATUS")
	private Status statusBean;

	public RelKorisnikXStatus() {
	}

	public String getBrisanje() {
		return this.brisanje;
	}

	public void setBrisanje(String brisanje) {
		this.brisanje = brisanje;
	}

	public String getIzmjena() {
		return this.izmjena;
	}

	public void setIzmjena(String izmjena) {
		this.izmjena = izmjena;
	}

	public String getKreiranje() {
		return this.kreiranje;
	}

	public void setKreiranje(String kreiranje) {
		this.kreiranje = kreiranje;
	}

	public String getPotpisivanje() {
		return this.potpisivanje;
	}

	public void setPotpisivanje(String potpisivanje) {
		this.potpisivanje = potpisivanje;
	}

	public String getPregled() {
		return this.pregled;
	}

	public void setPregled(String pregled) {
		this.pregled = pregled;
	}

	public String getPreuzimanje() {
		return this.preuzimanje;
	}

	public void setPreuzimanje(String preuzimanje) {
		this.preuzimanje = preuzimanje;
	}

	public RelacijaKorisnik getRelacijaKorisnikBean() {
		return this.relacijaKorisnikBean;
	}

	public void setRelacijaKorisnikBean(RelacijaKorisnik relacijaKorisnikBean) {
		this.relacijaKorisnikBean = relacijaKorisnikBean;
	}

	public Status getStatusBean() {
		return this.statusBean;
	}

	public void setStatusBean(Status statusBean) {
		this.statusBean = statusBean;
	}
	
	//dodao
	public RelKorisnikXStatusID getId() {
		RelKorisnikXStatusID id = new RelKorisnikXStatusID();
		id.relacijaKorisnikBean = this.relacijaKorisnikBean;
		id.statusBean = this.statusBean;
		return id;
	}
		
	public void setId(RelKorisnikXStatusID id) {
		this.relacijaKorisnikBean = id.relacijaKorisnikBean;
		this.statusBean = id.statusBean;
	}

}

