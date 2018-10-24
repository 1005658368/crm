package com.crm.crm.pojo.entity;

import javax.persistence.*;

/**
 * 系统用户父类表
 * 
 * @author Biz
 */
@Entity
@Table(name = "t_s_base_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class TSBaseUser extends BaseEntity{
    private static final long serialVersionUID = 1L;
    private String userName;// 用户名
    private String passWord;// 用户密码
    private String realName;// 真实姓名

    @Column(name = "username", nullable = true, length = 500)
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "password", length = 100)
    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String password) {
        this.passWord = password;
    }

    @Column(name = "realname", length = 100)
    public String getRealName() {
        return this.realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

}
