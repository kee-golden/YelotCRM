/**
  * Copyright 2017 aTool.org 
  */
package com.soecode.wxtools.bean.result.card;
import com.alibaba.fastjson.annotation.JSONField;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2017-09-24 21:8:33
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class UseCondition {

    @JSONField(name = "accept_category")
    private String acceptCategory;
    @JSONField(name = "reject_category")
    private String rejectCategory;
    @JSONField(name = "least_cost")
    private int leastCost;
    @JSONField(name = "can_use_with_other_discount")
    private boolean canUseWithOtherDiscount;
    @JSONField(name = "can_use_with_membercard")
    private boolean canUseWithMembercard;
    public void setAcceptCategory(String acceptCategory) {
         this.acceptCategory = acceptCategory;
     }
     public String getAcceptCategory() {
         return acceptCategory;
     }

    public void setRejectCategory(String rejectCategory) {
         this.rejectCategory = rejectCategory;
     }
     public String getRejectCategory() {
         return rejectCategory;
     }

    public void setLeastCost(int leastCost) {
         this.leastCost = leastCost;
     }
     public int getLeastCost() {
         return leastCost;
     }

    public void setCanUseWithOtherDiscount(boolean canUseWithOtherDiscount) {
         this.canUseWithOtherDiscount = canUseWithOtherDiscount;
     }
     public boolean getCanUseWithOtherDiscount() {
         return canUseWithOtherDiscount;
     }

    public void setCanUseWithMembercard(boolean canUseWithMembercard) {
         this.canUseWithMembercard = canUseWithMembercard;
     }
     public boolean getCanUseWithMembercard() {
         return canUseWithMembercard;
     }

}