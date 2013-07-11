package httpproxy.service;

import httpproxy.dao.HPDao;
import httpproxy.domain.HttpProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("inputHPService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class InputHPServiceImp implements InputHPService {
	@Autowired
	private HPDao hpDao;

	public HPDao getHpDao() {
		return hpDao;
	}

	@Autowired
	public void setHpDao(HPDao hpDao) {
		this.hpDao = hpDao;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void replaceHttpProxy(HttpProxy hp) {
		HttpProxy existHP = hpDao.getByHostAndPort(hp.getHost(), hp.getPort());
		if (null != existHP) {
			// exists

			if (hp.isValid()) {
				// valid
				existHP.setLastValidDateTime(hp.getLastValidDateTime());
			} else {
				// invalid
				existHP.setLastInvalidDateTime(hp.getLastInvalidDateTime());
			}

			// existHP.setLastValidDateTime(new Date());
		} else {
			// not exist

			if (hp.isValid()) {
				hpDao.saveHttpProxy(hp);
			}

		}
	}

}
