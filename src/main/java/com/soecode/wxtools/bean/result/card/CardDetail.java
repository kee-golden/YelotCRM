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
public class CardDetail {

    @JSONField(name = "base_info")
    private BaseInfo baseInfo;
    @JSONField(name = "least_cost")
    private int leastCost;
    @JSONField(name = "reduce_cost")
    private int reduceCost;
    @JSONField(name = "advanced_info")
    private AdvancedInfo advancedInfo;
    public void setBaseInfo(BaseInfo baseInfo) {
         this.baseInfo = baseInfo;
     }
     public BaseInfo getBaseInfo() {
         return baseInfo;
     }

    public void setLeastCost(int leastCost) {
         this.leastCost = leastCost;
     }
     public int getLeastCost() {
         return leastCost;
     }

    public void setReduceCost(int reduceCost) {
         this.reduceCost = reduceCost;
     }
     public int getReduceCost() {
         return reduceCost;
     }

    public void setAdvancedInfo(AdvancedInfo advancedInfo) {
         this.advancedInfo = advancedInfo;
     }
     public AdvancedInfo getAdvancedInfo() {
         return advancedInfo;
     }

}