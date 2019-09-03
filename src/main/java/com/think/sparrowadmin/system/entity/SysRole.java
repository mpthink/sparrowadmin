package com.think.sparrowadmin.system.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 角色表
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private String id;
    /**
     * 角色
     */
	private String roleName;
    /**
     * 角色描述
     */
	private String roleDesc;
    /**
     * 状态,0:启用,1:禁用
     */
	private Integer roleStatus;
    /**
     * 排序
     */
	private Integer sort;
    /**
     * 创建时间
     */
	private LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
	private LocalDateTime gmtModified;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Integer getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public LocalDateTime getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(LocalDateTime gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public LocalDateTime getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(LocalDateTime gmtModified) {
		this.gmtModified = gmtModified;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}


	@Override
	public String toString(){
		return "SysRole [id=" + id + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", roleStatus=" + roleStatus + ", sort=" + sort + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + ", ";
	}
}
