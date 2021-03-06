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

    @Value("${image.isDebug}")
    private Boolean debug;


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

    @Value("${host.url}")
    private String hostUrl;

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

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public Boolean getDebug() {
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
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

