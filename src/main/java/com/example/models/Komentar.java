package com.example.models;


import com.example.utils.validators.ValidBool;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


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

	//bi-directional many-to-one association to Dokument
	@ManyToOne
	@JoinColumn(name="DOKUMENT")
	private Dokument dokumentBean;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="AUTOR")
	private Korisnik korisnik;

	@ValidBool
	private String deleted;

	public Komentar() {
	}

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
		this.kreiran = kreiran;
	}

	public String getTekst() {
		return this.tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

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

}