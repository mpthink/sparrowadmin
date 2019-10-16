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
import com.think.sparrowadmin.remexplus.service.IBuildLaunchRequest;

/**
 * @author map6
 */
public class TaskRunner implements Runnable{

    private RpTask rpTask;

    private IBuildLaunchRequest buildLaunchRequest = SpringUtil.getBean(IBuildLaunchRequest.class);

    public TaskRunner(RpTask task){
        this.rpTask = task;
    }

    @Override
    public void run() {
        //TODO launch all jobs
        System.out.println("just for testing.....................");
        //Read file

    }





}
