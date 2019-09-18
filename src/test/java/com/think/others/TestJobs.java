/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.others;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ScheduledFuture;

/**
 * @author map6
 */
public class TestJobs {

    /**
     * @args command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.initialize();

        while (true){
            ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(new JobOne(11), new CronTrigger("0/1 * * * * *"));
            ScheduledFuture<?> schedule1 = threadPoolTaskScheduler.schedule(new JobOne(22), new CronTrigger("0/2 * * * * *"));

            Thread.sleep(20000);

        }
    }
}
