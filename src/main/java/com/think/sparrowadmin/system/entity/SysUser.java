package com.think.sparrowadmin.system.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private String id;
    /**
     * 用户名
     */
	private String userName;
    /**
     * 密码
     */
	private String password;
    /**
     * 用户状态,0:启用,1:禁用
     */
	private Integer userStatus;
    /**
     * 描述
     */
	private String userDesc;
    /**
     * 头像
     */
	private String avatar;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
		return "SysUser [id=" + id + ", userName=" + userName + ", password=" + password + ", userStatus=" + userStatus + ", userDesc=" + userDesc + ", avatar=" + avatar + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + ", ";
	}
}
