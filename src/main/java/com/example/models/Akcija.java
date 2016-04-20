package com.example.models;

import com.example.utils.validators.ValidBool;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


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
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "AKCIJA_SEQ")
	@SequenceGenerator(name = "AKCIJA_SEQ",sequenceName = "AKCIJA_SEQ")
	@Generated(GenerationTime.INSERT)
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
	@ValidBool
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

	@ManyToMany(mappedBy = "akcijaSet",fetch = FetchType.EAGER)
	private Set<Uloga> ulogaSet;

	public Set<Uloga> getUlogaSet() {
		return ulogaSet;
	}

	public void setUlogaSet(Set<Uloga> ulogaSet) {
		this.ulogaSet = ulogaSet;
	}
	//	public List<Uloga> getUlogas() {
//		return this.ulogas;
//	}

//	public void setUlogas(List<Uloga> ulogas) {
//		this.ulogas = ulogas;
//	}

}