package com.think.sparrowadmin.system.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private String id;
    /**
     * 父级菜单ID
     */
	private String pid;
    /**
     * 菜单名称
     */
	private String menuName;
    /**
     * 状态 0:正常 1:禁用
     */
	private Integer menuStatus;
    /**
     * 连接地址
     */
	private String url;
    /**
     * 图标
     */
	private String icon;
    /**
     * 排序
     */
	private Integer sort;
    /**
     * 深度
     */
	private Integer deep;
    /**
     * 编码
     */
	private String code;
    /**
     * 资源名称
     */
	private String resource;
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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getMenuStatus() {
		return menuStatus;
	}

	public void setMenuStatus(Integer menuStatus) {
		this.menuStatus = menuStatus;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getDeep() {
		return deep;
	}

	public void setDeep(Integer deep) {
		this.deep = deep;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
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
		return "SysMenu [id=" + id + ", pid=" + pid + ", menuName=" + menuName + ", menuStatus=" + menuStatus + ", url=" + url + ", icon=" + icon + ", sort=" + sort + ", deep=" + deep + ", code=" + code + ", resource=" + resource + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + ", ";
	}
}
