package com.example.models;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the RELACIJA_KORISNIK database table.
 * 
 */
@Entity
@Table(name="RELACIJA_KORISNIK")
//@NamedQuery(name="RelacijaKorisnik.findAll", query="SELECT r FROM RelacijaKorisnik r")
public class RelacijaKorisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RelacijaKorisnikSEQ")
	@SequenceGenerator(name = "RelacijaKorisnikSEQ", sequenceName = "RELACIJA_KORISNIK_SEQ", allocationSize = 1)
	@Generated(GenerationTime.INSERT) //dodano
	private long id;

	private String nazivba;

	private String naziven;

	//bi-directional many-to-one association to KorisnikXKorisnik
//	@OneToMany(mappedBy="relacijaKorisnik")
//	private List<KorisnikXKorisnik> korisnikXKorisniks;

	//bi-directional many-to-one association to RelKorisnikXNotifikacija
	@OneToMany(mappedBy="relacijaKorisnikBean")
	private List<RelKorisnikXNotifikacija> relKorisnikXNotifikacijas;

	//bi-directional many-to-one association to RelKorisnikXRelDokument
//	@OneToMany(mappedBy="relacijaKorisnikBean")
//	private List<RelKorisnikXRelDokument> relKorisnikXRelDokuments;

	//bi-directional many-to-one association to RelKorisnikXStatus
//	@OneToMany(mappedBy="relacijaKorisnikBean")
//	private List<RelKorisnikXStatus> relKorisnikXStatuses;

	//bi-directional many-to-one association to RelKorisnikXVrstaDoc
//	@OneToMany(mappedBy="relacijaKorisnikBean")
//	private List<RelKorisnikXVrstaDoc> relKorisnikXVrstaDocs;

//	private char deleted;
	@Length(max = 1)
	@Column(nullable = false,name="deleted")
	private String deleted;

	public RelacijaKorisnik() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNazivba() {
		return this.nazivba;
	}

	public void setNazivba(String nazivba) {
		this.nazivba = nazivba;
	}

	public String getNaziven() {
		return this.naziven;
	}

	public void setNaziven(String naziven) {
		this.naziven = naziven;
	}

//	public List<KorisnikXKorisnik> getKorisnikXKorisniks() {
//		return this.korisnikXKorisniks;
//	}

//	public void setKorisnikXKorisniks(List<KorisnikXKorisnik> korisnikXKorisniks) {
//		this.korisnikXKorisniks = korisnikXKorisniks;
//	}

//	public KorisnikXKorisnik addKorisnikXKorisnik(KorisnikXKorisnik korisnikXKorisnik) {
//		getKorisnikXKorisniks().add(korisnikXKorisnik);
//		korisnikXKorisnik.setRelacijaKorisnik(this);
//		return korisnikXKorisnik;
//	}

//	public KorisnikXKorisnik removeKorisnikXKorisnik(KorisnikXKorisnik korisnikXKorisnik) {
//		getKorisnikXKorisniks().remove(korisnikXKorisnik);
//		korisnikXKorisnik.setRelacijaKorisnik(null);
//		return korisnikXKorisnik;
//	}

//	public List<RelKorisnikXNotifikacija> getRelKorisnikXNotifikacijas() {
//		return this.relKorisnikXNotifikacijas;
//	}

//	public void setRelKorisnikXNotifikacijas(List<RelKorisnikXNotifikacija> relKorisnikXNotifikacijas) {
//		this.relKorisnikXNotifikacijas = relKorisnikXNotifikacijas;
//	}

//	public RelKorisnikXNotifikacija addRelKorisnikXNotifikacija(RelKorisnikXNotifikacija relKorisnikXNotifikacija) {
//		getRelKorisnikXNotifikacijas().add(relKorisnikXNotifikacija);
//		relKorisnikXNotifikacija.setRelacijaKorisnikBean(this);
//		return relKorisnikXNotifikacija;
//	}

//	public RelKorisnikXNotifikacija removeRelKorisnikXNotifikacija(RelKorisnikXNotifikacija relKorisnikXNotifikacija) {
//		getRelKorisnikXNotifikacijas().remove(relKorisnikXNotifikacija);
//		relKorisnikXNotifikacija.setRelacijaKorisnikBean(null);
//		return relKorisnikXNotifikacija;
//	}

//	public List<RelKorisnikXRelDokument> getRelKorisnikXRelDokuments() {
//		return this.relKorisnikXRelDokuments;
//	}

//	public void setRelKorisnikXRelDokuments(List<RelKorisnikXRelDokument> relKorisnikXRelDokuments) {
//		this.relKorisnikXRelDokuments = relKorisnikXRelDokuments;
//	}

//	public RelKorisnikXRelDokument addRelKorisnikXRelDokument(RelKorisnikXRelDokument relKorisnikXRelDokument) {
//		getRelKorisnikXRelDokuments().add(relKorisnikXRelDokument);
//		relKorisnikXRelDokument.setRelacijaKorisnikBean(this);
//		return relKorisnikXRelDokument;
//	}

//	public RelKorisnikXRelDokument removeRelKorisnikXRelDokument(RelKorisnikXRelDokument relKorisnikXRelDokument) {
//		getRelKorisnikXRelDokuments().remove(relKorisnikXRelDokument);
//		relKorisnikXRelDokument.setRelacijaKorisnikBean(null);
//		return relKorisnikXRelDokument;
//	}

//	public List<RelKorisnikXStatus> getRelKorisnikXStatuses() {
//		return this.relKorisnikXStatuses;
//	}

//	public void setRelKorisnikXStatuses(List<RelKorisnikXStatus> relKorisnikXStatuses) {
//		this.relKorisnikXStatuses = relKorisnikXStatuses;
//	}

//	public RelKorisnikXStatus addRelKorisnikXStatus(RelKorisnikXStatus relKorisnikXStatus) {
//		getRelKorisnikXStatuses().add(relKorisnikXStatus);
//		relKorisnikXStatus.setRelacijaKorisnikBean(this);
//		return relKorisnikXStatus;
//	}

//	public RelKorisnikXStatus removeRelKorisnikXStatus(RelKorisnikXStatus relKorisnikXStatus) {
//		getRelKorisnikXStatuses().remove(relKorisnikXStatus);
//		relKorisnikXStatus.setRelacijaKorisnikBean(null);
//		return relKorisnikXStatus;
//	}

//	public List<RelKorisnikXVrstaDoc> getRelKorisnikXVrstaDocs() {
//		return this.relKorisnikXVrstaDocs;
//	}

//	public void setRelKorisnikXVrstaDocs(List<RelKorisnikXVrstaDoc> relKorisnikXVrstaDocs) {
//		this.relKorisnikXVrstaDocs = relKorisnikXVrstaDocs;
//	}

//	public RelKorisnikXVrstaDoc addRelKorisnikXVrstaDoc(RelKorisnikXVrstaDoc relKorisnikXVrstaDoc) {
//		getRelKorisnikXVrstaDocs().add(relKorisnikXVrstaDoc);
//		relKorisnikXVrstaDoc.setRelacijaKorisnikBean(this);
//		return relKorisnikXVrstaDoc;
//	}

//	public RelKorisnikXVrstaDoc removeRelKorisnikXVrstaDoc(RelKorisnikXVrstaDoc relKorisnikXVrstaDoc) {
//		getRelKorisnikXVrstaDocs().remove(relKorisnikXVrstaDoc);
//		relKorisnikXVrstaDoc.setRelacijaKorisnikBean(null);
//		return relKorisnikXVrstaDoc;
//	}

/*	public char getDeleted() {
		return this.deleted;
	}

	public void setDeleted(char deleted) {
		this.deleted = deleted;
	}
*/
	public String getDeleted() { //bilo char
	return this.deleted;
}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
}