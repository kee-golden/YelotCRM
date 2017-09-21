package com.yelot.crm;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by kee on 2015/12/8.
 */
@Configuration
@PropertySource(value = "classpath:my-app.properties")
public class AppConfig {

    @Value("${image.dataPath}")
    private String dataPath;

    private Boolean isDebug;


//一下是，短信接口配置参数
    /**
     * hpw39
     */
    @Value("${sms.account}")
    private String smsAccount;

    /**
     * a56987
     */
    @Value("${sms.password}")
    private String smsPassword;


    /**
     * http://sh2.ipyy.com/sms.aspx
     */
    @Value("${sms.url}")
    private String sendMessageUrl;

    /**
     * send
     */
    private String action;

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public Boolean getDebug() {
        return isDebug;
    }

    public void setDebug(Boolean debug) {
        isDebug = debug;
    }

    public String getSmsAccount() {
        return smsAccount;
    }

    public void setSmsAccount(String smsAccount) {
        this.smsAccount = smsAccount;
    }

    public String getSmsPassword() {
        return smsPassword;
    }

    public void setSmsPassword(String smsPassword) {
        this.smsPassword = smsPassword;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSendMessageUrl() {
        return sendMessageUrl;
    }

    public void setSendMessageUrl(String sendMessageUrl) {
        this.sendMessageUrl = sendMessageUrl;
    }
}

