package httpproxy.service;

import httpproxy.dao.HPDao;
import httpproxy.domain.HttpProxiesViewDomain;
import httpproxy.domain.HttpProxy;
import httpproxy.domain.HttpProxyViewDomain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("listHPService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ListHPServiceImp implements ListHPService {

	@Autowired
	private HPDao hpDao;

	public HPDao getHpDao() {
		return hpDao;
	}

	@Autowired
	public void setHpDao(HPDao hpDao) {
		this.hpDao = hpDao;
	}

	/**
	 * first page is 1
	 */
	public HttpProxiesViewDomain listHttpProxyViewDomains(int pageNo,
			int pageSize) {

		List<HttpProxy> httpProxies = hpDao
				.listValidHttpProxy(pageNo, pageSize);
		HttpProxiesViewDomain httpProxiesViewDomain = null;
		if (httpProxies != null) {
			httpProxiesViewDomain = HttpProxiesViewDomain
					.generateFromHttpProxiesList(httpProxies);
		} else {
			httpProxiesViewDomain = new HttpProxiesViewDomain();
			httpProxiesViewDomain
					.setHttpProxyViewDomains(new ArrayList<HttpProxyViewDomain>());
		}

		return httpProxiesViewDomain;
	}
}
