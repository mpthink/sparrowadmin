/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.sparrowadmin.remexplus.remexplusUtils;

import com.dell.compellent.test.remex.message.v4_00.remoteexecution.JobSubmitRequest;
import com.dell.compellent.test.remex.message.v4_00.remoteexecution.JobSubmitResponse;
import com.think.sparrowadmin.remexplus.config.v1_10.*;
import com.think.sparrowadmin.remexplus.entity.RpTask;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author map6
 */
public class RemexV10Hanlder {

    private static final String submitUrl = "http://sqaprod.lab.beer.town:8080/remex/rest/jobmgr/v4/job/submit";

    public static void processRemexJobsV10(RemexSubmit remexSubmit, RpTask task){
        RestTemplate template = new RestTemplate();
        List< HttpMessageConverter<?>> converters = BuildJobSubmitRequest.buildConverts();
        template.setMessageConverters(converters);
        List<JobGroupType> jobGroups = remexSubmit.getJobGroups().getJobGroup();
        List<PoolGroupType> poolGroups = remexSubmit.getPoolGroups().getPoolGroup();
        List<SubmitGroupType> submitGroups = remexSubmit.getSubmitGroups().getSubmitGroup();
        Map<String, JobGroupType> jobGroupTypeMap = jobGroups.stream().collect(Collectors.toMap(JobGroupType::getId, jobGroupType -> jobGroupType));
        Map<String, PoolGroupType> poolGroupTypeMap = poolGroups.stream().collect(Collectors.toMap(PoolGroupType::getId, poolGroupType -> poolGroupType));

        for (SubmitGroupType submitGroup : submitGroups) {
            String jobGroupRef = submitGroup.getJobGroupRef();
            String poolGroupRef = submitGroup.getPoolGroupRef();
            JobGroupType jobGroupType = jobGroupTypeMap.get(jobGroupRef);
            PoolGroupType poolGroupType = poolGroupTypeMap.get(poolGroupRef);

            List<JobType> jobs = jobGroupType.getJob();
            for (JobType job : jobs) {
                List<PoolType> pools = poolGroupType.getPool();
                for (PoolType pool : pools) {
                    JobSubmitRequest jobLaunchRequest = BuildJobSubmitRequest.buildJobLaunchRequest(task, job, pool.getId());
                    JobSubmitResponse jobSubmitResponse = template.postForObject(submitUrl, jobLaunchRequest, JobSubmitResponse.class);
                    System.out.println("jobSubmitResponse: " + jobSubmitResponse);
                    //TODO save job id to DB
                }
            }
        }
    }
}
