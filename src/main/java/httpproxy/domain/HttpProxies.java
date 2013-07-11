package httpproxy.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "httpproxies")
public class HttpProxies implements Cloneable {

	private List<HttpProxy> httpProxies;

	public int getSize() {
		if (null != httpProxies) {
			return httpProxies.size();
		} else {
			return 0;
		}
	}

	@XmlElement(name = "httpproxy")
	public List<HttpProxy> getHttpProxies() {
		return httpProxies;
	}

	public void setHttpproxies(List<HttpProxy> httpProxies) {
		this.httpProxies = httpProxies;
	}

}
