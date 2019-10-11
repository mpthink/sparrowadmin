package com.think.sparrowadmin.remexplus.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;


/**
 * <p>
 * relationship table between task and job
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-29
 */
public class RpTaskJob extends Model<RpTaskJob> {

    private static final long serialVersionUID = 1L;

    /**
     * primary key
     */
	private String id;
    /**
     * task recored id
     */
	private String taskRecordId;
    /**
     * job id
     */
	private String jobId;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskRecordId() {
		return taskRecordId;
	}

	public void setTaskRecordId(String taskRecordId) {
		this.taskRecordId = taskRecordId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}


	@Override
	public String toString(){
		return "RpTaskJob [id=" + id + ", taskRecordId=" + taskRecordId + ", jobId=" + jobId + ", ";
	}
}
