package com.soecode.wxtools.bean;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * 预览-群发
 * @author antgan
 *
 */
public class PreviewSender extends SenderContent{

	private String touser;
	private String towxname;
	private String msgtype;
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTowxname() {
		return towxname;
	}
	public void setTowxname(String towxname) {
		this.towxname = towxname;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String toJson() throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}
	
}
