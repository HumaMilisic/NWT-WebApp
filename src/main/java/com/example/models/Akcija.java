package com.example.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the AKCIJA database table.
 * 
 */
@Entity
//@Table(name = "Akcija")
//@NamedQuery(name="Akcija.findAll", query="SELECT a FROM Akcija a")
//@Where(clause = "deleted = 0")
public class Akcija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue
	//@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {})
//	@GeneratedValue(generator = "AKCIJA_SEQ",strategy = GenerationType.SEQUENCE)
//	@Generated(GenerationTime.INSERT)

	@GeneratedValue(strategy = GenerationType.AUTO) //strategy = GenerationType.SEQUENCE, generator = "AkcijaSEQ")
//	@SequenceGenerator(name = "AkcijaSEQ", sequenceName = "AKCIJA_SEQ", allocationSize = 1)
	private long id;

	@Length(max = 100)
	@Column(nullable = false)
	private String naziv;

	//bi-directional many-to-many association to Uloga
//	@ManyToMany(mappedBy="akcijas")
//	private List<Uloga> ulogas;


	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	//private char deleted;
	@Length(max = 1)
	@Column(nullable = false,name="deleted")
	private String deleted;

	protected Akcija() {
	}

	public Akcija(String naziv){
		this.naziv = naziv;
		this.deleted = "0";
	}
//	public Akcija(String name){
//		this.naziv = name;
//	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

//	public List<Uloga> getUlogas() {
//		return this.ulogas;
//	}

//	public void setUlogas(List<Uloga> ulogas) {
//		this.ulogas = ulogas;
//	}

}