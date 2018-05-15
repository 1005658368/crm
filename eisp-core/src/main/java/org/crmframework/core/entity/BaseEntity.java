package org.crmframework.core.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@SuppressWarnings("serial")
@MappedSuperclass
public class BaseEntity implements java.io.Serializable  {
    private String id;

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

    /**
     * 创建人.id
     */
    private String createUserid;
    /**
     * 创建人.登录账号
     */
    private String createUsername;
    /**
     * 创建人.姓名
     */
    private String createRealname;
    /**
     *创建人.主职位id
     */
    private String createPosid;
    /**
     * 创建人.职位名字
     */
    private String createPosname;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人.登录账号
     */
    private String updateUsername;
    /**
     * 修改人.姓名
     */
    private String updateRealname;
    /**
     * 修改人.职位id
     */
    private String updatePosid;
    /**
     * 修改人.职位名称
     */
    private String updatePosname;


    /***修改时间*/
    private Date updateTime;
    /**状态 009启用，003停用*/
    private String status;
    @Column(name = "create_userid")
    public String getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid;
    }

    @Column(name = "create_username")
    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }

    @Column(name = "create_realname")
    public String getCreateRealname() {
        return createRealname;
    }

    public void setCreateRealname(String createRealname) {
        this.createRealname = createRealname;
    }

    @Column(name = "update_username")
    public String getUpdateUsername() {
        return updateUsername;
    }

    public void setUpdateUsername(String updateUsername) {
        this.updateUsername = updateUsername;
    }

    @Column(name = "update_realname")
    public String getUpdateRealname() {
        return updateRealname;
    }

    public void setUpdateRealname(String updateRealname) {
        this.updateRealname = updateRealname;
    }

    @Column(name = "create_posname")
    public String getCreatePosname() {
        return createPosname;
    }

    public void setCreatePosname(String createPosname) {
        this.createPosname = createPosname;
    }

    @Column(name = "update_posname")
    public String getUpdatePosname() {
        return updatePosname;
    }

    public void setUpdatePosname(String updatePosname) {
        this.updatePosname = updatePosname;
    }

    @Column(name = "create_posid")
    public String getCreatePosid() {
        return createPosid;
    }

    public void setCreatePosid(String createPosid) {
        this.createPosid = createPosid;
    }

    @Column(name = "update_posid")
    public String getUpdatePosid() {
        return updatePosid;
    }

    public void setUpdatePosid(String updatePosid) {
        this.updatePosid = updatePosid;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


