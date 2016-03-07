package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the DOKUMENT_X_STATUS database table.
 * 
 */
@Entity @IdClass(DokumentXStatusID.class)
@Table(name="DOKUMENT_X_STATUS")
@NamedQuery(name="DokumentXStatus.findAll", query="SELECT d FROM DokumentXStatus d")
public class DokumentXStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Timestamp izmjena;

	//bi-directional many-to-one association to Dokument
	@Id
	@ManyToOne
	@JoinColumn(name="DOKUMENT")
	private Dokument dokumentBean;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="KORISNIK")
	private Korisnik korisnikBean;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="STATUS")
	private Status statusBean;

	public DokumentXStatus() {
	}

	public Timestamp getIzmjena() {
		return this.izmjena;
	}

	public void setIzmjena(Timestamp izmjena) {
		this.izmjena = izmjena;
	}

	public Dokument getDokumentBean() {
		return this.dokumentBean;
	}

	public void setDokumentBean(Dokument dokumentBean) {
		this.dokumentBean = dokumentBean;
	}

	public Korisnik getKorisnikBean() {
		return this.korisnikBean;
	}

	public void setKorisnikBean(Korisnik korisnikBean) {
		this.korisnikBean = korisnikBean;
	}

	public Status getStatusBean() {
		return this.statusBean;
	}

	public void setStatusBean(Status statusBean) {
		this.statusBean = statusBean;
	}
	
	//dodao
	public DokumentXStatusID getId() {
		DokumentXStatusID id = new DokumentXStatusID();
		id.izmjena = this.izmjena;
		id.dokumentBean = this.dokumentBean;
		return id;
	}
						
	public void setId(DokumentXStatusID id) {
		this.izmjena = id.izmjena;
		this.dokumentBean = id.dokumentBean;
	}

}

