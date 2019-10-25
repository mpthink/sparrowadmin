/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.sparrowadmin.remexplus.scheduler;

import com.think.sparrowadmin.remexplus.entity.RpTask;

/**
 * @author map6
 */
public interface IScheduleCronTaskService {

    void updateCronTask(RpTask task);

    void addCronTask(RpTask task);

    void removeCronTask(String taskId);

}
