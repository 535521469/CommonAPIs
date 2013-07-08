package httpproxy.service;

import httpproxy.dao.HPDao;
import httpproxy.domain.HttpProxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("verifyHPService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class VerifyHPServiceImp implements VerifyHPService {

	@Autowired
	private HPDao hpDao;

	public HPDao getHpDao() {
		return hpDao;
	}

	@Autowired
	public void setHpDao(HPDao hpDao) {
		this.hpDao = hpDao;
	}

	public Long verify(HttpProxy hp, int timeout) {

		long now = new Date().getTime();
		long end = 0L;
		try {

//			URL url = new URL("http://www.baidu.com");
			 URL url = new URL("http://localhost:8080?host=" + hp.getHost()
			 + "&port=" + hp.getPort() + "&country=" + hp.getCountry());
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
					hp.getHost(), hp.getPort()));
			HttpURLConnection uc = (HttpURLConnection) url
					.openConnection(proxy);
			uc.setConnectTimeout(timeout);
			uc.connect();
			String line = null;
			StringBuffer tmp = new StringBuffer();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					uc.getInputStream()));
			while ((line = in.readLine()) != null) {
				tmp.append(line);
			}

			System.out.println(tmp.toString());

			uc.getInputStream();
			end = new Date().getTime();
			return end - now;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
}
