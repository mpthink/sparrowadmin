/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.sparrowadmin.remexplus.service.impl;

import com.think.sparrowadmin.remexplus.entity.RpTask;
import com.think.sparrowadmin.remexplus.entity.RpTaskRecord;
import com.think.sparrowadmin.remexplus.remexplusUtils.BuildJobSubmitRequest;
import com.think.sparrowadmin.remexplus.remexplusUtils.RemexV10Hanlder;
import com.think.sparrowadmin.remexplus.remexplusUtils.RemexV20Hanlder;
import com.think.sparrowadmin.remexplus.service.ILaunchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

/**
 * @author map6
 */
@Service
public class LaunchRequest implements ILaunchRequest {

    private static final Logger LOG = LoggerFactory.getLogger(LaunchRequest.class);
    private BuildJobSubmitRequest buildJobSubmitRequest = new BuildJobSubmitRequest();

    private static final String submitUrl = "http://sqaprod.lab.beer.town:8080/remex/rest/jobmgr/v4/job/submit";

    public void launchJobsFromRemexFile(RpTask task, RpTaskRecord rpTaskRecord){
        try {
            Object object = buildJobSubmitRequest.buildRemexSubmitObject(task.getRemexPom());
            if(object instanceof com.think.sparrowadmin.remexplus.config.v1_10.RemexSubmit){
                LOG.info("Begin to process remex pom, version is V1.10");
                com.think.sparrowadmin.remexplus.config.v1_10.RemexSubmit remexSubmit = (com.think.sparrowadmin.remexplus.config.v1_10.RemexSubmit) object;
                RemexV10Hanlder.processRemexJobsV10(remexSubmit, task, rpTaskRecord);
            }else if(object instanceof com.think.sparrowadmin.remexplus.config.v1_20.RemexSubmit){
                LOG.info("Begin to process remex pom, version is V1.20");
                com.think.sparrowadmin.remexplus.config.v1_20.RemexSubmit remexSubmit1 = (com.think.sparrowadmin.remexplus.config.v1_20.RemexSubmit) object;
                RemexV20Hanlder.processRemexJobsV20(remexSubmit1, task, rpTaskRecord);
            }else {
                LOG.error("Object is not match v1_10.RemexSubmit or v1_20.RemexSubmit failed!!");
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            LOG.error("LaunchRequest: failed to process remex jobs");
        }

    }

    /**
     * @args command line arguments
     */
    public static void main(String[] args) {
        RpTask rpTask = new RpTask();
        rpTask.setName("paulMa");
        rpTask.setOwnerId("map6");
        rpTask.setOwnerEmail("email@email.com");
        rpTask.setSubmitPom("/upload/2019-10-11/pom-submit-cache-751.xml");
        rpTask.setRemexPom("/upload/2019-10-11/remex-submit-cache-751.xml");

        LaunchRequest buildLanchRequest = new LaunchRequest();

    }

}
