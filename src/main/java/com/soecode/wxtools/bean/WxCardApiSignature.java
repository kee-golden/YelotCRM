package com.soecode.wxtools.bean;

/**
 * 卡券 signature
 * 
 * @author antgan
 *
 */
public class WxCardApiSignature {
	private String appid;

	private String noncestr;

	private long timestamp;

	private String url;

	private String signature;
	private String code;
	private String card_id;
	private String openid;
	private String cardApiTicket;

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getCardApiTicket() {
		return cardApiTicket;
	}

	public void setCardApiTicket(String cardApiTicket) {
		this.cardApiTicket = cardApiTicket;
	}
}
