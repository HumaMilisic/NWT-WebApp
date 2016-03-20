package com.example.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SLIKA database table.
 *
 */
@Entity
@NamedQuery(name="Dogadjaj.findAll", query="SELECT d FROM Dogadjaj d")
public class Dogadjaj implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DogadjajSEQ")
    @SequenceGenerator(name = "DogadjajSEQ", sequenceName = "DOGADJAJ_SEQ", allocationSize = 1)
    private long id;

    private String naziv;

    //bi-directional many-to-one association to Dokument
    //@OneToMany(mappedBy="slikaBean")
    //private List<Dokument> dokuments;

    //private List<StatusXStatus> statusXStatuses;

    private char deleted;

    public Dogadjaj() {
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

    //public List<StatusXStatus> getStatusXStatuses() {
    //	return this.statusXStatuses;
    //}

    //public void setStatusXStatuses(List<StatusXStatus> statusXStatuses) {
    //	this.statusXStatuses = statusXStatuses;
    //}

    public StatusXStatus addStatusXStatus(StatusXStatus statusXStatus) {
        //getStatusXStatuses().add(statusXStatus);
        //statusXStatus.setDogadjaj(this);

        return statusXStatus;
    }

    public StatusXStatus removeStatusXStatus(StatusXStatus statusXStatus) {
        //getStatusXStatuses().remove(statusXStatus);
        //statusXStatus.setDogadjaj(null);

        return statusXStatus;
    }

    public char getDeleted() {
        return this.deleted;
    }

    public void setDeleted(char deleted) {
        this.deleted = deleted;
    }

}