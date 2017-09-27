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
public class Sku {

    private int quantity;
    @JsonProperty("total_quantity")
    private int totalQuantity;
    public void setQuantity(int quantity) {
         this.quantity = quantity;
     }
     public int getQuantity() {
         return quantity;
     }

    public void setTotalQuantity(int totalQuantity) {
         this.totalQuantity = totalQuantity;
     }
     public int getTotalQuantity() {
         return totalQuantity;
     }

}