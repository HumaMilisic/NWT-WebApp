package com.example.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ULOGA_X_STATUS database table.
 * 
 */
@Entity @IdClass(UlogaXStatusID.class)
@Table(name="ULOGA_X_STATUS")
@NamedQuery(name="UlogaXStatus.findAll", query="SELECT u FROM UlogaXStatus u")
public class UlogaXStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	private String brisanje;

	private String izmjena;

	private String kreiranje;

	private String potpisivanje;

	private String pregled;

	private String preuzimanje;

	//bi-directional many-to-one association to Status
	@Id
	@ManyToOne
	@JoinColumn(name="STATUS")
	private Status statusBean;

	//bi-directional many-to-one association to Uloga
	@Id
	@ManyToOne
	@JoinColumn(name="ULOGA")
	private Uloga ulogaBean;

	public UlogaXStatus() {
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

	public Status getStatusBean() {
		return this.statusBean;
	}

	public void setStatusBean(Status statusBean) {
		this.statusBean = statusBean;
	}

	public Uloga getUlogaBean() {
		return this.ulogaBean;
	}

	public void setUlogaBean(Uloga ulogaBean) {
		this.ulogaBean = ulogaBean;
	}
	
	//dodao
	public UlogaXStatusID getId() {
		UlogaXStatusID id = new UlogaXStatusID();
		id.ulogaBean = this.ulogaBean;
		id.statusBean = this.statusBean;
		return id;
	}
	
	public void setId(UlogaXStatusID id) {
		this.ulogaBean = id.ulogaBean;
		this.statusBean = id.statusBean;
	}

}

