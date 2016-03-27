package com.example.models;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the RELACIJA_DOKUMENT database table.
 * 
 */
@Entity
//@Table(name="RELACIJA_DOKUMENT")
//@NamedQuery(name="RelacijaDokument.findAll", query="SELECT r FROM RelacijaDokument r")
public class RelacijaDokument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RelacijaDokumentSEQ")
	@SequenceGenerator(name = "RelacijaDokumentSEQ", sequenceName = "RELACIJA_DOKUMENT_SEQ", allocationSize = 1)
	@Generated(GenerationTime.INSERT) //dodano
	private long id;

	private String nazivba;

	private String naziven;

	//bi-directional many-to-one association to KorisnikXDokument
	//@OneToMany(mappedBy="relacijaDokument")
	//private List<KorisnikXDokument> korisnikXDokuments;

	//bi-directional many-to-one association to RelKorisnikXRelDokument
	//@OneToMany(mappedBy="relacijaDokumentBean")
	//private List<RelKorisnikXRelDokument> relKorisnikXRelDokuments;

	//private char deleted;
	@Length(max = 1)
	@Column(nullable = false,name="deleted")
	private String deleted;

	public RelacijaDokument() {
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

//	public List<KorisnikXDokument> getKorisnikXDokuments() {
//		return this.korisnikXDokuments;
//	}

//	public void setKorisnikXDokuments(List<KorisnikXDokument> korisnikXDokuments) {
//		this.korisnikXDokuments = korisnikXDokuments;
//	}

//	public KorisnikXDokument addKorisnikXDokument(KorisnikXDokument korisnikXDokument) {
//		getKorisnikXDokuments().add(korisnikXDokument);
//		korisnikXDokument.setRelacijaDokument(this);
//		return korisnikXDokument;
//	}

//	public KorisnikXDokument removeKorisnikXDokument(KorisnikXDokument korisnikXDokument) {
//		getKorisnikXDokuments().remove(korisnikXDokument);
//		korisnikXDokument.setRelacijaDokument(null);
//		return korisnikXDokument;
//	}

//	public List<RelKorisnikXRelDokument> getRelKorisnikXRelDokuments() {
//		return this.relKorisnikXRelDokuments;
//	}

//	public void setRelKorisnikXRelDokuments(List<RelKorisnikXRelDokument> relKorisnikXRelDokuments) {
//		this.relKorisnikXRelDokuments = relKorisnikXRelDokuments;
//	}

//	public RelKorisnikXRelDokument addRelKorisnikXRelDokument(RelKorisnikXRelDokument relKorisnikXRelDokument) {
//		getRelKorisnikXRelDokuments().add(relKorisnikXRelDokument);
//		relKorisnikXRelDokument.setRelacijaDokumentBean(this);
//		return relKorisnikXRelDokument;
//	}

//	public RelKorisnikXRelDokument removeRelKorisnikXRelDokument(RelKorisnikXRelDokument relKorisnikXRelDokument) {
//		getRelKorisnikXRelDokuments().remove(relKorisnikXRelDokument);
//		relKorisnikXRelDokument.setRelacijaDokumentBean(null);
//		return relKorisnikXRelDokument;
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