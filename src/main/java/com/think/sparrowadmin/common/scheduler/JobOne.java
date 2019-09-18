/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.sparrowadmin.common.scheduler;

/**
 * @author map6
 */
//@Component
public class JobOne {

    //@Scheduled(cron = "0/2 * * * * ?")
    public void doSomething(){
        System.out.println("This is JobOne");
    }
}
