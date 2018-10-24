package com.crm.crm.pojo.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


/**
 * 菜单权限表
 * 
 * @author Biz
 */
@Entity
@Table(name = "t_s_function")
public class TSFunction extends BaseEntity {
    private TSFunction TSFunction;// 父菜单
    private String functionName;// 菜单名称
    private Short functionLevel;// 菜单等级
    private String functionUrl;// 菜单地址
    private Short functionIframe;// 菜单地址打开方式
    private String functionOrder;// 菜单排序
    private Short functionType;// 菜单类型
    private String helpId;// 辅助字段
    private TSIcon TSIcon = new TSIcon();// 菜单图标
    // update-begin--Author:Biz Date:20140509 for：添加云桌面图标实体
    private TSIcon TSIconDesk;// 云桌面菜单图标
    private String iconPath;// 图标路径
    private String iconClas;// 图标样式

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "desk_iconid")
    public TSIcon getTSIconDesk() {
        return TSIconDesk;
    }

    public void setTSIconDesk(TSIcon TSIconDesk) {
        this.TSIconDesk = TSIconDesk;
    }

    // update-end--Author:Biz Date:20140509 for：添加云桌面图标实体

    private List<TSFunction> TSFunctions = new ArrayList<TSFunction>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "iconid")
    public TSIcon getTSIcon() {
        return TSIcon;
    }

    public void setTSIcon(TSIcon tSIcon) {
        TSIcon = tSIcon;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parentfunctionid")
    public TSFunction getTSFunction() {
        return this.TSFunction;
    }

    public void setTSFunction(TSFunction TSFunction) {
        this.TSFunction = TSFunction;
    }

    @Column(name = "functionname", nullable = false, length = 50)
    public String getFunctionName() {
        return this.functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    @Column(name = "functionlevel")
    public Short getFunctionLevel() {
        return this.functionLevel;
    }

    public void setFunctionLevel(Short functionLevel) {
        this.functionLevel = functionLevel;
    }

    @Column(name = "functiontype")
    public Short getFunctionType() {
        return this.functionType;
    }

    public void setFunctionType(Short functionType) {
        this.functionType = functionType;
    }

    @Column(name = "functionurl", length = 2000)
    public String getFunctionUrl() {
        return this.functionUrl;
    }

    public void setFunctionUrl(String functionUrl) {
        this.functionUrl = functionUrl;
    }

    @Column(name = "functionorder")
    public String getFunctionOrder() {
        return functionOrder;
    }

    public void setFunctionOrder(String functionOrder) {
        this.functionOrder = functionOrder;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "TSFunction")
    @OrderBy("functionOrder asc")
    public List<TSFunction> getTSFunctions() {
        return TSFunctions;
    }

    public void setTSFunctions(List<TSFunction> TSFunctions) {
        this.TSFunctions = TSFunctions;
    }

    @Column(name = "functioniframe")
    public Short getFunctionIframe() {
        return functionIframe;
    }

    public void setFunctionIframe(Short functionIframe) {
        this.functionIframe = functionIframe;
    }

    @Transient
    public String getHelpId() {
        return helpId;
    }

    public void setHelpId(String helpId) {
        this.helpId = helpId;
    }

    @Transient
    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    @Transient
    public String getIconClas() {
        return iconClas;
    }

    public void setIconClas(String iconClas) {
        this.iconClas = iconClas;
    }
}
