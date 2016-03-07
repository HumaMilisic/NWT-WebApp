package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the REL_KORISNIK_X_NOTIFIKACIJA database table.
 * 
 */
@Entity @IdClass(RelKorisnikXNotifikacijaID.class)
@Table(name="REL_KORISNIK_X_NOTIFIKACIJA")
@NamedQuery(name="RelKorisnikXNotifikacija.findAll", query="SELECT r FROM RelKorisnikXNotifikacija r")
public class RelKorisnikXNotifikacija implements Serializable {
	private static final long serialVersionUID = 1L;

	private String aplikacija;

	private String email;

	//bi-directional many-to-one association to Notifikacija
	@Id
	@ManyToOne
	@JoinColumn(name="NOTIFIKACIJA")
	private Notifikacija notifikacijaBean;

	//bi-directional many-to-one association to RelacijaKorisnik
	@Id
	@ManyToOne
	@JoinColumn(name="RELACIJA_KORISNIK")
	private RelacijaKorisnik relacijaKorisnikBean;

	public RelKorisnikXNotifikacija() {
	}

	public String getAplikacija() {
		return this.aplikacija;
	}

	public void setAplikacija(String aplikacija) {
		this.aplikacija = aplikacija;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Notifikacija getNotifikacijaBean() {
		return this.notifikacijaBean;
	}

	public void setNotifikacijaBean(Notifikacija notifikacijaBean) {
		this.notifikacijaBean = notifikacijaBean;
	}

	public RelacijaKorisnik getRelacijaKorisnikBean() {
		return this.relacijaKorisnikBean;
	}

	public void setRelacijaKorisnikBean(RelacijaKorisnik relacijaKorisnikBean) {
		this.relacijaKorisnikBean = relacijaKorisnikBean;
	}
	
	//dodao
	public RelKorisnikXNotifikacijaID getId() {
		RelKorisnikXNotifikacijaID id = new RelKorisnikXNotifikacijaID();
		id.relacijaKorisnikBean = this.relacijaKorisnikBean;
		id.notifikacijaBean = this.notifikacijaBean;
		return id;
	}
			
	public void setId(RelKorisnikXNotifikacijaID id) {
		this.relacijaKorisnikBean = id.relacijaKorisnikBean;
		this.notifikacijaBean = id.notifikacijaBean;
	}

}

