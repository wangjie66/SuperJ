package dto; /**
 * <br>
 * Copyright 2017 IFlyTek.All rights reserved.<br>
 * <br>			 
 * Package: com.iflytek.sc.pl.core.api.dto <br>
 * FileName: Message.java <br>
 * <br>
 * @version v0.6
 * @author llchen
 * @created 2017年9月24日
 * @last Modified 
 * @history
 */


import java.io.Serializable;


public class Message implements Serializable {

	/**
	 * Comment for <code>serialVersionUID</code>,｛该处说明该变量的含义及作用｝
	 */
	private static final long serialVersionUID = 275953940086584691L;

	/**
	 * 发送系统ID，通过消息中心注册申请获得;必填
	 */
	private String senderSystem;
	/**
	 * 授权码，通过消息中心注册-审核后获得;必填
	 */
	private String authCode;
	/**
	 * 发送用户ID
	 */
	private String senderUser;
	/**
	 * 接收系统ID，通过消息中心注册申请获得;必填
	 */
	private String receiverSystem;
	/**
	 * 接收用户ID,多个用户以“，”分隔;必填
	 * <p>
	 * 若消息类型为短信，接收用户为手机号码,多个用户以“，”分隔;必填
	 * </p>
	 */
	private String receiverUser;
	/**
	 * 消息类型,多个类型以“，”分隔；私信：0 通知：1 短信：2 推送：3 邮件：4;必填
	 */
	private String msgType;
	/**
	 * 消息标题，密文传输;非短信类型时，必填
	 */
	private String title;
	/**
	 * 消息内容，密文传输;必填 当为短信类型时，不超过200字符
	 */
	private String content;

	/**
	 * 消息优先级 普通：0 优先：1 default 0
	 */
	private String priority;

	/**
	 * 发送时间，需要定时发送时使用，格式为标准时间格式：yyyy-MM-dd'T'HH:mm:ss.SSSZ
	 */
	private String sendTime;

	/**
	 * 根据配置的路由规则获取通道，无extend扩展字段是选择默认通道，存在extend
	 * 扩展字段是根据extend值选择通道（如配置的路由规则为字符串：#msg{ content constans abs }） 发送消息时扩展字段为
	 * "extend": "{ content:'absds'}" 当配置为时间规则是 拓展字段时间格式为 yyyy/MM/dd HH:mm:ss
	 */
	private String extend;

	/**
	 * 消息ID
	 */
	private String mid;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public Message() {

	}

	public Message(String content, String receiverUser, String msgType) {
		this.content = content;
		this.receiverUser = receiverUser;
		this.msgType = msgType;
	}

	public Message(String title, String content, String senderUser,
			String receiverUser, String msgType, String extend) {
		this.title = title;
		this.content = content;
		this.senderUser = senderUser;
		this.receiverUser = receiverUser;
		this.msgType = msgType;
		this.extend = extend;
	}

	public String getSenderSystem() {
		return senderSystem;
	}

	public void setSenderSystem(String senderSystem) {
		this.senderSystem = senderSystem;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getSenderUser() {
		return senderUser;
	}

	public void setSenderUser(String senderUser) {
		this.senderUser = senderUser;
	}

	public String getReceiverSystem() {
		return receiverSystem;
	}

	public void setReceiverSystem(String receiverSystem) {
		this.receiverSystem = receiverSystem;
	}

	public String getReceiverUser() {
		return receiverUser;
	}

	public void setReceiverUser(String receiverUser) {
		this.receiverUser = receiverUser;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

}
