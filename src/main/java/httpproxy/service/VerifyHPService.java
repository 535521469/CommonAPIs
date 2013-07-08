package httpproxy.service;

import httpproxy.domain.HttpProxy;

public interface VerifyHPService {

	/**
	 * 
	 * @param hp
	 * @param timeout
	 * @return
	 */
	public Long verify(HttpProxy hp, int timeout);

}
