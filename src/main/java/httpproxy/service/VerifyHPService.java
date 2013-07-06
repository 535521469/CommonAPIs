package httpproxy.service;

import httpproxy.domain.HttpProxy;
import httpproxy.domain.VerifyResult;

import java.io.IOException;

public interface VerifyHPService {

	/**
	 * 
	 * @param hp
	 * @param timeout
	 * @return
	 */
	public VerifyResult verify(HttpProxy hp, int timeout) throws IOException;

}
