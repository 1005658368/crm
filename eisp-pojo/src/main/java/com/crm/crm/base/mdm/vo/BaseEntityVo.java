package com.crm.crm.base.mdm.vo;

import java.io.Serializable;
import java.util.Date;

public class BaseEntityVo implements Serializable {
    private String id;
    /**
     * 创建人.id
     */
    private String createUserid;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人.id
     */
    private String updateUserid;
    /**
     * 更新时间
     */
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(String updateUserid) {
        this.updateUserid = updateUserid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}


