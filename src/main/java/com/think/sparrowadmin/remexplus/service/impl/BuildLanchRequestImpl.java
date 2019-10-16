/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.sparrowadmin.remexplus.service.impl;

import com.dell.compellent.test.remex.message.v4_00.executor.JobLaunchRequest;
import com.dell.compellent.test.remex.message.v4_00.framework.TestPayload;
import com.dell.compellent.test.remex.message.v4_00.global.ExecutorSpecificationType;
import com.dell.compellent.test.remex.message.v4_00.global.JobSpecificationType;
import com.dell.compellent.test.remex.message.v4_00.global.RequestHeaderType;
import com.think.sparrowadmin.common.config.Config;
import com.think.sparrowadmin.remexplus.entity.RpTask;
import com.think.sparrowadmin.remexplus.remexplusUtils.FileHandleUtils;
import com.think.sparrowadmin.remexplus.service.IBuildLaunchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

/**
 * @author map6
 */
@Service
public class BuildLanchRequestImpl implements IBuildLaunchRequest{

    private static final Logger LOG = LoggerFactory.getLogger(BuildLanchRequestImpl.class);

    private static final String pomTemplatePath = "remexplus/pomTemplate.txt";

    public void launchJobsFromRemexFile(RpTask task){
        //TODO read remex pom file content, list all jobs
    }

    private JobLaunchRequest buildJobLaunchRequest(RpTask task, String mainClass, String poolName){
        JobLaunchRequest jobLaunchRequest = new JobLaunchRequest();
        RequestHeaderType requestHeaderType = new RequestHeaderType();
        //TODO
        jobLaunchRequest.setRequestHeader(requestHeaderType);
        ExecutorSpecificationType executorSpecificationType = new ExecutorSpecificationType();
        //TODO
        jobLaunchRequest.setExecutorSpecification(executorSpecificationType);
        JobSpecificationType jobSpecification = new JobSpecificationType();
        //TODO
        jobLaunchRequest.setJobSpecification(jobSpecification);

        return jobLaunchRequest;
    }

    private TestPayload buildTestPayLoad(RpTask task, String mainClass, String poolName){
        TestPayload testPayload = new TestPayload();
        String pomFileContent = buildPomFileContents(task.getSubmitPom());
        String testConfig = buildTestConfig(mainClass, poolName, task.getOwnerEmail());
        testPayload.setMainClass(mainClass);
        testPayload.setPomFileContents(pomFileContent.getBytes());
        testPayload.setTestConfig(testConfig.getBytes());
        return testPayload;
    }


    private String buildPomFileContents(String pomSubmitFilePath){
        String pomTemplateContent = FileHandleUtils.readFileFromClassPath(pomTemplatePath);
        String pomFileAbsolutePath = Config.UPLOAD_FOLDER + pomSubmitFilePath.replace("/upload/","");
        String taskSubmitFileContent = FileHandleUtils.readFileByChars(new File(pomFileAbsolutePath));
        int begin = taskSubmitFileContent.indexOf("<dependencies>");
        int end = taskSubmitFileContent.indexOf("</dependencies>");
        String dependencies = FileHandleUtils.replaceSpecialStr(taskSubmitFileContent.substring(begin + 14, end));
        pomTemplateContent.replace("<replacement>",dependencies);
        return pomTemplateContent;
    }

    private String buildTestConfig(String mainClass, String poolName, String email){
        String templateConfig = "#<classname>[1] <-> <classnamewithpackage>\n" +
                                "#<date>\n" +
                                "com.compellent.systemtest.libs.tasks.CompellentTask\\ <->\\ _taskconfiguration_emailContacts=<email>,\n" +
                                "com.compellent.systemtest.libs.tasks.CompellentDisplayableTask\\ <->\\ <poolName>";
        String dateString = new Date().toString();
        String[] strings = mainClass.split(".");
        String className = strings[strings.length-1];
        templateConfig.replace("<classname>", className);
        templateConfig.replace("<classnamewithpackage>", mainClass);
        templateConfig.replace("<date>", dateString);
        templateConfig.replace("<poolName>", poolName);
        templateConfig.replace("<email>", email);
        return templateConfig;
    }

}
