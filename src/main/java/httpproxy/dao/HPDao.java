package httpproxy.dao;

import java.util.List;

import httpproxy.domain.HttpProxy;

public interface HPDao {

	public HttpProxy getByHostAndPort(String host, int port);

	public void saveHttpProxy(HttpProxy hp);

	public List<HttpProxy> listValidHttpProxy(int pageNo, int pageSize);

}