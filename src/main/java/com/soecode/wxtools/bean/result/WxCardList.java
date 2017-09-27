package com.soecode.wxtools.bean.result;

import java.util.List;

/**
 * Created by kee on 17/9/24.
 */
public class WxCardList extends WxError {

    private List<String> card_id_list;
    private int total_num;

    public List<String> getCard_id_list() {
        return card_id_list;
    }

    public void setCard_id_list(List<String> card_id_list) {
        this.card_id_list = card_id_list;
    }

    public int getTotal_num() {
        return total_num;
    }

    public void setTotal_num(int total_num) {
        this.total_num = total_num;
    }
}
