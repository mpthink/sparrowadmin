package com.think.sparrowadmin.system.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;


/**
 * <p>
 * 角色菜单关联表
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
public class SysRoleMenu extends Model<SysRoleMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private String id;
    /**
     * 角色主键
     */
	private String roleId;
    /**
     * 菜单主键
     */
	private String menuId;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}


	@Override
	public String toString(){
		return "SysRoleMenu [id=" + id + ", roleId=" + roleId + ", menuId=" + menuId + ", ";
	}
}
