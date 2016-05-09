package com.example.models;


import com.example.utils.validators.ValidBool;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the DOKUMENT database table.
 * 
 */
@Entity
//@NamedQuery(name="Dokument.findAll", query="SELECT d FROM Dokument d")
public class Dokument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DokumentSEQ")
	@SequenceGenerator(name = "DokumentSEQ", sequenceName = "DOKUMENT_SEQ", allocationSize = 1)
	private long id;

	private Timestamp azuriran;

	@Temporal(TemporalType.DATE)
	private Date istice;

	private Timestamp kreiran;

	private String oznaka;

	@Lob
	private String potpis;

	private Timestamp potpisan;

	@Lob
	private String tekst;

	//bi-directional many-to-one association to Dokument
//	@ManyToOne
//	@JoinColumn(name="AZURIRAN_NA")
//	private Dokument dokument;

	//bi-directional many-to-one association to Dokument
//	@OneToMany(mappedBy="dokument")
//	private List<Dokument> dokuments;

	//bi-directional many-to-one association to Korisnik
//	@ManyToOne
//	@JoinColumn(name="POTPISAO")
//	private Korisnik korisnik;

	//bi-directional many-to-one association to Slika
	//@ManyToOne
	//@JoinColumn(name="SLIKA")
	//private Slika slikaBean;

	//bi-directional many-to-one association to Status
//	@ManyToOne
//	@JoinColumn(name="STATUS")
//	private Status statusBean;

	//bi-directional many-to-one association to VrstaDokumenta
//	@ManyToOne
//	@JoinColumn(name="VRSTA")
//	private VrstaDokumenta vrstaDokumenta;

	//bi-directional many-to-one association to DokumentXStatus
//	@OneToMany(mappedBy="dokumentBean")
//	private List<DokumentXStatus> dokumentXStatuses;

//	@JsonIgnore //proba
//	//bi-directional many-to-one association to Komentar
//	@OneToMany(mappedBy="dokumentBean")
//	//@JsonManagedReference //proba
//	private List<Komentar> komentars;

//	@JsonIgnore
//	//bi-directional many-to-one association to KorisnikXDokument
//	@OneToMany(mappedBy="dokumentBean")
//	private List<KorisnikXDokument> korisnikXDokuments;

//	@ValidBool
	private String deleted;

	public Dokument() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getAzuriran() {
		return this.azuriran;
	}

	public void setAzuriran(Timestamp azuriran) {
		this.azuriran = azuriran;
	}

	public Date getIstice() {
		return this.istice;
	}

	public void setIstice(Date istice) {
		this.istice = istice;
	}

	public Timestamp getKreiran() {
		return this.kreiran;
	}

	public void setKreiran(Timestamp kreiran) {
		this.kreiran = kreiran;
	}

	public String getOznaka() {
		return this.oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public String getPotpis() {
		return this.potpis;
	}

	public void setPotpis(String potpis) {
		this.potpis = potpis;
	}

	public Timestamp getPotpisan() {
		return this.potpisan;
	}

	public void setPotpisan(Timestamp potpisan) {
		this.potpisan = potpisan;
	}

	public String getTekst() {
		return this.tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

//	public Dokument getDokument() {
//		return this.dokument;
//	}
//
//	public void setDokument(Dokument dokument) {
//		this.dokument = dokument;
//	}
//
//	public List<Dokument> getDokuments() {
//		return this.dokuments;
//	}
//
//	public void setDokuments(List<Dokument> dokuments) {
//		this.dokuments = dokuments;
//	}

//	public Dokument addDokument(Dokument dokument) {
//		getDokuments().add(dokument);
//		dokument.setDokument(this);
//
//		return dokument;
//	}
//
//	public Dokument removeDokument(Dokument dokument) {
//		getDokuments().remove(dokument);
//		dokument.setDokument(null);
//
//		return dokument;
//	}

//	public Korisnik getKorisnik() {
//		return this.korisnik;
//	}
//
//	public void setKorisnik(Korisnik korisnik) {
//		this.korisnik = korisnik;
//	}

	//public Slika getSlikaBean() {
	//	return this.slikaBean;
	//}

	//public void setSlikaBean(Slika slikaBean) {
	//	this.slikaBean = slikaBean;
	//}

//	public Status getStatusBean() {
//		return this.statusBean;
//	}
//
//	public void setStatusBean(Status statusBean) {
//		this.statusBean = statusBean;
//	}
//
//	public VrstaDokumenta getVrstaDokumenta() {
//		return this.vrstaDokumenta;
//	}
//
//	public void setVrstaDokumenta(VrstaDokumenta vrstaDokumenta) {
//		this.vrstaDokumenta = vrstaDokumenta;
//	}
//
//	public List<DokumentXStatus> getDokumentXStatuses() {
//		return this.dokumentXStatuses;
//	}
//
//	public void setDokumentXStatuses(List<DokumentXStatus> dokumentXStatuses) {
//		this.dokumentXStatuses = dokumentXStatuses;
//	}
//
//	public DokumentXStatus addDokumentXStatus(DokumentXStatus dokumentXStatus) {
//		getDokumentXStatuses().add(dokumentXStatus);
//		dokumentXStatus.setDokumentBean(this);
//
//		return dokumentXStatus;
//	}
//
//	public DokumentXStatus removeDokumentXStatus(DokumentXStatus dokumentXStatus) {
//		getDokumentXStatuses().remove(dokumentXStatus);
//		dokumentXStatus.setDokumentBean(null);
//
//		return dokumentXStatus;
//	}
//
//	public List<Komentar> getKomentars() {
//		return this.komentars;
//	}
//
//	public void setKomentars(List<Komentar> komentars) {
//		this.komentars = komentars;
//	}
//
//	public Komentar addKomentar(Komentar komentar) {
//		getKomentars().add(komentar);
//		komentar.setDokumentBean(this);
//
//		return komentar;
//	}
//
//	public Komentar removeKomentar(Komentar komentar) {
//		getKomentars().remove(komentar);
//		komentar.setDokumentBean(null);
//
//		return komentar;
//	}
//
//	public List<KorisnikXDokument> getKorisnikXDokuments() {
//		return this.korisnikXDokuments;
//	}
//
//	public void setKorisnikXDokuments(List<KorisnikXDokument> korisnikXDokuments) {
//		this.korisnikXDokuments = korisnikXDokuments;
//	}
//
//	public KorisnikXDokument addKorisnikXDokument(KorisnikXDokument korisnikXDokument) {
//		getKorisnikXDokuments().add(korisnikXDokument);
//		korisnikXDokument.setDokumentBean(this);
//
//		return korisnikXDokument;
//	}
//
//	public KorisnikXDokument removeKorisnikXDokument(KorisnikXDokument korisnikXDokument) {
//		getKorisnikXDokuments().remove(korisnikXDokument);
//		korisnikXDokument.setDokumentBean(null);
//
//		return korisnikXDokument;
//	}

	public String getDeleted() {
		return this.deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}