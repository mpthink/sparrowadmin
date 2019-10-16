package com.think.sparrowadmin.remexplus.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * table for creating regular task
 * </p>
 *
 * @author Paul Ma
 * @since 2019-10-16
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
     * towner ID
     */
	private String ownerId;
    /**
     * owner email address
     */
	private String ownerEmail;
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
	private LocalDateTime gmtCreate;
    /**
     * task create time
     */
	private LocalDateTime gmtUpdate;
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

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
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

	public LocalDateTime getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(LocalDateTime gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public LocalDateTime getGmtUpdate() {
		return gmtUpdate;
	}

	public void setGmtUpdate(LocalDateTime gmtUpdate) {
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
		return "RpTask [id=" + id + ", name=" + name + ", ownerId=" + ownerId + ", ownerEmail=" + ownerEmail + ", runnerPom=" + runnerPom + ", submitPom=" + submitPom + ", remexPom=" + remexPom + ", quartz=" + quartz + ", gmtCreate=" + gmtCreate + ", gmtUpdate=" + gmtUpdate + ", status=" + status + ", ";
	}
}
