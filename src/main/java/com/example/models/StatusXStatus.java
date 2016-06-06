//package com.example.models;
//
//import java.io.Serializable;
//import javax.persistence.*;
//
//
///**
// * The persistent class for the STATUS_X_STATUS database table.
// *
// */
//@Entity @IdClass(StatusXStatusID.class)
//@Table(name="STATUS_X_STATUS")
////@NamedQuery(name="StatusXStatus.findAll", query="SELECT s FROM StatusXStatus s")
//public class StatusXStatus implements Serializable {
//    private static final long serialVersionUID = 1L;
//
//    //bi-directional many-to-one association to Korisnik
//    @Id
//    @ManyToOne
//    @JoinColumn(name="IZ_STATUSA")
//    private Status status1;
//
//    //bi-directional many-to-one association to Korisnik
//    @Id
//    @ManyToOne
//    @JoinColumn(name="U_STATUS")
//    private Status status2;
//
//    //bi-directional many-to-one association to RelacijaKorisnik
//    @Id
//    @ManyToOne
//    @JoinColumn(name="NA_DOGADJAJ")
//    private Dogadjaj dogadjaj;
//
//    public StatusXStatus() {
//    }
//
//    public Status getStatus1() {
//        return this.status1;
//    }
//
//    public void setStatus1(Status status1) {
//        this.status1 = status1;
//    }
//
//    public Status getStatus2() {
//        return this.status2;
//    }
//
//    public void setStatus2(Status status2) {
//        this.status2 = status2;
//    }
//
//    public Dogadjaj getDogadjaj() {
//        return this.dogadjaj;
//    }
//
//    public void setDogadjaj(Dogadjaj dogadjaj) {
//        this.dogadjaj = dogadjaj;
//    }
//
//    //dodao
//    public StatusXStatusID getId() {
//        StatusXStatusID id = new StatusXStatusID();
//        id.status1 = this.status1;
//        id.status2 = this.status2;
//        id.dogadjaj = this.dogadjaj;
//        return id;
//    }
//
//    public void setId(StatusXStatusID id) {
//        this.status1 = id.status1;
//        this.status2 = id.status2;
//        this.dogadjaj = id.dogadjaj;
//    }
//
//}
//
////class KorisnikXKorisnikID implements java.io.Serializable {
////	private static final long serialVersionUID = 1L;
////
////	Korisnik korisnik1;
////	Korisnik korisnik2;
////	RelacijaKorisnik relacijaKorisnik;
////}