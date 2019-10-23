/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.sparrowadmin.common.scheduler;

import com.think.sparrowadmin.common.util.SpringUtil;
import com.think.sparrowadmin.remexplus.entity.RpTask;
import com.think.sparrowadmin.remexplus.entity.RpTaskRecord;
import com.think.sparrowadmin.remexplus.service.ILaunchRequest;

import java.util.Date;

/**
 * @author map6
 */
public class TaskRunner implements Runnable{

    private RpTask rpTask;

    private ILaunchRequest buildLaunchRequest = SpringUtil.getBean(ILaunchRequest.class);

    public TaskRunner(RpTask task){
        this.rpTask = task;
    }

    @Override
    public void run() {
        //System.out.println("just for testing.....................");
        RpTaskRecord rpTaskRecord = new RpTaskRecord();
        rpTaskRecord.setTaskId(rpTask.getId());
        rpTaskRecord.setGmtCreate(new Date());
        rpTaskRecord.insert();
        buildLaunchRequest.launchJobsFromRemexFile(rpTask, rpTaskRecord);
    }





}
