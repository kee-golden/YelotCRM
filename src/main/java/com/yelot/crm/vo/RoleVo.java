package com.yelot.crm.vo;

/**
 * left join 查询用户是否有这角色
 * Created by kee on 17/9/2.
 */
public class RoleVo {

    private Long id;
    private String name;
    private String code;
    private Long userId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
