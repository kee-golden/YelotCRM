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
public class TimeLimit {

    private String type;
    @JsonProperty("begin_hour")
    private int beginHour;
    @JsonProperty("begin_minute")
    private int beginMinute;
    @JsonProperty("end_hour")
    private int endHour;
    @JsonProperty("end_minute")
    private int endMinute;
    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setBeginHour(int beginHour) {
         this.beginHour = beginHour;
     }
     public int getBeginHour() {
         return beginHour;
     }

    public void setBeginMinute(int beginMinute) {
         this.beginMinute = beginMinute;
     }
     public int getBeginMinute() {
         return beginMinute;
     }

    public void setEndHour(int endHour) {
         this.endHour = endHour;
     }
     public int getEndHour() {
         return endHour;
     }

    public void setEndMinute(int endMinute) {
         this.endMinute = endMinute;
     }
     public int getEndMinute() {
         return endMinute;
     }

}