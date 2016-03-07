package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the REL_KORISNIK_X_VRSTA_DOC database table.
 * 
 */
@Entity @IdClass(RelKorisnikXVrstaDocID.class)
@Table(name="REL_KORISNIK_X_VRSTA_DOC")
@NamedQuery(name="RelKorisnikXVrstaDoc.findAll", query="SELECT r FROM RelKorisnikXVrstaDoc r")
public class RelKorisnikXVrstaDoc implements Serializable {
	private static final long serialVersionUID = 1L;

	private String brisanje;

	private String izmjena;

	private String kreiranje;

	private String potpisivanje;

	private String pregled;

	private String preuzimanje;

	//bi-directional many-to-one association to RelacijaKorisnik
	@Id
	@ManyToOne
	@JoinColumn(name="RELACIJA_KORISNIK")
	private RelacijaKorisnik relacijaKorisnikBean;

	//bi-directional many-to-one association to VrstaDokumenta
	@Id
	@ManyToOne
	@JoinColumn(name="VRSTA_DOKUMENTA")
	private VrstaDokumenta vrstaDokumentaBean;

	public RelKorisnikXVrstaDoc() {
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

	public RelacijaKorisnik getRelacijaKorisnikBean() {
		return this.relacijaKorisnikBean;
	}

	public void setRelacijaKorisnikBean(RelacijaKorisnik relacijaKorisnikBean) {
		this.relacijaKorisnikBean = relacijaKorisnikBean;
	}

	public VrstaDokumenta getVrstaDokumentaBean() {
		return this.vrstaDokumentaBean;
	}

	public void setVrstaDokumentaBean(VrstaDokumenta vrstaDokumentaBean) {
		this.vrstaDokumentaBean = vrstaDokumentaBean;
	}
	
	//dodao
	public RelKorisnikXVrstaDocID getId() {
		RelKorisnikXVrstaDocID id = new RelKorisnikXVrstaDocID();
		id.relacijaKorisnikBean = this.relacijaKorisnikBean;
		id.vrstaDokumentaBean = this.vrstaDokumentaBean;
		return id;
	}
	
	public void setId(RelKorisnikXVrstaDocID id) {
		this.relacijaKorisnikBean = id.relacijaKorisnikBean;
		this.vrstaDokumentaBean = id.vrstaDokumentaBean;
	}

}

