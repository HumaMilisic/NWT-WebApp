package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ULOGA_X_NOTIFIKACIJA database table.
 * 
 */
@Entity @IdClass(UlogaXNotifikacijaID.class)
@Table(name="ULOGA_X_NOTIFIKACIJA")
@NamedQuery(name="UlogaXNotifikacija.findAll", query="SELECT u FROM UlogaXNotifikacija u")
public class UlogaXNotifikacija implements Serializable {
	private static final long serialVersionUID = 1L;

	private String aplikacija;

	private String email;

	//bi-directional many-to-one association to Notifikacija
	@Id
	@ManyToOne
	@JoinColumn(name="NOTIFIKACIJA")
	private Notifikacija notifikacijaBean;

	//bi-directional many-to-one association to Uloga
	@Id
	@ManyToOne
	@JoinColumn(name="ULOGA")
	private Uloga ulogaBean;

	public UlogaXNotifikacija() {
	}

	public String getAplikacija() {
		return this.aplikacija;
	}

	public void setAplikacija(String aplikacija) {
		this.aplikacija = aplikacija;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Notifikacija getNotifikacijaBean() {
		return this.notifikacijaBean;
	}

	public void setNotifikacijaBean(Notifikacija notifikacijaBean) {
		this.notifikacijaBean = notifikacijaBean;
	}

	public Uloga getUlogaBean() {
		return this.ulogaBean;
	}

	public void setUlogaBean(Uloga ulogaBean) {
		this.ulogaBean = ulogaBean;
	}
	
	//dodao
		public UlogaXNotifikacijaID getId() {
			UlogaXNotifikacijaID id = new UlogaXNotifikacijaID();
			id.ulogaBean = this.ulogaBean;
			id.notifikacijaBean = this.notifikacijaBean;
			return id;
		}
		
		public void setId(UlogaXNotifikacijaID id) {
			this.ulogaBean = id.ulogaBean;
			this.notifikacijaBean = id.notifikacijaBean;
		}

}

