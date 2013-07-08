package httpproxy.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Proxy_HttpInfo")
public class HttpProxy implements Cloneable {

	@Id
	@GeneratedValue(generator = "uuidgenerator")
	@GenericGenerator(name = "uuidgenerator", strategy = "uuid")
	private String seqID;

	@Column(name = "Host", nullable = false)
	private String host;

	@Column(name = "Port", nullable = false)
	private int port;

	@Column(name = "Country", nullable = true)
	private String country;

	@Column(name = "LastValidDateTime", nullable = false)
	private Date lastValidDateTime;

	@Column(name = "LastInvalidDateTime", nullable = false)
	private Date lastInvalidDateTime;

	public String getSeqID() {
		return seqID;
	}

	public void setSeqID(String seqID) {
		this.seqID = seqID;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getLastValidDateTime() {
		return lastValidDateTime;
	}

	public void setLastValidDateTime(Date lastValidDateTime) {
		this.lastValidDateTime = lastValidDateTime;
	}

	public Date getLastInvalidDateTime() {
		return lastInvalidDateTime;
	}

	public void setLastInvalidDateTime(Date lastInvalidDateTime) {
		this.lastInvalidDateTime = lastInvalidDateTime;
	}

}
