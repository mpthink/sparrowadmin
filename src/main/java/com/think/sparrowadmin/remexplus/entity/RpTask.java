package com.think.sparrowadmin.remexplus.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * table for creating regular task
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-29
 */
public class RpTask extends Model<RpTask> {

    private static final long serialVersionUID = 1L;

    /**
     * primary key
     */
	private String id;
    /**
     * task name
     */
	private String name;
    /**
     * task owner
     */
	private String owner;
    /**
     * runner pom file
     */
	private String runnerPom;
    /**
     * submit pom file
     */
	private String submitPom;
    /**
     * remex pom file
     */
	private String remexPom;
    /**
     * schedule of task executing
     */
	private String quartz;
    /**
     * task create time
     */
	private Date gmtCreate;
    /**
     * task create time
     */
	private Date gmtUpdate;
    /**
     * schedule status,0:enable,1:disable
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getRunnerPom() {
		return runnerPom;
	}

	public void setRunnerPom(String runnerPom) {
		this.runnerPom = runnerPom;
	}

	public String getSubmitPom() {
		return submitPom;
	}

	public void setSubmitPom(String submitPom) {
		this.submitPom = submitPom;
	}

	public String getRemexPom() {
		return remexPom;
	}

	public void setRemexPom(String remexPom) {
		this.remexPom = remexPom;
	}

	public String getQuartz() {
		return quartz;
	}

	public void setQuartz(String quartz) {
		this.quartz = quartz;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtUpdate() {
		return gmtUpdate;
	}

	public void setGmtUpdate(Date gmtUpdate) {
		this.gmtUpdate = gmtUpdate;
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
		return "RpTask [id=" + id + ", name=" + name + ", owner=" + owner + ", runnerPom=" + runnerPom + ", submitPom=" + submitPom + ", remexPom=" + remexPom + ", quartz=" + quartz + ", gmtCreate=" + gmtCreate + ", gmtUpdate=" + gmtUpdate + ", status=" + status + ", ";
	}
}
