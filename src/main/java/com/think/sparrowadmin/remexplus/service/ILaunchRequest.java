/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.sparrowadmin.remexplus.service;

import com.think.sparrowadmin.remexplus.entity.RpTask;
import com.think.sparrowadmin.remexplus.entity.RpTaskRecord;

/**
 * @author map6
 */
public interface ILaunchRequest {

    void launchJobsFromRemexFile(RpTask task, RpTaskRecord rpTaskRecord);

}
