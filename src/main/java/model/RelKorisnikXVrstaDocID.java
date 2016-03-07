package model;

/**
 * Created by Owner on 16.11.2015.
 */
public class RelKorisnikXVrstaDocID implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public RelKorisnikXVrstaDocID() {}

    public RelacijaKorisnik relacijaKorisnikBean;
    public VrstaDokumenta vrstaDokumentaBean;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RelKorisnikXVrstaDocID)) return false;

        RelKorisnikXVrstaDocID that = (RelKorisnikXVrstaDocID) o;

        if (relacijaKorisnikBean != null ? !relacijaKorisnikBean.equals(that.relacijaKorisnikBean) : that.relacijaKorisnikBean != null)
            return false;
        return !(vrstaDokumentaBean != null ? !vrstaDokumentaBean.equals(that.vrstaDokumentaBean) : that.vrstaDokumentaBean != null);

    }

    @Override
    public int hashCode() {
        int result = relacijaKorisnikBean != null ? relacijaKorisnikBean.hashCode() : 0;
        result = 31 * result + (vrstaDokumentaBean != null ? vrstaDokumentaBean.hashCode() : 0);
        return result;
    }
}
