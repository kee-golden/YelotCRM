/**
  * Copyright 2017 aTool.org 
  */
package com.soecode.wxtools.bean.result.card;

import com.soecode.wxtools.bean.result.WxError;

/**
 * Auto-generated: 2017-09-24 21:8:33
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class WxCardResult extends WxError{

    private int errcode;
    private String errmsg;
    private Card card;
    public void setErrcode(int errcode) {
         this.errcode = errcode;
     }
     public int getErrcode() {
         return errcode;
     }

    public void setErrmsg(String errmsg) {
         this.errmsg = errmsg;
     }
     public String getErrmsg() {
         return errmsg;
     }

    public void setCard(Card card) {
         this.card = card;
     }
     public Card getCard() {
         return card;
     }

}