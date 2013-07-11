package httpproxy.service;

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

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("verifyHPService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class VerifyHPServiceImp implements VerifyHPService {

	private String host;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Long verify(HttpProxy hp, int timeout, long beginTime) {

		long now = new Date().getTime();
		long end = 0L;
		BufferedReader in = null;
		try {

			// String urls =
			// "http://localhost:8080/CommonAPIs/api/v1/access_with_proxy?format=json&beginTime"
			String urls = "http://"
					+ host
					+ "/CommonAPIs/api/v1/access_with_proxy?format=json&beginTime="
					+ beginTime;
			// urls = "http://www.baidu.com";

			LoggerFactory.getLogger(this.getClass()).debug(urls);

			URL url = new URL(urls);

			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
					hp.getHost(), hp.getPort()));
			HttpURLConnection uc = (HttpURLConnection) url
					.openConnection(proxy);
			uc.setConnectTimeout(timeout);
			uc.setReadTimeout(timeout);
			uc.connect();
			String line = null;

			StringBuffer tmp = new StringBuffer();
			in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			while ((line = in.readLine()) != null) {
				tmp.append(line);
			}

			end = new Date().getTime();
			return end - now;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (null != null) {

				try {
					in.close();
				} catch (IOException e) {
				}
			}

		}

	}
}
