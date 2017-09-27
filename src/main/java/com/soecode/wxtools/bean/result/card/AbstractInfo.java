/**
  * Copyright 2017 aTool.org 
  */
package com.soecode.wxtools.bean.result.card;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2017-09-24 21:8:33
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class AbstractInfo {

    private String abstractInfo;

    @JSONField(name = "icon_url_list")
    private List<String> iconUrlList;

    public void setAbstract(String abstractInfo) {
         this.abstractInfo = abstractInfo;
     }
     public String getAbstract() {
         return abstractInfo;
     }

    public void setIconUrlList(List<String> iconUrlList) {
         this.iconUrlList = iconUrlList;
     }
     public List<String> getIconUrlList() {
         return iconUrlList;
     }

}