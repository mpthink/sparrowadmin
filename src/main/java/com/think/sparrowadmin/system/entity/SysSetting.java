package com.think.sparrowadmin.system.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 系统设置表
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
public class SysSetting extends Model<SysSetting> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private String id;
    /**
     * 名称
     */
	private String sysName;
    /**
     * KEY
     */
	private String sysKey;
    /**
     * 值
     */
	private String sysValue;
    /**
     * 排序
     */
	private Integer sort;
    /**
     * 说明
     */
	private String sysDesc;
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

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getSysKey() {
		return sysKey;
	}

	public void setSysKey(String sysKey) {
		this.sysKey = sysKey;
	}

	public String getSysValue() {
		return sysValue;
	}

	public void setSysValue(String sysValue) {
		this.sysValue = sysValue;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getSysDesc() {
		return sysDesc;
	}

	public void setSysDesc(String sysDesc) {
		this.sysDesc = sysDesc;
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
		return "SysSetting [id=" + id + ", sysName=" + sysName + ", sysKey=" + sysKey + ", sysValue=" + sysValue + ", sort=" + sort + ", sysDesc=" + sysDesc + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + ", ";
	}
}
