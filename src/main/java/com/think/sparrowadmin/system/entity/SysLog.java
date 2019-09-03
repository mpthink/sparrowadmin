package com.think.sparrowadmin.system.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 日志表
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private String id;
    /**
     * 用户
     */
	private String userName;
    /**
     * 日志
     */
	private String title;
    /**
     * 地址
     */
	private String url;
    /**
     * 参数
     */
	private String params;
    /**
     * 创建时间
     */
	private LocalDateTime gmtCreate;


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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public LocalDateTime getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(LocalDateTime gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}


	@Override
	public String toString(){
		return "SysLog [id=" + id + ", userName=" + userName + ", title=" + title + ", url=" + url + ", params=" + params + ", gmtCreate=" + gmtCreate + ", ";
	}
}
