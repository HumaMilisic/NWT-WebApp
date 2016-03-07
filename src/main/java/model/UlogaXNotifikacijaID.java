package model;

/**
 * Created by Owner on 16.11.2015.
 */
public class UlogaXNotifikacijaID implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public UlogaXNotifikacijaID() {}

    public Notifikacija notifikacijaBean;
    public Uloga ulogaBean;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UlogaXNotifikacijaID)) return false;

        UlogaXNotifikacijaID that = (UlogaXNotifikacijaID) o;

        if (notifikacijaBean != null ? !notifikacijaBean.equals(that.notifikacijaBean) : that.notifikacijaBean != null)
            return false;
        return !(ulogaBean != null ? !ulogaBean.equals(that.ulogaBean) : that.ulogaBean != null);

    }

    @Override
    public int hashCode() {
        int result = notifikacijaBean != null ? notifikacijaBean.hashCode() : 0;
        result = 31 * result + (ulogaBean != null ? ulogaBean.hashCode() : 0);
        return result;
    }
}
