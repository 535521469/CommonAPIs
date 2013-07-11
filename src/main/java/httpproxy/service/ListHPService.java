package httpproxy.service;

import httpproxy.domain.HttpProxiesViewDomain;

public interface ListHPService {

	public HttpProxiesViewDomain listHttpProxyViewDomains(int pageNo,
			int pageSize);

}
