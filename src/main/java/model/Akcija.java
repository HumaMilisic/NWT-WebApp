package model;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the AKCIJA database table.
 * 
 */
@Entity
@NamedQuery(name="Akcija.findAll", query="SELECT a FROM Akcija a")
public class Akcija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GenericGenerator(name = "sequence", strategy = "sequence", parameters = {})
	//@GeneratedValue(generator = "AKCIJA_SEQ",strategy = GenerationType.SEQUENCE)
	////@Generated(GenerationTime.INSERT)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AkcijaSEQ")
	@SequenceGenerator(name = "AkcijaSEQ", sequenceName = "AKCIJA_SEQ", allocationSize = 1)
	private long id;

	private String naziv;

	//bi-directional many-to-many association to Uloga
	@ManyToMany(mappedBy="akcijas")
	private List<Uloga> ulogas;

	private char deleted;

	public Akcija() {
	}

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

	public List<Uloga> getUlogas() {
		return this.ulogas;
	}

	public void setUlogas(List<Uloga> ulogas) {
		this.ulogas = ulogas;
	}

	public char getDeleted() {
		return this.deleted;
	}

	public void setDeleted(char deleted) {
		this.deleted = deleted;
	}

}