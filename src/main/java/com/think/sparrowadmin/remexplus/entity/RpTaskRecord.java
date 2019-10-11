package com.think.sparrowadmin.remexplus.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * record task running
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-29
 */
public class RpTaskRecord extends Model<RpTaskRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * primary key
     */
	private String id;
    /**
     * task id
     */
	private String taskId;
    /**
     * task create time
     */
	private Date gmtCreate;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}


	@Override
	public String toString(){
		return "RpTaskRecord [id=" + id + ", taskId=" + taskId + ", gmtCreate=" + gmtCreate + ", ";
	}
}
