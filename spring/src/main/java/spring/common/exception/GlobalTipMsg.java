package spring.common.exception;


public class GlobalTipMsg {

    // ========== Global module ============
    public static final String SUCCESS = "TIP_REQ_SUCCESS";
    public static final String FAILURE = "TIP_REQ_FAILURE";
    public static final String LOGIN_AGAIN = "TIP_LOGIN_AGAIN";
    public static final String AUTHORITY_ERROR = "TIP_AUTHORITY_ERROR";
    public static final String INPUT_ERROR = "TIP_INPUT_ERROR";
    public static final String FILE_STATUS_ERROR = "TIP_FILE_STATUS_ERROR";
    public static final String FILE_SIZE_ERROR = "TIP_FILE_SIZE_ERROR";
    public static final String REPORT_NULL = "TIP_REPORT_NULL";
    public static final String REPORT_FILE_DELETE_SUCCESS = "TIP_REPORT_FILE_DELETE_SUCCESS";
    public static final String REPORT_FILE_DELETE_FAILURE = "TIP_REPORT_FILE_DELETE_FAILURE";
    public static final String AGENT_GLOBAL_ERROR = "TIP_AGENT_GLOBAL_ERROR";
    public static final String DELETE_DATA_FAILURE = "TIP_DELETE_DATA_FAILURE";
    public static final String DATA_HAS_EXIST = "TIP_DATA_HAS_EXIST";
    public static final String LOGO_SIZE_TOO_LARGE = "TIP_LOGO_SIZE_TOO_LARGE";
    public static final String LOGO_TYPE_ERROR = "TIP_LOGO_TYPE_ERROR";
    public static final String PIC_TYPE_ERROR = "TIP_PIC_TYPE_ERROR";
    public static final String FILE_IO_FAILURE = "TIP_FILE_IO_FAILURE";
    // ========== NotNull module ============
    public static final String GROUPID_NULL = "TIP_GROUP_ID_NULL";
    public static final String GROUP_NULL = "TIP_GROUP_NULL";
    // ========== app module ==============
    public static final String DELETE_APP_SUCCESS = "TIP_DEL_APP_SUCCESS";
    public static final String APP_LICENSE_EXPIRE = "TIP_APP_LICENSE_EXPIRE";
    public static final String RESET_APP_SUCCESS = "TIP_RESET_APP_SUCCESS";
    public static final String ADD_APP_ERROR = "TIP_ADD_APP_ERROR";
    public static final String RESET_APP_ERROR = "TIP_RESET_APP_ERROR";
    public static final String ADD_SERVER_APP_ERROR = "TIP_ADD_SERVER_APP_ERROR";
    public static final String APP_NOT_FOUND = "TIP_APP_NOT_FOUND";
    public static final String REPORT_APP_LACK_SERVER_NAME = "TIP_REPORT_APP_LACK_SERVER_NAME";
    public static final String RECYCLE_APP_FIRST = "TIP_RECYCLE_APP_FIRST";
    public static final String APP_VERSION_DEFAULT_DELETE_ERROR = "TIP_APP_VERSION_DEFAULT_DELETE_ERROR";
    // ========== agent & engine module ==============
    public static final String NO_FIND_TOKEN = "Request did not carry token info";
    public static final String NO_TOEKN_MATCH_USER = "According to the token did not find the user";
    // ========== email module ==============
    public static final String ADD_EMAIL_CONFIG_ERROR = "TIP_ADD_EMAIL_CONFIG_ERROR";
    public static final String UPD_EMAIL_CONFIG_ERROR = "TIP_UPD_EMAIL_CONFIG_ERROR";
    public static final String UPD_EMAIL_CONFIG_SUCCESS = "TIP_UPD_EMAIL_CONFIG_SUCCESS";
    // ========== event module ==============
    public static final String GET_EVENT_ERROR = "TIP_GET_EVENT_ERROR";
    // ========== group module ==============
    public static final String ADD_GROUP_SUCCESS = "TIP_ADD_GROUP_SUCCESS";
    public static final String DEL_GROUP_SUCCESS = "TIP_DEL_GROUP_SUCCESS";
    public static final String DEL_GROUP_ERROR = "TIP_DEL_GROUP_ERROR";
    public static final String UPD_GROUP_SUCCESS = "TIP_UPD_GROUP_SUCCESS";
    public static final String GET_GROUP_ERROR = "TIP_GET_GROUP_ERROR";
    public static final String UPD_AGENT_SUCCESS = "TIP_UPD_AGENT_SUCCESS";
    public static final String UPD_AGENT_FAILURE = "TIP_UPD_AGENT_FAILURE";
    // ========== license module ==============
    public static final String GET_LIC_ERROR = "TIP_GET_LIC_ERROR";
    public static final String LICENSE_ERROR = "TIP_LICENSE_ERROR";
    public static final String LIC_NUMBER_ERROR = "TIP_LIC_NUMBER_ERROR";
    public static final String ASSIGN_LICENSE_SUCCESS = "TIP_ASSIGN_LICENSE_SUCCESS";
    public static final String GET_MACHINE_ERROR = "TIP_GET_MACHINE_ERROR";
    public static final String UPLOAD_LICFILE_ENTRY = "TIP_UPLOAD_LICFILE_ENTRY";
    public static final String UPLOAD_LICFILE_ERROR = "TIP_UPLOAD_LICFILE_ERROR";
    public static final String UPLOAD_LICFILE_NOTNEED = "TIP_UPLOAD_LICFILE_NOTNEED";
    public static final String USE_LIC_SUCCESS = "TIP_USE_LIC_SUCCESS";
    public static final String USE_LIC_ERROR = "TIP_USE_LIC_ERROR";
    public static final String RECYCLE_LIC_SUCCESS = "TIP_RECYCLE_LIC_SUCCESS";
    public static final String UPD_LICDATE_SUCCESS = "TIP_UPD_LICDATE_SUCCESS";
    // ========== organization module ==============
    public static final String ADD_ORG_SUCCESS = "TIP_ADD_ORG_SUCCESS";
    public static final String ADD_ORG_ERROR = "TIP_ADD_ORG_ERROR";
    public static final String ADD_ORG_CUSTOM_ERROR = "TIP_ADD_ORG_CUSTOM_ERROR";
    public static final String ORG_NOT_FOUND = "TIP_ORG_NOT_FOUND";
    public static final String DEL_ORG_SUCCESS = "TIP_DEL_ORG_SUCCESS";
    public static final String DEL_ORG_ERROR = "TIP_DEL_ORG_ERROR";
    public static final String UPD_OEG_SUCCESS = "TIP_UPD_OEG_SUCCESS";
    public static final String UPD_OEG_ERROR = "TIP_UPD_OEG_ERROR";
    // ========== policy module ==============
    public static final String UPD_RULE_ASSESS_SUCCESS = "TIP_UPD_RULE_ASSESS_SUCCESS";
    public static final String UPD_RULE_SEVERITY_SUCCESS = "TIP_UPD_RULE_SEVERITY_SUCCESS";
    public static final String UPD_RULE_SEVERITY_ERROR = "TIP_UPD_RULE_SEVERITY_ERROR";
    public static final String RESET_RULE_SEVERITY_SUCCESS = "TIP_RESET_RULE_SEVERITY_SUCCESS";
    public static final String NO_CUSTOM_RULE = "TIP_NO_CUSTOM_RULE";
    public static final String DEL_CUSTOM_RULE_SUCCESS = "TIP_DEL_CUSTOM_RULE_SUCCESS";
    public static final String UPD_CUSTOM_RULE_SUCCESS = "TIP_UPD_CUSTOM_RULE_SUCCESS";
    public static final String ADD_CUSTOM_RULE_SUCCESS = "TIP_ADD_CUSTOM_RULE_SUCCESS";
    public static final String DEL_VWL_RULE_SUCCESS = "TIP_DEL_VWL_RULE_SUCCESS";
    public static final String UPD_VWL_RULE_SUCCESS = "TIP_UPD_VWL_RULE_SUCCESS";
    public static final String ADD_VWL_RULE_SUCCESS = "TIP_ADD_VWL_RULE_SUCCESS";
    // ========== proxy module ==============
    public static final String ADD_PROXY_SUCCESS = "TIP_ADD_PROXY_SUCCESS";
    public static final String ADD_PROXY_ERROR = "TIP_ADD_PROXY_ERROR";
    public static final String UPD_PROXY_SUCCESS = "TIP_UPD_PROXY_SUCCESS";
    public static final String UPD_PROXY_ERROR = "TIP_UPD_PROXY_ERROR";
    public static final String VALID_PROXY_EXIST = "TIP_VALID_PROXY_EXIST";
    public static final String VALID_PROXY_NOEXIST = "TIP_VALID_PROXY_NOEXIST";
    public static final String DEL_PROXY_SUCCESS = "TIP_DEL_PROXY_SUCCESS";
    public static final String DEL_PROXY_ERROR = "TIP_DEL_PROXY_ERROR";
    // ========== server module ==============
    public static final String ADD_SERVER_ERROR = "TIP_ADD_SERVER_ERROR";
    public static final String DEL_SERVER_SUCCESS = "TIP_DEL_SERVER_SUCCESS";
    public static final String DEL_SERVER_ERROR = "TIP_DEL_SERVER_ERROR";
    public static final String FIND_SERVER_MULTIPLE = "TIP_FIND_SERVER_MULTIPLE";
    // ========== rule module ==============
    public static final String NO_FIND_RULE = "No rule info found";
    // ========== validation module ==============
    public static final String FILED_VALUE_ERROR = "TIP_FILED_VALUE_ERROR";
    // ========== user module ==============
    public static final String EMAIL_USED = "TIP_EMAIL_USED";
    public static final String ADD_USER_SUCCESS = "TIP_ADD_USER_SUCCESS";
    public static final String VERIFY_CODE_ERROR = "TIP_VERIFY_CODE_ERROR";
    public static final String UPD_UESR_SUCCESS = "TIP_UPD_UESR_SUCCESS";
    public static final String UPD_UESR_ERROR = "TIP_UPD_UESR_ERROR";
    public static final String DEL_UESR_SUCCESS = "TIP_DEL_USER_SUCCESS";
    public static final String DEL_UESR_ERROR = "TIP_DEL_USER_ERROR";
    public static final String TIP_LOGIN_FAIL = "TIP_LOGIN_FAIL";
    public static final String TIP_USER_NO_ALIVE = "TIP_USER_NO_ALIVE";
    public static final String TIP_USER_EXIST_SUCCESS = "TIP_USER_EXIST_SUCCESS";
    public static final String ACTIVATE_USED = "TIP_ACTIVATW_USED";
    public static final String CAPTCHA_ERROR = "TIP_CAPTCHA_ERROR";
    public static final String SEND_EMAIL_SUCCESS = "TIP_SEND_EMAIL_SUCCESS";
    public static final String UPD_USER_SUCCESS = "TIP_UPD_USER_SUCCESS";
    public static final String USER_NOT_FOUND = "input error user not found";
    public static final String USER_STATUS_EXCEPTION = "user status exception";
    public static final String USER_TEAM_STATUS_LOCKED = "User working team locked";
    public static final String EMAIL_CHECK_FAILURE = "Can't check the email";
    // ========== vulnerability module ==============
    public static final String APP_NOT_EQUAL_CONTEXTPATH = "APP_NOT_EQUAL_CONTEXTPATH";
    public static final String AUTH_TOKEN_ERROR = "AUTH_TOKEN_ERROR";
    public static final String ADD_COMMENT_SUCCESS = "TIP_ADD_COMMENT_SUCCESS";
    public static final String DEL_COMMENT_SUCCESS = "TIP_DEL_COMMENT_SUCCESS";
    public static final String UPD_VUL_SUCCESS = "TIP_UPD_VUL_SUCCESS";
    public static final String UPD_VUL_STATUS_SUCCESS = "TIP_UPD_VUL_STATUS_SUCCESS";
    public static final String NO_VUL_INFO = "TIP_NO_VUL_INFO";
    public static final String NO_MATCH_RULE = "TIP_NO_MATCH_RULE";
    public static final String NO_MATCH_HTTPHEADER = "TIP_NO_MATCH_HTTPHEADER";
    public static final String DEL_VUL_SUCCESS = "TIP_DEL_VUL_SUCCESS";
    public static final String HARDCODE_ERROR = "TIP_HARD_ERROR";
    public static final String UPD_VUL_HOW_TO_FIX_SUCCESS = "TIP_UPD_VUL_HOW_TO_FIX_SUCCESS";
    public static final String UPD_VUL_ERROR = "Update vulnerability error !";
    // ========== third lib module ==============
    public static final String DEL_LIB_SUCCESS = "TIP_DEL_LIB_SUCCESS";
    public static final String APP_CVE_NOT_FOUND = "TIP_APP_CVE_NOT_FOUND";
    // ========== system config module ==============
    public static final String UPD_SYSTEM_SUCCESS = "TIP_UPD_LIB_SUCCESS";
    //portal title parameter
    public static final String HEADER = "TIP_HEADER";
    public static final String HEADERPLUS = "TIP_HEADER_PLUS";
    public static final String HEADERNAMESPLUS = "TIP_HEADER_NAMES_PLUS";
    public static final String HEADERSPLUS = "TIP_HEADERS_PLUS";
    public static final String QUERYPLUS = "TIP_QUERY_PLUS";
    public static final String SESSIONID = "TIP_SESSION_ID";
    public static final String PARAMETER = "TIP_PARAMETER";
    public static final String PARAMETERSPLUS = "TIP_PARAMETERS_PLUS";
    public static final String PARAMETERPLUS = "TIP_PARAMETER_PLUS";
    public static final String PARAMETERNAME = "TIP_PARAMETER_NAME";
    public static final String PARAMETERVALUE = "TIP_PARAMETER_VALUE";
    public static final String REQUESTURI = "TIP_URI";
    public static final String REQUESTURL = "TIP_URL";
    public static final String HTTPINPUTSTREAM = "TIP_HTTP_INPUT_STREAM";
    public static final String HTTPREQUESTBODY = "TIP_HTTP_REQUEST_BODY";
    public static final String HTTPREQUESTCOOKIESPLUS = "TIP_HTTP_REQUEST_COOKIES_PLUS";
    public static final String MULTIPARTDATASTREAM = "TIP_MULTIPART_DATA_STREAM";
    public static final String MULTIPARTCONTENTTYPE = "TIP_MULTIPART_CONTENT_TYPE";
    public static final String MULTIPARTFIELD = "TIP_MULTIPART_FIELD";
    public static final String MULTIPARTFIELDNAME = "TIP_MULTIPART_FIELD_NAME";
    public static final String MULTIPARTDATA = "TIP_MULTIPART_DATA";
    public static final String MULTIPARTDATAPLUS = "TIP_MULTIPART_DATA_PLUS";
    public static final String AXISWEBSERVICEINPUT = "TIP_AXIS_WEB_SERVICE_INPUT";
    public static final String FILEUPLOADFILE = "TIP_FILE_UPLOAD_FILE";
    public static final String BODYCONTENT = "TIP_BODY_CONTENT";
    public static final String FILEUPLOADTRANSFERENCODING = "TIP_FILE_UPLOAD_TRANSFER_ENCODING";
    public static final String FILEUPLOADCONTENTTYPE = "TIP_FILE_UPLOAD_CONTENT_TYPE";
    public static final String FILEUPLOADFILENAME = "TIP_FILE_UPLOAD_FILENAME";
    public static final String PATHVARIABLE = "TIP_PATHVARIABLE";
    // ========== jira intergation module ==============
    public static final String INVALID_SERVERURI = "TIP_INVALID_SERVERURI";
    public static final String INVALID_CREDENTIALS = "TIP_INVALID_CREDENTIALS";
    public static final String JIRA_CAPTCHA = "TIP_CAPTCHA";
    public static final String JIRA_CONFIG = "JIRA_CONFIG";
    // ========== acl intergation module ==============
    public static final String ACL_COMMUNITY = "TIP_ACL_COMMUNITY";
    public static final String VUL_BUG_TRACK_CONFIG_EXCEPTION = "TIP_VUL_BUG_TRACK_CONFIG_EXCEPTION";


    //jira相关
    public static final String JIRA_PUSH_SUCCESS= "推送成功";
    public static final String JIRA_PUSH_FAILER= "推送失败";
    public static final String JIRA_DELETE_FAILER= "删除失败";
    public static final String JIRA_DELETE_SUCCESS= "删除成功";
}