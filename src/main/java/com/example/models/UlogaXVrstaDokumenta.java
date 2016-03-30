package com.example.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ULOGA_X_VRSTA_DOKUMENTA database table.
 * 
 */
@Entity @IdClass(UlogaXVrstaDokumentaID.class)
@Table(name="ULOGA_X_VRSTA_DOKUMENTA")
//@NamedQuery(name="UlogaXVrstaDokumenta.findAll", query="SELECT u FROM UlogaXVrstaDokumenta u")
public class UlogaXVrstaDokumenta implements Serializable {
	private static final long serialVersionUID = 1L;

	private String brisanje;

	private String izmjena;

	private String kreiranje;

	private String potpisivanje;

	private String pregled;

	private String preuzimanje;

	//bi-directional many-to-one association to Uloga
	@Id
	@ManyToOne
	@JoinColumn(name="ULOGA")
	private Uloga ulogaBean;

	//bi-directional many-to-one association to VrstaDokumenta
	@Id
	@ManyToOne
	@JoinColumn(name="VRSTA_DOKUMENTA")
	private VrstaDokumenta vrstaDokumentaBean;

	public UlogaXVrstaDokumenta() {
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

	public Uloga getUlogaBean() {
		return this.ulogaBean;
	}

	public void setUlogaBean(Uloga ulogaBean) {
		this.ulogaBean = ulogaBean;
	}

	public VrstaDokumenta getVrstaDokumentaBean() {
		return this.vrstaDokumentaBean;
	}

	public void setVrstaDokumentaBean(VrstaDokumenta vrstaDokumentaBean) {
		this.vrstaDokumentaBean = vrstaDokumentaBean;
	}
	
	//dodao
	public UlogaXVrstaDokumentaID getId() {
		UlogaXVrstaDokumentaID id = new UlogaXVrstaDokumentaID();
		id.ulogaBean = this.ulogaBean;
		id.vrstaDokumentaBean = this.vrstaDokumentaBean;
		return id;
	}
	
	public void setId(UlogaXVrstaDokumentaID id) {
		this.ulogaBean = id.ulogaBean;
		this.vrstaDokumentaBean = id.vrstaDokumentaBean;
	}

}

