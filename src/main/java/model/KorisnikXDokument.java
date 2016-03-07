package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the KORISNIK_X_DOKUMENT database table.
 * 
 */
@Entity @IdClass(KorisnikXDokumentID.class)
@Table(name="KORISNIK_X_DOKUMENT")
@NamedQuery(name="KorisnikXDokument.findAll", query="SELECT k FROM KorisnikXDokument k")
public class KorisnikXDokument implements Serializable {
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to Dokument
	@Id
	@ManyToOne
	@JoinColumn(name="DOKUMENT")
	private Dokument dokumentBean;

	//bi-directional many-to-one association to Korisnik
	@Id
	@ManyToOne
	@JoinColumn(name="KORISNIK")
	private Korisnik korisnikBean;

	//bi-directional many-to-one association to RelacijaDokument
	@Id
	@ManyToOne
	@JoinColumn(name="RELACIJA")
	private RelacijaDokument relacijaDokument;

	public KorisnikXDokument() {
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

	public RelacijaDokument getRelacijaDokument() {
		return this.relacijaDokument;
	}

	public void setRelacijaDokument(RelacijaDokument relacijaDokument) {
		this.relacijaDokument = relacijaDokument;
	}
	
	//dodao
	public KorisnikXDokumentID getId() {
		KorisnikXDokumentID id = new KorisnikXDokumentID();
		id.korisnikBean = this.korisnikBean;
		id.dokumentBean = this.dokumentBean;
		id.relacijaDokument = this.relacijaDokument;
		return id;
	}
					
	public void setId(KorisnikXDokumentID id) {
		this.korisnikBean = id.korisnikBean;
		this.dokumentBean = id.dokumentBean;
		this.relacijaDokument = id.relacijaDokument;
	}

}

