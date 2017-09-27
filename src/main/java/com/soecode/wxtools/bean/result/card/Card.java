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
    private CardDetail cash;
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

}