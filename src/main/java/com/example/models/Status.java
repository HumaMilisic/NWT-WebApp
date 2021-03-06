package com.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


/**
 * The persistent class for the STATUS database table.
 * 
 */
@Entity(name = "status")
//@NamedQuery(name="Status.findAll", query="SELECT s FROM Status s")
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "StatusSEQ")
	@SequenceGenerator(name = "StatusSEQ", sequenceName = "STATUS_SEQ", allocationSize = 1)
	@Generated(GenerationTime.INSERT) //dodano
	private long id;

	private String nazivba;

	private String naziven;

//	@JsonIgnore
	//bi-directional many-to-one association to Dokument
//	@OneToMany(mappedBy="statusBean")
//	private List<Dokument> dokuments;

	//bi-directional many-to-one association to DokumentXStatus
//	@OneToMany(mappedBy="statusBean")
//	private List<DokumentXStatus> dokumentXStatuses;

	//bi-directional many-to-one association to RelKorisnikXStatus
//	@JsonIgnore //dodala, izmjena: umjesto JsonBackReference stavila JsonIgnore, radi ovako
//	@OneToMany(mappedBy="statusBean")
//	private List<RelKorisnikXStatus> relKorisnikXStatuses;

	//bi-directional many-to-many association to Notifikacija
//	@JsonIgnore //dodala
//	@ManyToMany
//	@JoinTable(
//		name="STATUS_X_NOTIFIKACIJA"
//		, joinColumns={
//			@JoinColumn(name="STATUS")
//			}
//		, inverseJoinColumns={
//			@JoinColumn(name="NOTIFIKACIJA")
//			}
//		)
//	private List<Notifikacija> notifikacijas;

	//bi-directional many-to-one association to UlogaXStatus
//	@OneToMany(mappedBy="statusBean")
//	private List<UlogaXStatus> ulogaXStatuses;

//	@JsonIgnore
	//bi-directional many-to-many association to VrstaDokumenta
//	@ManyToMany(mappedBy="statuses")
//	private List<VrstaDokumenta> vrstaDokumentas;

	//private char deleted;
	//@Length(max = 1)
	//@Column(nullable = false,name="deleted")
	private String deleted;

	public Status() {
	}

	public Status(String nazivba, String naziven) {
		this.naziven = naziven;
		this.nazivba = nazivba;
		this.deleted = "0";
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

	@JsonIgnore
	@ManyToMany(mappedBy = "statusSet", fetch = FetchType.EAGER)
	private Set<Dokument> dokumentSet;

	public Set<Dokument> getDokumentSet() {
		return dokumentSet;
	}

	public void setDokumentSet(Set<Dokument> dokumentSet) {
		this.dokumentSet = dokumentSet;
	}

//	public List<Dokument> getDokuments() {
//		return this.dokuments;
//	}

//	public void setDokuments(List<Dokument> dokuments) {
//		this.dokuments = dokuments;
//	}

//	public Dokument addDokument(Dokument dokument) {
//		getDokuments().add(dokument);
//		dokument.setStatusBean(this);
//		return dokument;
//	}

//	public Dokument removeDokument(Dokument dokument) {
//		getDokuments().remove(dokument);
//		dokument.setStatusBean(null);
//		return dokument;
//	}

//	public List<DokumentXStatus> getDokumentXStatuses() {
//		return this.dokumentXStatuses;
//	}

//	public void setDokumentXStatuses(List<DokumentXStatus> dokumentXStatuses) {
//		this.dokumentXStatuses = dokumentXStatuses;
//	}

//	public DokumentXStatus addDokumentXStatus(DokumentXStatus dokumentXStatus) {
//		getDokumentXStatuses().add(dokumentXStatus);
//		dokumentXStatus.setStatusBean(this);
//		return dokumentXStatus;
//	}

//	public DokumentXStatus removeDokumentXStatus(DokumentXStatus dokumentXStatus) {
//		getDokumentXStatuses().remove(dokumentXStatus);
//		dokumentXStatus.setStatusBean(null);
//		return dokumentXStatus;
//	}

//	public List<RelKorisnikXStatus> getRelKorisnikXStatuses() {
//		return this.relKorisnikXStatuses;
//	}
//
//	public void setRelKorisnikXStatuses(List<RelKorisnikXStatus> relKorisnikXStatuses) {
//		this.relKorisnikXStatuses = relKorisnikXStatuses;
//	}
//
//	public RelKorisnikXStatus addRelKorisnikXStatus(RelKorisnikXStatus relKorisnikXStatus) {
//		getRelKorisnikXStatuses().add(relKorisnikXStatus);
//		relKorisnikXStatus.setStatusBean(this);
//		return relKorisnikXStatus;
//	}
//
//	public RelKorisnikXStatus removeRelKorisnikXStatus(RelKorisnikXStatus relKorisnikXStatus) {
//		getRelKorisnikXStatuses().remove(relKorisnikXStatus);
//		relKorisnikXStatus.setStatusBean(null);
//		return relKorisnikXStatus;
//	}
//
//	public List<Notifikacija> getNotifikacijas() {
//		return this.notifikacijas;
//	}
//
//	public void setNotifikacijas(List<Notifikacija> notifikacijas) {
//		this.notifikacijas = notifikacijas;
//	}

//	public List<UlogaXStatus> getUlogaXStatuses() {
//		return this.ulogaXStatuses;
//	}

//	public void setUlogaXStatuses(List<UlogaXStatus> ulogaXStatuses) {
//		this.ulogaXStatuses = ulogaXStatuses;
//	}

//	public UlogaXStatus addUlogaXStatus(UlogaXStatus ulogaXStatus) {
//		getUlogaXStatuses().add(ulogaXStatus);
//		ulogaXStatus.setStatusBean(this);
//		return ulogaXStatus;
//	}

//	public UlogaXStatus removeUlogaXStatus(UlogaXStatus ulogaXStatus) {
//		getUlogaXStatuses().remove(ulogaXStatus);
//		ulogaXStatus.setStatusBean(null);
//		return ulogaXStatus;
//	}

//	public List<VrstaDokumenta> getVrstaDokumentas() {
//		return this.vrstaDokumentas;
//	}

//	public void setVrstaDokumentas(List<VrstaDokumenta> vrstaDokumentas) {
//		this.vrstaDokumentas = vrstaDokumentas;
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