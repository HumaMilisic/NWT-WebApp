//package com.example.models;
//
///**
// * Created by Owner on 16.11.2015.
// */
//public class UlogaXVrstaDokumentaID implements java.io.Serializable {
//    private static final long serialVersionUID = 1L;
//
//    public UlogaXVrstaDokumentaID() {}
//
//    public Uloga ulogaBean;
//    public VrstaDokumenta vrstaDokumentaBean;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof UlogaXVrstaDokumentaID)) return false;
//
//        UlogaXVrstaDokumentaID that = (UlogaXVrstaDokumentaID) o;
//
//        if (ulogaBean != null ? !ulogaBean.equals(that.ulogaBean) : that.ulogaBean != null) return false;
//        return !(vrstaDokumentaBean != null ? !vrstaDokumentaBean.equals(that.vrstaDokumentaBean) : that.vrstaDokumentaBean != null);
//
//    }
//
//    @Override
//    public int hashCode() {
//        int result = ulogaBean != null ? ulogaBean.hashCode() : 0;
//        result = 31 * result + (vrstaDokumentaBean != null ? vrstaDokumentaBean.hashCode() : 0);
//        return result;
//    }
//}
