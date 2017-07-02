package com.yelot.crm.vo;

/**
 * @author kee
 *
 *         dataTables ui 封装
 */
public class Table {


    /*总记录数*/
    private Integer recordsTotal;

    /*过滤记录数*/
    private Integer recordsFiltered;

    private Object data;

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Table() {
    }

    public Table(Integer recordsTotal, Integer recordsFiltered, Object data) {
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }
}
