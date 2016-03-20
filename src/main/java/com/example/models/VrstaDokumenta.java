package com.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the VRSTA_DOKUMENTA database table.
 * 
 */
@Entity
@Table(name="VRSTA_DOKUMENTA")
@NamedQuery(name="VrstaDokumenta.findAll", query="SELECT v FROM VrstaDokumenta v")
public class VrstaDokumenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VrstaDokumentaSEQ")
	@SequenceGenerator(name = "VrstaDokumentaSEQ", sequenceName = "VRSTA_DOKUMENTA_SEQ", allocationSize = 1)
	private long id;

	private String nazivba;

	private String naziven;

	private String templateba;

	private String templateen;

	@JsonIgnore //proba
	//bi-directional many-to-one association to Dokument
	@OneToMany(mappedBy="vrstaDokumenta")
	private List<Dokument> dokuments;

	//bi-directional many-to-one association to RelKorisnikXVrstaDoc
	@OneToMany(mappedBy="vrstaDokumentaBean")
	private List<RelKorisnikXVrstaDoc> relKorisnikXVrstaDocs;

	//bi-directional many-to-one association to UlogaXVrstaDokumenta
	@OneToMany(mappedBy="vrstaDokumentaBean")
	private List<UlogaXVrstaDokumenta> ulogaXVrstaDokumentas;

	//bi-directional many-to-many association to Status
	@ManyToMany
	@JoinTable(
		name="VRSTA_DOKUMENTA_X_STATUS"
		, joinColumns={
			@JoinColumn(name="VRSTA_DOKUMENTA")
			}
		, inverseJoinColumns={
			@JoinColumn(name="STATUS")
			}
		)
	private List<Status> statuses;

	private char deleted;

	public VrstaDokumenta() {
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

	public String getTemplateba() {
		return this.templateba;
	}

	public void setTemplateba(String templateba) {
		this.templateba = templateba;
	}

	public String getTemplateen() {
		return this.templateen;
	}

	public void setTemplateen(String templateen) {
		this.templateen = templateen;
	}

	public List<Dokument> getDokuments() {
		return this.dokuments;
	}

	public void setDokuments(List<Dokument> dokuments) {
		this.dokuments = dokuments;
	}

	public Dokument addDokument(Dokument dokument) {
		getDokuments().add(dokument);
		dokument.setVrstaDokumenta(this);

		return dokument;
	}

	public Dokument removeDokument(Dokument dokument) {
		getDokuments().remove(dokument);
		dokument.setVrstaDokumenta(null);

		return dokument;
	}

	public List<RelKorisnikXVrstaDoc> getRelKorisnikXVrstaDocs() {
		return this.relKorisnikXVrstaDocs;
	}

	public void setRelKorisnikXVrstaDocs(List<RelKorisnikXVrstaDoc> relKorisnikXVrstaDocs) {
		this.relKorisnikXVrstaDocs = relKorisnikXVrstaDocs;
	}

	public RelKorisnikXVrstaDoc addRelKorisnikXVrstaDoc(RelKorisnikXVrstaDoc relKorisnikXVrstaDoc) {
		getRelKorisnikXVrstaDocs().add(relKorisnikXVrstaDoc);
		relKorisnikXVrstaDoc.setVrstaDokumentaBean(this);

		return relKorisnikXVrstaDoc;
	}

	public RelKorisnikXVrstaDoc removeRelKorisnikXVrstaDoc(RelKorisnikXVrstaDoc relKorisnikXVrstaDoc) {
		getRelKorisnikXVrstaDocs().remove(relKorisnikXVrstaDoc);
		relKorisnikXVrstaDoc.setVrstaDokumentaBean(null);

		return relKorisnikXVrstaDoc;
	}

	public List<UlogaXVrstaDokumenta> getUlogaXVrstaDokumentas() {
		return this.ulogaXVrstaDokumentas;
	}

	public void setUlogaXVrstaDokumentas(List<UlogaXVrstaDokumenta> ulogaXVrstaDokumentas) {
		this.ulogaXVrstaDokumentas = ulogaXVrstaDokumentas;
	}

	public UlogaXVrstaDokumenta addUlogaXVrstaDokumenta(UlogaXVrstaDokumenta ulogaXVrstaDokumenta) {
		getUlogaXVrstaDokumentas().add(ulogaXVrstaDokumenta);
		ulogaXVrstaDokumenta.setVrstaDokumentaBean(this);

		return ulogaXVrstaDokumenta;
	}

	public UlogaXVrstaDokumenta removeUlogaXVrstaDokumenta(UlogaXVrstaDokumenta ulogaXVrstaDokumenta) {
		getUlogaXVrstaDokumentas().remove(ulogaXVrstaDokumenta);
		ulogaXVrstaDokumenta.setVrstaDokumentaBean(null);

		return ulogaXVrstaDokumenta;
	}

	public List<Status> getStatuses() {
		return this.statuses;
	}

	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
	}

	public char getDeleted() {
		return this.deleted;
	}

	public void setDeleted(char deleted) {
		this.deleted = deleted;
	}

}