package com.example.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ULOGA database table.
 * 
 */
@Entity
@NamedQuery(name="Uloga.findAll", query="SELECT u FROM Uloga u")
public class Uloga implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UlogaSEQ")
	@SequenceGenerator(name = "UlogaSEQ", sequenceName = "ULOGA_SEQ", allocationSize = 1)
	private long id;

	private String naziv;

	//bi-directional many-to-many association to Korisnik
	@ManyToMany
	@JoinTable(
		name="KORISNIK_X_ULOGA"
		, joinColumns={
			@JoinColumn(name="ULOGA")
			}
		, inverseJoinColumns={
			@JoinColumn(name="KORISNIK")
			}
		)
	private List<Korisnik> korisniks;

	//bi-directional many-to-many association to Akcija
	@ManyToMany
	@JoinTable(
		name="ULOGA_X_AKCIJA"
		, joinColumns={
			@JoinColumn(name="ULOGA")
			}
		, inverseJoinColumns={
			@JoinColumn(name="AKCIJA")
			}
		)
	private List<Akcija> akcijas;

	//bi-directional many-to-one association to UlogaXNotifikacija
	@OneToMany(mappedBy="ulogaBean")
	private List<UlogaXNotifikacija> ulogaXNotifikacijas;

	//bi-directional many-to-one association to UlogaXStatus
	@OneToMany(mappedBy="ulogaBean")
	private List<UlogaXStatus> ulogaXStatuses;

	//bi-directional many-to-one association to UlogaXVrstaDokumenta
	@OneToMany(mappedBy="ulogaBean")
	private List<UlogaXVrstaDokumenta> ulogaXVrstaDokumentas;

	private char deleted;

	public Uloga() {
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

	public List<Korisnik> getKorisniks() {
		return this.korisniks;
	}

	public void setKorisniks(List<Korisnik> korisniks) {
		this.korisniks = korisniks;
	}

	public List<Akcija> getAkcijas() {
		return this.akcijas;
	}

	public void setAkcijas(List<Akcija> akcijas) {
		this.akcijas = akcijas;
	}

	public List<UlogaXNotifikacija> getUlogaXNotifikacijas() {
		return this.ulogaXNotifikacijas;
	}

	public void setUlogaXNotifikacijas(List<UlogaXNotifikacija> ulogaXNotifikacijas) {
		this.ulogaXNotifikacijas = ulogaXNotifikacijas;
	}

	public UlogaXNotifikacija addUlogaXNotifikacija(UlogaXNotifikacija ulogaXNotifikacija) {
		getUlogaXNotifikacijas().add(ulogaXNotifikacija);
		ulogaXNotifikacija.setUlogaBean(this);

		return ulogaXNotifikacija;
	}

	public UlogaXNotifikacija removeUlogaXNotifikacija(UlogaXNotifikacija ulogaXNotifikacija) {
		getUlogaXNotifikacijas().remove(ulogaXNotifikacija);
		ulogaXNotifikacija.setUlogaBean(null);

		return ulogaXNotifikacija;
	}

	public List<UlogaXStatus> getUlogaXStatuses() {
		return this.ulogaXStatuses;
	}

	public void setUlogaXStatuses(List<UlogaXStatus> ulogaXStatuses) {
		this.ulogaXStatuses = ulogaXStatuses;
	}

	public UlogaXStatus addUlogaXStatus(UlogaXStatus ulogaXStatus) {
		getUlogaXStatuses().add(ulogaXStatus);
		ulogaXStatus.setUlogaBean(this);

		return ulogaXStatus;
	}

	public UlogaXStatus removeUlogaXStatus(UlogaXStatus ulogaXStatus) {
		getUlogaXStatuses().remove(ulogaXStatus);
		ulogaXStatus.setUlogaBean(null);

		return ulogaXStatus;
	}

	public List<UlogaXVrstaDokumenta> getUlogaXVrstaDokumentas() {
		return this.ulogaXVrstaDokumentas;
	}

	public void setUlogaXVrstaDokumentas(List<UlogaXVrstaDokumenta> ulogaXVrstaDokumentas) {
		this.ulogaXVrstaDokumentas = ulogaXVrstaDokumentas;
	}

	public UlogaXVrstaDokumenta addUlogaXVrstaDokumenta(UlogaXVrstaDokumenta ulogaXVrstaDokumenta) {
		getUlogaXVrstaDokumentas().add(ulogaXVrstaDokumenta);
		ulogaXVrstaDokumenta.setUlogaBean(this);

		return ulogaXVrstaDokumenta;
	}

	public UlogaXVrstaDokumenta removeUlogaXVrstaDokumenta(UlogaXVrstaDokumenta ulogaXVrstaDokumenta) {
		getUlogaXVrstaDokumentas().remove(ulogaXVrstaDokumenta);
		ulogaXVrstaDokumenta.setUlogaBean(null);

		return ulogaXVrstaDokumenta;
	}

	public char getDeleted() {
		return this.deleted;
	}

	public void setDeleted(char deleted) {
		this.deleted = deleted;
	}

}