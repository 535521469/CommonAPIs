package httpproxy.domain;

public class VerifyResult {

	private HttpProxy hp;
	private Long duringTime;
	private Long beginTime;
	private Long endTime;

	public HttpProxy getHp() {
		return hp;
	}

	public void setHp(HttpProxy hp) {
		this.hp = hp;
	}

	public long getDuringTime() {
		return duringTime;
	}

	public void setDuringTime(long duringTime) {
		this.duringTime = duringTime;
	}

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

}
