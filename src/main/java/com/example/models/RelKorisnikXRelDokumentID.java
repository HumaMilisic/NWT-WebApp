//package com.example.models;
//
///**
// * Created by Owner on 16.11.2015.
// */
//public class RelKorisnikXRelDokumentID implements java.io.Serializable {
//    private static final long serialVersionUID = 1L;
//
//    public RelKorisnikXRelDokumentID() {}
//
//    public RelacijaDokument relacijaDokumentBean;
//    public RelacijaKorisnik relacijaKorisnikBean;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof RelKorisnikXRelDokumentID)) return false;
//
//        RelKorisnikXRelDokumentID that = (RelKorisnikXRelDokumentID) o;
//
//        if (relacijaDokumentBean != null ? !relacijaDokumentBean.equals(that.relacijaDokumentBean) : that.relacijaDokumentBean != null)
//            return false;
//        return !(relacijaKorisnikBean != null ? !relacijaKorisnikBean.equals(that.relacijaKorisnikBean) : that.relacijaKorisnikBean != null);
//
//    }
//
//    @Override
//    public int hashCode() {
//        int result = relacijaDokumentBean != null ? relacijaDokumentBean.hashCode() : 0;
//        result = 31 * result + (relacijaKorisnikBean != null ? relacijaKorisnikBean.hashCode() : 0);
//        return result;
//    }
//}
