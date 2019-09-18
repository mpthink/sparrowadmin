/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.others;

/**
 * @author map6
 */
public class JobOne implements Runnable{

    private int test;

    public JobOne(int test){
        this.test = test;
    }

    @Override
    public void run() {
        System.out.println("This is JobOne" + test);
    }
}
