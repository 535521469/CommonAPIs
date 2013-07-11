package httpproxy.controller;

import httpproxy.domain.HttpProxiesViewDomain;
import httpproxy.domain.HttpProxy;
import httpproxy.service.InputHPService;
import httpproxy.service.ListHPService;
import httpproxy.service.VerifyHPService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("hpController")
public class HPController {

	@Autowired
	private VerifyHPService verifyHPService;
	@Autowired
	private InputHPService inputHPService;
	@Autowired
	private ListHPService listHPService;

	@RequestMapping(method = RequestMethod.GET, value = "/v1/access_with_proxy")
	public ModelAndView accessWithProxy(HttpServletRequest request,
			@RequestParam String beginTime) {

		Map<String, String> ms = new HashMap<String, String>();
		ms.put("beginTime", beginTime);
		ms.put("result", "success");
		return new ModelAndView("httpproxy_v1", ms);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/v1/proxies")
	public ModelAndView listValidHttpProxies(HttpServletRequest request,
			@RequestParam String pageNo, @RequestParam String pageSize) {
		if (Integer.valueOf(pageSize) > 500) {
			pageSize = "500";
		}
		HttpProxiesViewDomain httpProxiesViewDomain = this.listHPService
				.listHttpProxyViewDomains(Integer.valueOf(pageNo),
						Integer.valueOf(pageSize));
		return new ModelAndView("httpproxy_v1", "httpProxiesViewDomain",
				httpProxiesViewDomain);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/v1/httpproxy/")
	public ModelAndView getHttpProxies(HttpServletRequest request,
			@RequestParam String port, @RequestParam String host,
			@RequestParam String timeout) {

		Map<String, String> ms = new HashMap<String, String>();
		// ms.put("method", id);

		HttpProxy hp = new HttpProxy();
		hp.setPort(Integer.valueOf(port));
		hp.setHost(host);

		Long timeUsage = this.verifyHPService.verify(hp,
				Integer.valueOf(timeout), new Date().getTime());

		DateTime now = DateTime.now();

		if (timeUsage == null) {
			ms.put("result", "false");
			hp.setLastInvalidDateTime(now.toDate());
			hp.setLastValidDateTime(now.minusSeconds(1).toDate());
		} else {
			ms.put("result", "true");
			ms.put("time_usage", timeUsage.toString());
			hp.setLastValidDateTime(now.toDate());
			hp.setLastInvalidDateTime(now.minusSeconds(1).toDate());
		}
		this.inputHPService.replaceHttpProxy(hp);
		return new ModelAndView("httpproxy_v1", ms);
	}
}
