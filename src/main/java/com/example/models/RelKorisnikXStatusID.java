package com.example.models;

/**
 * Created by Owner on 16.11.2015.
 */
public class RelKorisnikXStatusID implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public RelKorisnikXStatusID() {}

    public RelacijaKorisnik relacijaKorisnikBean;
    public Status statusBean;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RelKorisnikXStatusID)) return false;

        RelKorisnikXStatusID that = (RelKorisnikXStatusID) o;

        if (relacijaKorisnikBean != null ? !relacijaKorisnikBean.equals(that.relacijaKorisnikBean) : that.relacijaKorisnikBean != null)
            return false;
        return !(statusBean != null ? !statusBean.equals(that.statusBean) : that.statusBean != null);

    }

    @Override
    public int hashCode() {
        int result = relacijaKorisnikBean != null ? relacijaKorisnikBean.hashCode() : 0;
        result = 31 * result + (statusBean != null ? statusBean.hashCode() : 0);
        return result;
    }
}
