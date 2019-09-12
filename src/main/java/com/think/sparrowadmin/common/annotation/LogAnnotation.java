/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.sparrowadmin.common.annotation;

import java.lang.annotation.*;

/**
 * @author map6
 */
@Target(ElementType.METHOD)  // @Target：定义注解的作用目标, 方法和方法参数
@Retention(RetentionPolicy.RUNTIME) //注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Documented //说明该注解将被包含在javadoc中
@Inherited  //说明子类可以继承父类中的该注解
public @interface LogAnnotation {

    String value();

}
