package httpproxy.enums;

public enum HPVerifyResult {

	RESULT, ERROR_NO, ERROR_MSG, TIME_SPEND;

	private String Code;

	public String getCode() {
		return Code;
	}

	private HPVerifyResult() {
		this.Code = this.name();
	}

	public enum Result {

		YES, NO;
		private String Code;

		public String getCode() {
			return Code;
		}

		private Result() {
			this.Code = this.name();
		}

	}
}
