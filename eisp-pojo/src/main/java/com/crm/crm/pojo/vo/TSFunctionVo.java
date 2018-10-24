package com.crm.crm.pojo.vo;

import java.io.Serializable;
import java.util.List;

public class TSFunctionVo extends BaseEntityVo{
    private String id;
    private String parentFunctionId;// 父菜单id
    private String functionName;// 菜单名称
    private Short functionLevel;// 菜单等级
    private String functionUrl;// 菜单地址
    private Short functionIframe;// 菜单地址打开方式
    private String functionOrder;// 菜单排序
    private Short functionType;// 菜单类型
    private String iconPath;// 图标路径
    private String iconClas;// 图标样式
    private List<TSFunctionVo> functionVoList;//子菜单
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getParentFunctionId() {
        return parentFunctionId;
    }

    public void setParentFunctionId(String parentFunctionId) {
        this.parentFunctionId = parentFunctionId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Short getFunctionLevel() {
        return functionLevel;
    }

    public void setFunctionLevel(Short functionLevel) {
        this.functionLevel = functionLevel;
    }

    public String getFunctionUrl() {
        return functionUrl;
    }

    public void setFunctionUrl(String functionUrl) {
        this.functionUrl = functionUrl;
    }

    public Short getFunctionIframe() {
        return functionIframe;
    }

    public void setFunctionIframe(Short functionIframe) {
        this.functionIframe = functionIframe;
    }

    public String getFunctionOrder() {
        return functionOrder;
    }

    public void setFunctionOrder(String functionOrder) {
        this.functionOrder = functionOrder;
    }

    public Short getFunctionType() {
        return functionType;
    }

    public void setFunctionType(Short functionType) {
        this.functionType = functionType;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getIconClas() {
        return iconClas;
    }

    public void setIconClas(String iconClas) {
        this.iconClas = iconClas;
    }

    public List<TSFunctionVo> getFunctionVoList() {
        return functionVoList;
    }

    public void setFunctionVoList(List<TSFunctionVo> functionVoList) {
        this.functionVoList = functionVoList;
    }
}

