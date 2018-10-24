package com.crm.crm.pojo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色表
 * 
 * @author Biz
 */
@Entity
@Table(name = "t_s_role")
public class TSRole extends BaseEntity {
    private String roleName;// 角色名称
    private String roleCode;// 角色编码
	private String roleType;

	@Column(name = "rolename", nullable = false, length = 100)
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    
    @Column(name = "ROLE_TYPE")
    public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	@Column(name = "rolecode", length = 100)
    public String getRoleCode() {
        return this.roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
