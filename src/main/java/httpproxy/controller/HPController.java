package httpproxy.controller;

import httpproxy.domain.HttpProxy;
import httpproxy.enums.HPPutResult;
import httpproxy.enums.HPVerifyResult;
import httpproxy.service.InputHPService;
import httpproxy.service.VerifyHPService;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;

@Controller("hpController")
public class HPController {

	@Autowired
	private InputHPService inputHPService;
	@Autowired
	private VerifyHPService verifyHPService;
	private Map<String, String> result;
	private Long requestTime;

	private Integer timeout;

	public int getTimeout() {
		if (null == timeout) {
			timeout = 5;
		}
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public Long getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Long requestTime) {
		this.requestTime = requestTime;
	}

	public Map<String, String> getResult() {
		return result;
	}

	@JSON
	public void setResult(Map<String, String> result) {
		this.result = result;
	}

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

	private Integer port;
	private String host;
	private String country;

	@JSON
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String replaceHttpProxy() {

		HttpProxy hp = new HttpProxy();
		hp.setHost(host);
		hp.setPort(port);
		hp.setCountry(country);

		DateTime now = DateTime.now();
		hp.setLastValidDateTime(now.toDate());
		hp.setLastInvalidDateTime(now.minusSeconds(1).toDate());

		this.inputHPService.replaceHttpProxy(hp);

		this.result = new HashMap<String, String>();
		this.result.put(HPPutResult.RESULT.getCode(),
				HPPutResult.Result.YES.getCode());

		return Action.SUCCESS;
	}

	public String verifyHttpProxy() {
		HttpProxy hp = new HttpProxy();
		hp.setHost(host);
		hp.setPort(port);
		hp.setCountry(country);

		Long timeSpend = verifyHPService.verify(hp, timeout);

		this.result = new HashMap<String, String>();
		if (null == timeSpend) {
			this.result.put(HPVerifyResult.RESULT.getCode(),
					HPVerifyResult.Result.NO.getCode());
		} else {
			this.result.put(HPVerifyResult.RESULT.getCode(),
					HPVerifyResult.Result.YES.getCode());
			this.result.put(HPVerifyResult.TIME_SPEND.getCode(),
					timeSpend.toString());
		}

		return Action.SUCCESS;

	}

}
