package httpproxy.enums;

public enum HPPutResult {

	RESULT, ERROR_NO, ERROR_MSG;

	private String Code;

	public String getCode() {
		return Code;
	}

	private HPPutResult() {
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
