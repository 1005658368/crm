package com.crm.crm.base.mdm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * TRoleFunction entity.
 * 
 * @author Biz
 */
@Entity
@Table(name = "t_s_role_function")
public class TSRoleFunction extends BaseEntity {
    private TSFunction TSFunction;
    private TSRole TSRole;//角色
    private String operation;//按钮
    private String dataRule;//数据规则
    private String roleName;
    private String roleCode;

    @Transient
    public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Transient
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "functionid")
    public TSFunction getTSFunction() {
        return this.TSFunction;
    }

    public void setTSFunction(TSFunction TSFunction) {
        this.TSFunction = TSFunction;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleid")
    public TSRole getTSRole() {
        return this.TSRole;
    }

    public void setTSRole(TSRole TSRole) {
        this.TSRole = TSRole;
    }

    @Column(name = "operation", length = 1000)
    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Column(name = "datarule", length = 1000)
    public String getDataRule() {
        return dataRule;
    }

    public void setDataRule(String dataRule) {
        this.dataRule = dataRule;
    }


}
