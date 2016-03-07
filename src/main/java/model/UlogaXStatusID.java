package model;

/**
 * Created by Owner on 16.11.2015.
 */
public class UlogaXStatusID implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public UlogaXStatusID() {}

    public Status statusBean;
    public Uloga ulogaBean;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UlogaXStatusID)) return false;

        UlogaXStatusID that = (UlogaXStatusID) o;

        if (statusBean != null ? !statusBean.equals(that.statusBean) : that.statusBean != null) return false;
        return !(ulogaBean != null ? !ulogaBean.equals(that.ulogaBean) : that.ulogaBean != null);

    }

    @Override
    public int hashCode() {
        int result = statusBean != null ? statusBean.hashCode() : 0;
        result = 31 * result + (ulogaBean != null ? ulogaBean.hashCode() : 0);
        return result;
    }
}
