package com.example.models;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the NOTIFIKACIJA database table.
 * 
 */
@Entity
//@NamedQuery(name="Notifikacija.findAll", query="SELECT n FROM Notifikacija n")
public class Notifikacija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NotifikacijaSEQ")
	@SequenceGenerator(name = "NotifikacijaSEQ", sequenceName = "NOTIFIKACIJA_SEQ", allocationSize = 1)
	@Generated(GenerationTime.INSERT) //dodano
	private long id;

	@Lob
	private String tekst;

	//bi-directional many-to-one association to RelKorisnikXNotifikacija
	//@OneToMany(mappedBy="notifikacijaBean") //, fetch = FetchType.EAGER)
	//private List<RelKorisnikXNotifikacija> relKorisnikXNotifikacijas;

	//bi-directional many-to-many association to Status
	//@ManyToMany(mappedBy="notifikacijas")
	//private List<Status> statuses;

	//bi-directional many-to-one association to UlogaXNotifikacija
	//@OneToMany(mappedBy="notifikacijaBean")
	//private List<UlogaXNotifikacija> ulogaXNotifikacijas;

	//private char deleted;
	@Length(max = 1)
	@Column(nullable = false,name="deleted")
	private String deleted;

	public Notifikacija() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTekst() {
		return this.tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

//	public List<RelKorisnikXNotifikacija> getRelKorisnikXNotifikacijas() {
//		return this.relKorisnikXNotifikacijas;
//	}

//	public void setRelKorisnikXNotifikacijas(List<RelKorisnikXNotifikacija> relKorisnikXNotifikacijas) {
//		this.relKorisnikXNotifikacijas = relKorisnikXNotifikacijas;
//	}

//	public RelKorisnikXNotifikacija addRelKorisnikXNotifikacija(RelKorisnikXNotifikacija relKorisnikXNotifikacija) {
//		getRelKorisnikXNotifikacijas().add(relKorisnikXNotifikacija);
//		relKorisnikXNotifikacija.setNotifikacijaBean(this);

//		return relKorisnikXNotifikacija;
//	}

//	public RelKorisnikXNotifikacija removeRelKorisnikXNotifikacija(RelKorisnikXNotifikacija relKorisnikXNotifikacija) {
//		getRelKorisnikXNotifikacijas().remove(relKorisnikXNotifikacija);
//		relKorisnikXNotifikacija.setNotifikacijaBean(null);

//		return relKorisnikXNotifikacija;
//	}

//	public List<Status> getStatuses() {
//		return this.statuses;
//	}

//	public void setStatuses(List<Status> statuses) {
//		this.statuses = statuses;
//	}

//	public List<UlogaXNotifikacija> getUlogaXNotifikacijas() {
//		return this.ulogaXNotifikacijas;
//	}

//	public void setUlogaXNotifikacijas(List<UlogaXNotifikacija> ulogaXNotifikacijas) {
//		this.ulogaXNotifikacijas = ulogaXNotifikacijas;
//	}

//	public UlogaXNotifikacija addUlogaXNotifikacija(UlogaXNotifikacija ulogaXNotifikacija) {
//		getUlogaXNotifikacijas().add(ulogaXNotifikacija);
//		ulogaXNotifikacija.setNotifikacijaBean(this);

//		return ulogaXNotifikacija;
//	}

//	public UlogaXNotifikacija removeUlogaXNotifikacija(UlogaXNotifikacija ulogaXNotifikacija) {
//		getUlogaXNotifikacijas().remove(ulogaXNotifikacija);
//		ulogaXNotifikacija.setNotifikacijaBean(null);

//		return ulogaXNotifikacija;
//	}

	public String getDeleted() { //bilo char
		return this.deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}