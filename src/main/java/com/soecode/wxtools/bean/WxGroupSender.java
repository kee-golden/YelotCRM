package com.soecode.wxtools.bean;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * 根据组群发
 * @author antgan
 *
 */
public class WxGroupSender extends SenderContent{
	private SenderFilter filter;
	private String msgtype;
	public SenderFilter getFilter() {
		return filter;
	}
	public void setFilter(SenderFilter filter) {
		this.filter = filter;
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




