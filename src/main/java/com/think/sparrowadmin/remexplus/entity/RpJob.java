package com.think.sparrowadmin.remexplus.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;


/**
 * <p>
 * jobs of task
 * </p>
 *
 * @author Paul Ma
 * @since 2019-10-22
 */
public class RpJob extends Model<RpJob> {

    private static final long serialVersionUID = 1L;

    /**
     * primary key
     */
	private String id;
    /**
     * job name
     */
	private String name;
    /**
     * job id in remex
     */
	private String remexJobId;
    /**
     * job status
     */
	private String status;
    /**
     * job result
     */
	private String result;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemexJobId() {
		return remexJobId;
	}

	public void setRemexJobId(String remexJobId) {
		this.remexJobId = remexJobId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}


	@Override
	public String toString(){
		return "RpJob [id=" + id + ", name=" + name + ", remexJobId=" + remexJobId + ", status=" + status + ", result=" + result + ", ";
	}
}
