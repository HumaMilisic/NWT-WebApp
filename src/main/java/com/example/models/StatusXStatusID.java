package com.example.models;

/**
 * Created by Owner on 16.11.2015.
 */
public class StatusXStatusID implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public StatusXStatusID() {}

    public Status status1;
    public Status status2;
    public Dogadjaj dogadjaj;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KorisnikXKorisnikID)) return false;

        StatusXStatusID that = (StatusXStatusID) o;

        if (status1 != null ? !status1.equals(that.status1) : that.status1 != null) return false;
        if (status2 != null ? !status2.equals(that.status2) : that.status2 != null) return false;
        return !(dogadjaj != null ? !dogadjaj.equals(that.dogadjaj) : that.dogadjaj != null);

    }

    @Override
    public int hashCode() {
        int result = status1 != null ? status1.hashCode() : 0;
        result = 31 * result + (status2 != null ? status2.hashCode() : 0);
        result = 31 * result + (dogadjaj != null ? dogadjaj.hashCode() : 0);
        return result;
    }
}
