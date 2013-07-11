package httpproxy.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HttpProxyViewDomain {
	private String host;
	private int port;
	private String country;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void copyFromHttpProxy(HttpProxy hp) {
		this.setCountry(hp.getCountry());
		this.setHost(hp.getHost());
		this.setPort(hp.getPort());
	}

	public static HttpProxyViewDomain generateFromHttpProxy(HttpProxy hp) {
		HttpProxyViewDomain httpProxyViewDomain = new HttpProxyViewDomain();
		httpProxyViewDomain.copyFromHttpProxy(hp);
		return httpProxyViewDomain;
	}

}
