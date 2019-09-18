/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.sparrowadmin.common.util;

/**
 * @author map6
 */
public class OSinfoUtil {

    private static String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isLinux(){
        return OS.indexOf("linux")>=0;
    }

    public static boolean isWindows(){
        return OS.indexOf("windows")>=0;
    }

}
