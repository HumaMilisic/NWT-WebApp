package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the KORISNIK_X_KORISNIK database table.
 * 
 */
@Entity @IdClass(KorisnikXKorisnikID.class)
@Table(name="KORISNIK_X_KORISNIK")
@NamedQuery(name="KorisnikXKorisnik.findAll", query="SELECT k FROM KorisnikXKorisnik k")
public class KorisnikXKorisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	//bi-directional many-to-one association to Korisnik
	@Id
	@ManyToOne
	@JoinColumn(name="JE_OVOM")
	private Korisnik korisnik1;

	//bi-directional many-to-one association to Korisnik
	@Id
	@ManyToOne
	@JoinColumn(name="OVAJ")
	private Korisnik korisnik2;

	//bi-directional many-to-one association to RelacijaKorisnik
	@Id
	@ManyToOne
	@JoinColumn(name="RELACIJA")
	private RelacijaKorisnik relacijaKorisnik;

	public KorisnikXKorisnik() {
	}

	public Korisnik getKorisnik1() {
		return this.korisnik1;
	}

	public void setKorisnik1(Korisnik korisnik1) {
		this.korisnik1 = korisnik1;
	}

	public Korisnik getKorisnik2() {
		return this.korisnik2;
	}

	public void setKorisnik2(Korisnik korisnik2) {
		this.korisnik2 = korisnik2;
	}

	public RelacijaKorisnik getRelacijaKorisnik() {
		return this.relacijaKorisnik;
	}

	public void setRelacijaKorisnik(RelacijaKorisnik relacijaKorisnik) {
		this.relacijaKorisnik = relacijaKorisnik;
	}
	
	//dodao
	public KorisnikXKorisnikID getId() {
		KorisnikXKorisnikID id = new KorisnikXKorisnikID();
		id.korisnik1 = this.korisnik1;
		id.korisnik2 = this.korisnik2;
		id.relacijaKorisnik = this.relacijaKorisnik;
		return id;
	}
				
	public void setId(KorisnikXKorisnikID id) {
		this.korisnik1 = id.korisnik1;
		this.korisnik2 = id.korisnik2;
		this.relacijaKorisnik = id.relacijaKorisnik;
	}

}

//class KorisnikXKorisnikID implements java.io.Serializable {
//	private static final long serialVersionUID = 1L;
//
//	Korisnik korisnik1;
//	Korisnik korisnik2;
//	RelacijaKorisnik relacijaKorisnik;
//}