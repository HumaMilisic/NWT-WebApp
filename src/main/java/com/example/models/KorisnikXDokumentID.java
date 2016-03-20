package com.example.models;

/**
 * Created by Owner on 16.11.2015.
 */
public class KorisnikXDokumentID implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public KorisnikXDokumentID() {}

    public Dokument dokumentBean;
    public Korisnik korisnikBean;
    public RelacijaDokument relacijaDokument;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KorisnikXDokumentID)) return false;

        KorisnikXDokumentID that = (KorisnikXDokumentID) o;

        if (dokumentBean != null ? !dokumentBean.equals(that.dokumentBean) : that.dokumentBean != null) return false;
        if (korisnikBean != null ? !korisnikBean.equals(that.korisnikBean) : that.korisnikBean != null) return false;
        return !(relacijaDokument != null ? !relacijaDokument.equals(that.relacijaDokument) : that.relacijaDokument != null);

    }

    @Override
    public int hashCode() {
        int result = dokumentBean != null ? dokumentBean.hashCode() : 0;
        result = 31 * result + (korisnikBean != null ? korisnikBean.hashCode() : 0);
        result = 31 * result + (relacijaDokument != null ? relacijaDokument.hashCode() : 0);
        return result;
    }
}
