package spring.common.response;


public enum ResponseCode {
	// 返回状态正常的状态码
	ERROR(0, "ERROR"),
	SUCCESS(1, "SUCCESS"),
	VALID_TIP(2, "MESSAGE TIP"),
	NEED_LOGIN(10, "NEED_LOGIN"),
	ILLEGAL_ARGUMENT(11, "ILLEGAL_ARGUMENT"),//授权问题
	LICESE_ERROR(30, "LICENSE_ERROR"),
	AGENT_ERROR(40, "AGENT_DETECT_DISABLE"),
	AGENT_GLOBAL_ERROR(50, "AGENT_GLOBAL_DISABLE");

	private final int code;
	private final String description;

	ResponseCode(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
