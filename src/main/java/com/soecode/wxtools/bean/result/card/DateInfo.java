/**
  * Copyright 2017 aTool.org 
  */
package com.soecode.wxtools.bean.result.card;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2017-09-24 21:8:33
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class DateInfo {

    private String type;
    @JsonProperty("begin_timestamp")
    private int beginTimestamp;
    @JsonProperty("end_timestamp")
    private int endTimestamp;
    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setBeginTimestamp(int beginTimestamp) {
         this.beginTimestamp = beginTimestamp;
     }
     public int getBeginTimestamp() {
         return beginTimestamp;
     }

    public void setEndTimestamp(int endTimestamp) {
         this.endTimestamp = endTimestamp;
     }
     public int getEndTimestamp() {
         return endTimestamp;
     }

}