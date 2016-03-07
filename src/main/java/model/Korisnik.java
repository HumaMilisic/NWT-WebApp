package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the KORISNIK database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//staro, ne okida sekvencu ni sa GenerationType.AUTO
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KorisnikSEQ")
	@SequenceGenerator(name = "KorisnikSEQ", sequenceName = "KORISNIK_SEQ", allocationSize = 1) //allocationSize = 1)

	//@GenericGenerator(name = "KorisnikSEQ", strategy = "sequence", parameters = {
	//		@org.hibernate.annotations.Parameter(name = "sequenceName", value = "KORISNIK_SEQ"),
	//		@org.hibernate.annotations.Parameter(name = "allocationSize", value = "1")
	//})
	//@GeneratedValue(generator = "KorisnikSEQ", strategy=GenerationType.SEQUENCE)

	//@GenericGenerator(name = "KorisnikSEQ", strategy = "sequence-identity", parameters = @org.hibernate.annotations.Parameter(name = "sequence", value = "KORISNIK_SEQ"))
	//@Id
	//@GeneratedValue(generator = "KorisnikSEQ")
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="DATUM_RODJENJA")
	private Date datumRodjenja;

	private String email;

	private String ime;

	private BigDecimal jmbg;

	@Column(name="MJESTO_RODJENJA")
	private String mjestoRodjenja;

	//@Lob
	private String password;

	private String prezime;

	private String username;

	//bi-directional many-to-one association to Dokument
	@OneToMany(mappedBy="korisnik")
	private List<Dokument> dokuments;

	//bi-directional many-to-one association to DokumentXStatus
	@OneToMany(mappedBy="korisnikBean")
	private List<DokumentXStatus> dokumentXStatuses;

	@JsonIgnore //proba
	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="korisnik")
	//@JsonManagedReference
	private List<Komentar> komentars;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="AZURIRAN_NA")
	private Korisnik korisnik;

	//bi-directional many-to-one association to Korisnik
	@OneToMany(mappedBy="korisnik")
	private List<Korisnik> korisniks;

	//bi-directional many-to-one association to KorisnikXDokument
	@OneToMany(mappedBy="korisnikBean")
	private List<KorisnikXDokument> korisnikXDokuments;

	//bi-directional many-to-one association to KorisnikXKorisnik
	@OneToMany(mappedBy="korisnik1")
	private List<KorisnikXKorisnik> korisnikXKorisniks1;

	//bi-directional many-to-one association to KorisnikXKorisnik
	@OneToMany(mappedBy="korisnik2")
	private List<KorisnikXKorisnik> korisnikXKorisniks2;

	//bi-directional many-to-many association to Uloga
	@ManyToMany(mappedBy="korisniks")
	private List<Uloga> ulogas;

	private char deleted;

	public Korisnik() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatumRodjenja() {
		return this.datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public BigDecimal getJmbg() {
		return this.jmbg;
	}

	public void setJmbg(BigDecimal jmbg) {
		this.jmbg = jmbg;
	}

	public String getMjestoRodjenja() {
		return this.mjestoRodjenja;
	}

	public void setMjestoRodjenja(String mjestoRodjenja) {
		this.mjestoRodjenja = mjestoRodjenja;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Dokument> getDokuments() {
		return this.dokuments;
	}

	public void setDokuments(List<Dokument> dokuments) {
		this.dokuments = dokuments;
	}

	public Dokument addDokument(Dokument dokument) {
		getDokuments().add(dokument);
		dokument.setKorisnik(this);

		return dokument;
	}

	public Dokument removeDokument(Dokument dokument) {
		getDokuments().remove(dokument);
		dokument.setKorisnik(null);

		return dokument;
	}

	public List<DokumentXStatus> getDokumentXStatuses() {
		return this.dokumentXStatuses;
	}

	public void setDokumentXStatuses(List<DokumentXStatus> dokumentXStatuses) {
		this.dokumentXStatuses = dokumentXStatuses;
	}

	public DokumentXStatus addDokumentXStatus(DokumentXStatus dokumentXStatus) {
		getDokumentXStatuses().add(dokumentXStatus);
		dokumentXStatus.setKorisnikBean(this);

		return dokumentXStatus;
	}

	public DokumentXStatus removeDokumentXStatus(DokumentXStatus dokumentXStatus) {
		getDokumentXStatuses().remove(dokumentXStatus);
		dokumentXStatus.setKorisnikBean(null);

		return dokumentXStatus;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setKorisnik(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setKorisnik(null);

		return komentar;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public List<Korisnik> getKorisniks() {
		return this.korisniks;
	}

	public void setKorisniks(List<Korisnik> korisniks) {
		this.korisniks = korisniks;
	}

	public Korisnik addKorisnik(Korisnik korisnik) {
		getKorisniks().add(korisnik);
		korisnik.setKorisnik(this);

		return korisnik;
	}

	public Korisnik removeKorisnik(Korisnik korisnik) {
		getKorisniks().remove(korisnik);
		korisnik.setKorisnik(null);

		return korisnik;
	}

	public List<KorisnikXDokument> getKorisnikXDokuments() {
		return this.korisnikXDokuments;
	}

	public void setKorisnikXDokuments(List<KorisnikXDokument> korisnikXDokuments) {
		this.korisnikXDokuments = korisnikXDokuments;
	}

	public KorisnikXDokument addKorisnikXDokument(KorisnikXDokument korisnikXDokument) {
		getKorisnikXDokuments().add(korisnikXDokument);
		korisnikXDokument.setKorisnikBean(this);

		return korisnikXDokument;
	}

	public KorisnikXDokument removeKorisnikXDokument(KorisnikXDokument korisnikXDokument) {
		getKorisnikXDokuments().remove(korisnikXDokument);
		korisnikXDokument.setKorisnikBean(null);

		return korisnikXDokument;
	}

	public List<KorisnikXKorisnik> getKorisnikXKorisniks1() {
		return this.korisnikXKorisniks1;
	}

	public void setKorisnikXKorisniks1(List<KorisnikXKorisnik> korisnikXKorisniks1) {
		this.korisnikXKorisniks1 = korisnikXKorisniks1;
	}

	public KorisnikXKorisnik addKorisnikXKorisniks1(KorisnikXKorisnik korisnikXKorisniks1) {
		getKorisnikXKorisniks1().add(korisnikXKorisniks1);
		korisnikXKorisniks1.setKorisnik1(this);

		return korisnikXKorisniks1;
	}

	public KorisnikXKorisnik removeKorisnikXKorisniks1(KorisnikXKorisnik korisnikXKorisniks1) {
		getKorisnikXKorisniks1().remove(korisnikXKorisniks1);
		korisnikXKorisniks1.setKorisnik1(null);

		return korisnikXKorisniks1;
	}

	public List<KorisnikXKorisnik> getKorisnikXKorisniks2() {
		return this.korisnikXKorisniks2;
	}

	public void setKorisnikXKorisniks2(List<KorisnikXKorisnik> korisnikXKorisniks2) {
		this.korisnikXKorisniks2 = korisnikXKorisniks2;
	}

	public KorisnikXKorisnik addKorisnikXKorisniks2(KorisnikXKorisnik korisnikXKorisniks2) {
		getKorisnikXKorisniks2().add(korisnikXKorisniks2);
		korisnikXKorisniks2.setKorisnik2(this);

		return korisnikXKorisniks2;
	}

	public KorisnikXKorisnik removeKorisnikXKorisniks2(KorisnikXKorisnik korisnikXKorisniks2) {
		getKorisnikXKorisniks2().remove(korisnikXKorisniks2);
		korisnikXKorisniks2.setKorisnik2(null);

		return korisnikXKorisniks2;
	}

	public List<Uloga> getUlogas() {
		return this.ulogas;
	}

	public void setUlogas(List<Uloga> ulogas) {
		this.ulogas = ulogas;
	}

	public char getDeleted() {
		return this.deleted;
	}

	public void setDeleted(char deleted) {
		this.deleted = deleted;
	}

}