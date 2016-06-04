package com.example.models;


//import com.example.utils.validators.ValidBool;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the KOMENTAR database table.
 *
 */
@Entity
//@NamedQuery(name="Komentar.findAll", query="SELECT k FROM Komentar k")
public class Komentar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KomentarSEQ")
	@SequenceGenerator(name = "KomentarSEQ", sequenceName = "KOMENTAR_SEQ", allocationSize = 1)
	private long id;

	private Timestamp kreiran;

	@Lob
	private String tekst;

	@JsonIgnore
	@ManyToOne
	private Korisnik korisnik;

//	@JsonIgnore
//	@ManyToMany
//	private Set<Dokument>dokumentSet;
//
//	public Set<Dokument> getDokumentSet() {
//		return dokumentSet;
//	}
//
//	public void setDokumentSet(Set<Dokument> dokumentSet) {
//		this.dokumentSet = dokumentSet;
//	}

	//bi-directional many-to-one association to Dokument
/*
	@ManyToOne
	@JoinColumn(name="DOKUMENT")
	private Dokument dokumentBean;
*/

	//bi-directional many-to-one association to Korisnik
/*
	@ManyToOne
	@JoinColumn(name="AUTOR")
	private Korisnik korisnik;
*/

/*
	@ValidBool
	private String deleted;

	public Komentar() {
	}
*/

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getKreiran() {
		return this.kreiran;
	}

	public void setKreiran(Timestamp kreiran) {
//		this.kreiran = kreiran;
		datumi();
	}

	public String getTekst() {
		return this.tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
		datumi();
	}

/*
	public Dokument getDokumentBean() {
		return this.dokumentBean;
	}

	public void setDokumentBean(Dokument dokumentBean) {
		this.dokumentBean = dokumentBean;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public String getDeleted() {
		return this.deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
*/
private void datumi()
{
	Date date = new Date();
	if(this.kreiran==null){
		this.kreiran = new Timestamp(date.getTime());
	}
}

}