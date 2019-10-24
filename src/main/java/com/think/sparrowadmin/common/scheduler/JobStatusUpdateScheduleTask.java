/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.sparrowadmin.common.scheduler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dell.compellent.test.remex.message.v4_00.global.JobQueryType;
import com.dell.compellent.test.remex.message.v4_00.remoteexecution.JobQueryResponse;
import com.think.sparrowadmin.remexplus.entity.RpJob;
import com.think.sparrowadmin.remexplus.service.IRpJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author map6
 */
@Component
public class JobStatusUpdateScheduleTask {

    private static final Logger LOG = LoggerFactory.getLogger(JobStatusUpdateScheduleTask.class);
    private static final String JOB_QUERY_URL = "http://sqaprod.lab.beer.town:8080/remex/rest/querymgr/v4/job/query?id=";
    private static final List<String> FINAL_JOB_STATUS = Arrays.asList("Finalized","Completed","FailedInitialization");

    @Autowired
    private IRpJobService jobService;

    @Scheduled(cron = "0 */10 * * * *")
    public void updateJobStatusAndResult(){
        RestTemplate template = new RestTemplate();
        QueryWrapper<RpJob> queryWrapper = new QueryWrapper<>();
        queryWrapper.notIn("status", FINAL_JOB_STATUS);
        List<RpJob> rpJobs = jobService.list(queryWrapper);
        rpJobs.forEach(rpJob -> {
            String requestUrl = JOB_QUERY_URL + rpJob.getRemexJobId();
            JobQueryResponse jobQueryResponse = template.getForObject(requestUrl, JobQueryResponse.class);
            JobQueryType jobQueryType = jobQueryResponse.getJobQuery().get(0);
            rpJob.setName(jobQueryType.getJobIdentifier().getJobName());
            rpJob.setStatus(jobQueryType.getJobStatus().getJobStatus().value());
            if(jobQueryType.getJobStatus().getJobResult() != null){
                rpJob.setResult(jobQueryType.getJobStatus().getJobResult().value());
            }
            rpJob.insertOrUpdate();
        });

    }

}
