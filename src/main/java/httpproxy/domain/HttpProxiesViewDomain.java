package httpproxy.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HttpProxiesViewDomain {

	private List<HttpProxyViewDomain> httpProxyViewDomains;

	public int getSize() {
		if (null != httpProxyViewDomains) {
			return httpProxyViewDomains.size();
		} else {
			return 0;
		}
	}

	public List<HttpProxyViewDomain> getHttpProxyViewDomains() {
		return httpProxyViewDomains;
	}

	public void setHttpProxyViewDomains(
			List<HttpProxyViewDomain> httpProxyViewDomains) {
		this.httpProxyViewDomains = httpProxyViewDomains;
	}

	public void convertFromHttpProxiesList(List<HttpProxy> httpProxies) {

		if (this.getHttpProxyViewDomains() == null) {
			this.httpProxyViewDomains = new ArrayList<HttpProxyViewDomain>();
		}
		if (httpProxies != null) {
			for (HttpProxy httpProxy : httpProxies) {
				this.httpProxyViewDomains.add(HttpProxyViewDomain
						.generateFromHttpProxy(httpProxy));
			}
		}
	}

	public static HttpProxiesViewDomain generateFromHttpProxiesList(
			List<HttpProxy> httpProxies) {
		HttpProxiesViewDomain httpProxiesViewDomain = new HttpProxiesViewDomain();
		httpProxiesViewDomain.convertFromHttpProxiesList(httpProxies);
		return httpProxiesViewDomain;
	}

}
