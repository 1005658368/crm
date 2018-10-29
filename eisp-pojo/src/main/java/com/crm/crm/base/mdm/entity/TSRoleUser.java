package com.crm.crm.base.mdm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TSRoleUser entity.
 * 
 * @author Biz
 */
@Entity
@Table(name = "t_s_role_user")
public class TSRoleUser extends BaseEntity {
    private TSUser TSUser;
    private TSRole TSRole;
    private String otherId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid")
    public TSUser getTSUser() {
        return this.TSUser;
    }

    public void setTSUser(TSUser TSUser) {
        this.TSUser = TSUser;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleid")
    public TSRole getTSRole() {
        return this.TSRole;
    }

    public void setTSRole(TSRole TSRole) {
        this.TSRole = TSRole;
    }

    @Column(name="otherId",nullable=true,length=360)
	public String getOtherId() {
		return otherId;
	}

	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}
    

}
