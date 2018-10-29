package com.crm.crm.base.mdm.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
@MappedSuperclass
public class BaseEntity implements Serializable {
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

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 36)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    @Column(name = "CREATE_USER_ID")
    @Transient
    public String getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid;
    }

//    @Column(name = "create_Time")
    @Transient
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

//    @Column(name = "update_User_id")
    @Transient
    public String getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(String updateUserid) {
        this.updateUserid = updateUserid;
    }

//    @Column(name = "update_Time")
    @Transient
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}


