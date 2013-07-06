package httpproxy.controller;

import httpproxy.domain.HttpProxy;
import httpproxy.service.InputHPService;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;

@Controller("hpController")
public class HPController {

	@Autowired
	private InputHPService inputHPService;

	public InputHPService getInputHPService() {
		return inputHPService;
	}

	@Autowired
	public void setInputHPService(InputHPService inputHPService) {
		this.inputHPService = inputHPService;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	private String result;
	private long useTime;

	private int port;
	private String host;
	private String country;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public long getUseTime() {
		return useTime;
	}

	public void setUseTime(long useTime) {
		this.useTime = useTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String execute() {

		HttpProxy hp = new HttpProxy();
		hp.setHost(host);
		hp.setPort(port);
		hp.setCountry(country);

		DateTime now = DateTime.now();
		hp.setLastValidDateTime(now.toDate());
		hp.setLastInvalidDateTime(now.minusSeconds(1).toDate());

		this.inputHPService.replaceHttpProxy(hp);
		return Action.SUCCESS;
	}

}
