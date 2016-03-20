package com.example.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the REL_KORISNIK_X_REL_DOKUMENT database table.
 * 
 */
@Entity @IdClass(RelKorisnikXRelDokumentID.class)
@Table(name="REL_KORISNIK_X_REL_DOKUMENT")
@NamedQuery(name="RelKorisnikXRelDokument.findAll", query="SELECT r FROM RelKorisnikXRelDokument r")
public class RelKorisnikXRelDokument implements Serializable {
	private static final long serialVersionUID = 1L;

	private String brisanje;

	private String izmjena;

	private String kreiranje;

	private String potpisivanje;

	private String pregled;

	private String preuzimanje;

	//bi-directional many-to-one association to RelacijaDokument
	@Id
	@ManyToOne
	@JoinColumn(name="RELACIJA_DOKUMENT")
	private RelacijaDokument relacijaDokumentBean;

	//bi-directional many-to-one association to RelacijaKorisnik
	@Id
	@ManyToOne
	@JoinColumn(name="RELACIJA_KORISNIK")
	private RelacijaKorisnik relacijaKorisnikBean;

	public RelKorisnikXRelDokument() {
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

	public RelacijaDokument getRelacijaDokumentBean() {
		return this.relacijaDokumentBean;
	}

	public void setRelacijaDokumentBean(RelacijaDokument relacijaDokumentBean) {
		this.relacijaDokumentBean = relacijaDokumentBean;
	}

	public RelacijaKorisnik getRelacijaKorisnikBean() {
		return this.relacijaKorisnikBean;
	}

	public void setRelacijaKorisnikBean(RelacijaKorisnik relacijaKorisnikBean) {
		this.relacijaKorisnikBean = relacijaKorisnikBean;
	}
	
	//dodao
	public RelKorisnikXRelDokumentID getId() {
		RelKorisnikXRelDokumentID id = new RelKorisnikXRelDokumentID();
		id.relacijaKorisnikBean = this.relacijaKorisnikBean;
		id.relacijaDokumentBean = this.relacijaDokumentBean;
		return id;
	}
		
	public void setId(RelKorisnikXRelDokumentID id) {
		this.relacijaKorisnikBean = id.relacijaKorisnikBean;
		this.relacijaDokumentBean = id.relacijaDokumentBean;
	}

}

