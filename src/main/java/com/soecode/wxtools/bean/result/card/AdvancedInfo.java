/**
  * Copyright 2017 aTool.org 
  */
package com.soecode.wxtools.bean.result.card;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2017-09-24 21:8:33
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class AdvancedInfo {

    @JSONField(name = "time_limit")
    private List<TimeLimit> timeLimit;
    @JSONField(name = "text_image_list")
    private List<String> textImageList;
    @JSONField(name = "business_service")
    private List<String> businessService;
    @JSONField(name = "consume_share_card_list")
    private List<String> consumeShareCardList;
    @JSONField(name = "abstract")
    private AbstractInfo abstractInfo;
    @JSONField(name = "use_condition")
    private UseCondition useCondition;
    @JSONField(name = "share_friends")
    private boolean shareFriends;
    public void setTimeLimit(List<TimeLimit> timeLimit) {
         this.timeLimit = timeLimit;
     }
     public List<TimeLimit> getTimeLimit() {
         return timeLimit;
     }

    public void setTextImageList(List<String> textImageList) {
         this.textImageList = textImageList;
     }
     public List<String> getTextImageList() {
         return textImageList;
     }

    public void setBusinessService(List<String> businessService) {
         this.businessService = businessService;
     }
     public List<String> getBusinessService() {
         return businessService;
     }

    public void setConsumeShareCardList(List<String> consumeShareCardList) {
         this.consumeShareCardList = consumeShareCardList;
     }
     public List<String> getConsumeShareCardList() {
         return consumeShareCardList;
     }

    public AbstractInfo getAbstractInfo() {
        return abstractInfo;
    }

    public void setAbstractInfo(AbstractInfo abstractInfo) {
        this.abstractInfo = abstractInfo;
    }

    public boolean isShareFriends() {
        return shareFriends;
    }

    public void setUseCondition(UseCondition useCondition) {
         this.useCondition = useCondition;
     }
     public UseCondition getUseCondition() {
         return useCondition;
     }

    public void setShareFriends(boolean shareFriends) {
         this.shareFriends = shareFriends;
     }
     public boolean getShareFriends() {
         return shareFriends;
     }

}