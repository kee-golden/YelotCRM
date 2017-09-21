package com.yelot.crm.service;


import com.yelot.crm.AppConfig;
import com.yelot.crm.Util.HttpUtils;
import com.yelot.crm.Util.NumberUtils;
import com.yelot.crm.entity.SendMessage;
import com.yelot.crm.enums.VerifyCodeType;
import com.yelot.crm.mapper.SendMessageMapper;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kee on 16/12/16.
 */
@Service
public class SendMessageService {

    @Autowired
    private SendMessageMapper sendMessageMapper;

    @Autowired
    private AppConfig appConfig;

    public boolean sendVerifyCode(String phone){
//
//        AppConfig appConfig = new AppConfig();
//        appConfig.setSmsAccount("jkwl210");
//        appConfig.setSmsPassword("362189");
//        appConfig.setSendMessageUrl("http://sh2.ipyy.com/sms.aspx");
        //jkwl210    362189

        String randomVerifyCode = NumberUtils.generateRandom(4);
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("userid", appConfig.getSmsAccount()));
        nameValuePairs.add(new BasicNameValuePair("account",appConfig.getSmsAccount()));
        nameValuePairs.add(new BasicNameValuePair("password",appConfig.getSmsPassword()));
        nameValuePairs.add(new BasicNameValuePair("mobile",phone));
        //【云慧ITSS】 验证码是：@，2分钟内有效，如非本人操作，请忽略本消息。
        String content = "【云惠软件ITSM】 验证码是："+randomVerifyCode + "，3分钟内有效，如非本人操作，请忽略本消息。";
        nameValuePairs.add(new BasicNameValuePair("content",content));
        nameValuePairs.add(new BasicNameValuePair("action","send"));
        nameValuePairs.add(new BasicNameValuePair("sendTime",""));//立即发送
        nameValuePairs.add(new BasicNameValuePair("extno",""));

        String result = HttpUtils.post(appConfig.getSendMessageUrl(),nameValuePairs);
        if(result.contains("<returnstatus>Success</returnstatus>")){//接口调用成功
            SendMessage sendMessage = sendMessageMapper.findByPhone(phone);
            SendMessage sm = new SendMessage();
            sm.setPhone(phone);
            sm.setVerifyCode(randomVerifyCode);

            if(sendMessage == null){
//                sm.setId(IdWorker.nextId());

                sm.setSendTm(new Date());
                sm.setSendTimes(1);
                sm.setDayTimes(1);

                sendMessageMapper.insert(sm);
            }else{
                sendMessageMapper.update(sm);
            }
            return true;
        }
        return false;

    }

    /**
     * 验证码是否正确，3分钟有效，
     * 返回 3 表成功
     * @param phone
     * @param verifyCode
     * @return
     */
    public Integer checkVerifyCode(String phone,String verifyCode){

        SendMessage sendMessage = sendMessageMapper.findByPhone(phone);
        if(sendMessage == null){
            return VerifyCodeType.Verify_Error.getCode();
        }
        Date sendTm = sendMessage.getSendTm();
        long currentTimes = System.currentTimeMillis();

        long distance = (currentTimes - sendTm.getTime())/(1000);
        if(distance > 60 * 3){//超出3分钟，就为无效。超时。
            return VerifyCodeType.Verify_Delay.getCode();
        }

        Integer checkCount = sendMessageMapper.checkVerifyCode(phone, verifyCode);
        if(checkCount < 1){//验证码错误
            return VerifyCodeType.Verify_Error.getCode();
        }

        return VerifyCodeType.Verify_Success.getCode();
    }

    public static void main(String[] args) {
        SendMessageService sendMessageService = new SendMessageService();
        sendMessageService.sendVerifyCode("15358000878");
    }
}
