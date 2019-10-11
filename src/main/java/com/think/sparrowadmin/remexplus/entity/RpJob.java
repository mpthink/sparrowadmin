package com.think.sparrowadmin.remexplus.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;


/**
 * <p>
 * jobs of task
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-29
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
     * job status,0:scheduled,1:started,2:launched,3:running,4:completed.....
     */
	private Integer status;


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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}


	@Override
	public String toString(){
		return "RpJob [id=" + id + ", name=" + name + ", remexJobId=" + remexJobId + ", status=" + status + ", ";
	}
}
