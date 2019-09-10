/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.sparrowadmin.springConfig;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author map6
 */
@Configuration
public class FreeMarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;

    @PostConstruct
    public void setSharedVariable() {
        try {
            configuration.setSharedVariable("shiro", new ShiroTags());
            //https://stackoverflow.com/questions/30503889/freemarker-and-java8-default-methods
            configuration.setObjectWrapper(new BeansWrapper(new Version("2.3.28")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
