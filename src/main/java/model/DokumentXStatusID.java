package model;

import java.sql.Timestamp;

/**
 * Created by Owner on 16.11.2015.
 */
public class DokumentXStatusID implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public DokumentXStatusID() {}

    public Timestamp izmjena;
    public Dokument dokumentBean;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DokumentXStatusID)) return false;

        DokumentXStatusID that = (DokumentXStatusID) o;

        if (izmjena != null ? !izmjena.equals(that.izmjena) : that.izmjena != null) return false;
        return !(dokumentBean != null ? !dokumentBean.equals(that.dokumentBean) : that.dokumentBean != null);

    }

    @Override
    public int hashCode() {
        int result = izmjena != null ? izmjena.hashCode() : 0;
        result = 31 * result + (dokumentBean != null ? dokumentBean.hashCode() : 0);
        return result;
    }
}
