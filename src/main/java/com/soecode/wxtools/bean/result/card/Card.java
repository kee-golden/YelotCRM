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
public class Card {

    @JSONField(name = "card_type")
    private String cardType;

    @JSONField(name = "cash")
    private CardDetail cash;

    /**
     * 新增一个字段，不是接口数据，生成签名，附加字段
     */
    private String signature;
    private Long timestamp;
    private String noncestr;
    public void setCardType(String cardType) {
         this.cardType = cardType;
     }
     public String getCardType() {
         return cardType;
     }

    public void setCash(CardDetail cash) {
         this.cash = cash;
     }
     public CardDetail getCash() {
         return cash;
     }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }
}