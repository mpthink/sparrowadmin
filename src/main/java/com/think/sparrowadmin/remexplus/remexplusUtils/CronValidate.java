/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.sparrowadmin.remexplus.remexplusUtils;

import org.quartz.impl.triggers.CronTriggerImpl;

import java.util.Date;

/**
 * @author map6
 */
public class CronValidate {

    public static boolean isValidExpression(final String cronExpression){
        CronTriggerImpl trigger = new CronTriggerImpl();
        try {
            trigger.setCronExpression(cronExpression);
            Date date = trigger.computeFirstFireTime(null);
            return date != null && date.after(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @args command line arguments
     */
    public static void main(String[] args) {
        String contTest = "0 0 10,15,16 * * ?";
        String contTest2 = "0/5 * * * * ? ";

        System.out.println(isValidExpression(contTest));
        System.out.println(isValidExpression(contTest2));
    }
}
