package spring.common.response;

public class ResponseMsg {
    // Global
    public static final String SUCCESS = "OK";
    public static final String FAILURE = "Error";

    // portal - frontend
    public static final String LOGIN_AGAIN = "用户会话过期，请重新登录";
    public static final String AUTHORITY_ERROR = "权限访问异常";
    public static final String INPUT_ERROR = "输入值不匹配要求的规则";
    public static final String FILE_IO_FAILURE = "文件读写失败";
    public static final String GROUP_NULL = "小组不能为空";
    public static final String APP_NOT_FOUND = "未找到指定应用";
    public static final String GET_LIC_ERROR = "获取license失败或license不可用";
    public static final String GET_MACHINE_ERROR = "license机器码异常";
    public static final String UPLOAD_LICFILE_ENTRY = "上传的license文件为空";
    public static final String UPLOAD_LICFILE_ERROR = "上传的license文件不符合规则";
    public static final String UPLOAD_LICFILE_NOTNEED = "license文件状态正常，无需上传新的文件";


    // portal - agent
    public static final String NO_FIND_TOKEN = "The request does not carry Token information.";
}
