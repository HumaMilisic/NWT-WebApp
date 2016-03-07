package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the STATUS database table.
 * 
 */
@Entity
@NamedQuery(name="Status.findAll", query="SELECT s FROM Status s")
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "StatusSEQ")
	@SequenceGenerator(name = "StatusSEQ", sequenceName = "STATUS_SEQ", allocationSize = 1)
	private long id;

	private String nazivba;

	private String naziven;

	@JsonIgnore
	//bi-directional many-to-one association to Dokument
	@OneToMany(mappedBy="statusBean")
	private List<Dokument> dokuments;

	//bi-directional many-to-one association to DokumentXStatus
	@OneToMany(mappedBy="statusBean")
	private List<DokumentXStatus> dokumentXStatuses;

	//bi-directional many-to-one association to RelKorisnikXStatus
	@OneToMany(mappedBy="statusBean")
	private List<RelKorisnikXStatus> relKorisnikXStatuses;

	//bi-directional many-to-many association to Notifikacija
	@ManyToMany
	@JoinTable(
		name="STATUS_X_NOTIFIKACIJA"
		, joinColumns={
			@JoinColumn(name="STATUS")
			}
		, inverseJoinColumns={
			@JoinColumn(name="NOTIFIKACIJA")
			}
		)
	private List<Notifikacija> notifikacijas;

	//bi-directional many-to-one association to UlogaXStatus
	@OneToMany(mappedBy="statusBean")
	private List<UlogaXStatus> ulogaXStatuses;

	@JsonIgnore
	//bi-directional many-to-many association to VrstaDokumenta
	@ManyToMany(mappedBy="statuses")
	private List<VrstaDokumenta> vrstaDokumentas;

	private char deleted;

	public Status() {
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

	public List<Dokument> getDokuments() {
		return this.dokuments;
	}

	public void setDokuments(List<Dokument> dokuments) {
		this.dokuments = dokuments;
	}

	public Dokument addDokument(Dokument dokument) {
		getDokuments().add(dokument);
		dokument.setStatusBean(this);

		return dokument;
	}

	public Dokument removeDokument(Dokument dokument) {
		getDokuments().remove(dokument);
		dokument.setStatusBean(null);

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
		dokumentXStatus.setStatusBean(this);

		return dokumentXStatus;
	}

	public DokumentXStatus removeDokumentXStatus(DokumentXStatus dokumentXStatus) {
		getDokumentXStatuses().remove(dokumentXStatus);
		dokumentXStatus.setStatusBean(null);

		return dokumentXStatus;
	}

	public List<RelKorisnikXStatus> getRelKorisnikXStatuses() {
		return this.relKorisnikXStatuses;
	}

	public void setRelKorisnikXStatuses(List<RelKorisnikXStatus> relKorisnikXStatuses) {
		this.relKorisnikXStatuses = relKorisnikXStatuses;
	}

	public RelKorisnikXStatus addRelKorisnikXStatus(RelKorisnikXStatus relKorisnikXStatus) {
		getRelKorisnikXStatuses().add(relKorisnikXStatus);
		relKorisnikXStatus.setStatusBean(this);

		return relKorisnikXStatus;
	}

	public RelKorisnikXStatus removeRelKorisnikXStatus(RelKorisnikXStatus relKorisnikXStatus) {
		getRelKorisnikXStatuses().remove(relKorisnikXStatus);
		relKorisnikXStatus.setStatusBean(null);

		return relKorisnikXStatus;
	}

	public List<Notifikacija> getNotifikacijas() {
		return this.notifikacijas;
	}

	public void setNotifikacijas(List<Notifikacija> notifikacijas) {
		this.notifikacijas = notifikacijas;
	}

	public List<UlogaXStatus> getUlogaXStatuses() {
		return this.ulogaXStatuses;
	}

	public void setUlogaXStatuses(List<UlogaXStatus> ulogaXStatuses) {
		this.ulogaXStatuses = ulogaXStatuses;
	}

	public UlogaXStatus addUlogaXStatus(UlogaXStatus ulogaXStatus) {
		getUlogaXStatuses().add(ulogaXStatus);
		ulogaXStatus.setStatusBean(this);

		return ulogaXStatus;
	}

	public UlogaXStatus removeUlogaXStatus(UlogaXStatus ulogaXStatus) {
		getUlogaXStatuses().remove(ulogaXStatus);
		ulogaXStatus.setStatusBean(null);

		return ulogaXStatus;
	}

	public List<VrstaDokumenta> getVrstaDokumentas() {
		return this.vrstaDokumentas;
	}

	public void setVrstaDokumentas(List<VrstaDokumenta> vrstaDokumentas) {
		this.vrstaDokumentas = vrstaDokumentas;
	}

	public char getDeleted() {
		return this.deleted;
	}

	public void setDeleted(char deleted) {
		this.deleted = deleted;
	}

}