package model;

/**
 * Created by Owner on 16.11.2015.
 */
public class KorisnikXKorisnikID implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public KorisnikXKorisnikID() {}

    public Korisnik korisnik1;
    public Korisnik korisnik2;
    public RelacijaKorisnik relacijaKorisnik;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KorisnikXKorisnikID)) return false;

        KorisnikXKorisnikID that = (KorisnikXKorisnikID) o;

        if (korisnik1 != null ? !korisnik1.equals(that.korisnik1) : that.korisnik1 != null) return false;
        if (korisnik2 != null ? !korisnik2.equals(that.korisnik2) : that.korisnik2 != null) return false;
        return !(relacijaKorisnik != null ? !relacijaKorisnik.equals(that.relacijaKorisnik) : that.relacijaKorisnik != null);

    }

    @Override
    public int hashCode() {
        int result = korisnik1 != null ? korisnik1.hashCode() : 0;
        result = 31 * result + (korisnik2 != null ? korisnik2.hashCode() : 0);
        result = 31 * result + (relacijaKorisnik != null ? relacijaKorisnik.hashCode() : 0);
        return result;
    }
}
