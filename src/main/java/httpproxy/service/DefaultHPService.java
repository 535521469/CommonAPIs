package httpproxy.service;

import httpproxy.domain.HttpProxy;
import httpproxy.domain.VerifyResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class DefaultHPService implements InputHPService,
		VerifyHPService {

	public void replaceHttpProxy(HttpProxy hp) {

	}

	public VerifyResult verify(HttpProxy hp, int timeout) throws IOException {
		URL url = new URL("http://localhost:8080?host=" + hp.getHost()
				+ "&port=" + hp.getPort() + "&country=" + hp.getCountry());
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
				hp.getHost(), hp.getPort()));
		HttpURLConnection uc = (HttpURLConnection) url.openConnection(proxy);
		uc.setConnectTimeout(timeout);
		uc.connect();
		String line = null;
		StringBuffer tmp = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				uc.getInputStream()));
		while ((line = in.readLine()) != null) {
			tmp.append(line);
		}

		Document doc = Jsoup.parse(String.valueOf(tmp));

		return null;

	}
}
