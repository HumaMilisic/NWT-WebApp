package com.example.models;

import com.example.utils.validators.ValidBool;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the SLIKA database table.
 *
 */
@Entity
//@NamedQuery(name="Dogadjaj.findAll", query="SELECT d FROM Dogadjaj d")
public class Dogadjaj implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DogadjajSEQ")
    @SequenceGenerator(name = "DogadjajSEQ", sequenceName = "DOGADJAJ_SEQ", allocationSize = 1)
    private long id;

    @Length(max = 15)
    private String naziv;

    //bi-directional many-to-one association to Dokument
    //@OneToMany(mappedBy="slikaBean")
    //private List<Dokument> dokuments;

    //private List<StatusXStatus> statusXStatuses;

 //   @ValidBool
    private String deleted;

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

//    public StatusXStatus addStatusXStatus(StatusXStatus statusXStatus) {
//        //getStatusXStatuses().add(statusXStatus);
//        //statusXStatus.setDogadjaj(this);
//
//        return statusXStatus;
//    }
//
//    public StatusXStatus removeStatusXStatus(StatusXStatus statusXStatus) {
//        //getStatusXStatuses().remove(statusXStatus);
//        //statusXStatus.setDogadjaj(null);
//
//        return statusXStatus;
//    }

    public String getDeleted() {
        return this.deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

}