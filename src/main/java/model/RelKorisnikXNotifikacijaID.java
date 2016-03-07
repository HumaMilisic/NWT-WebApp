package model;

/**
 * Created by Owner on 16.11.2015.
 */
public class RelKorisnikXNotifikacijaID implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public RelKorisnikXNotifikacijaID() {}

    public Notifikacija notifikacijaBean;
    public RelacijaKorisnik relacijaKorisnikBean;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RelKorisnikXNotifikacijaID)) return false;

        RelKorisnikXNotifikacijaID that = (RelKorisnikXNotifikacijaID) o;

        if (notifikacijaBean != null ? !notifikacijaBean.equals(that.notifikacijaBean) : that.notifikacijaBean != null)
            return false;
        return !(relacijaKorisnikBean != null ? !relacijaKorisnikBean.equals(that.relacijaKorisnikBean) : that.relacijaKorisnikBean != null);

    }

    @Override
    public int hashCode() {
        int result = notifikacijaBean != null ? notifikacijaBean.hashCode() : 0;
        result = 31 * result + (relacijaKorisnikBean != null ? relacijaKorisnikBean.hashCode() : 0);
        return result;
    }
}
