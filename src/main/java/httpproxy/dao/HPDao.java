package httpproxy.dao;

import httpproxy.domain.HttpProxy;

public interface HPDao {

	public void updateLastValidDateTime(HttpProxy hp);

	public void updatelastInvalidDateTime(HttpProxy hp);

	public HttpProxy getByHostAndPort(String host, int port);

	public void saveHttpProxy(HttpProxy hp);

}